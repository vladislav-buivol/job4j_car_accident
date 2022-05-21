package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;
    private final RuleJdbcTemplate ruleJdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc, RuleJdbcTemplate ruleJdbc,
                                AccidentTypeJdbcTemplate typeJdbc) {
        this.jdbc = jdbc;
        this.ruleJdbc = ruleJdbc;
    }

    public Accident save(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into accident (name, text, address, accident_type_id) values (?,?,?,?) returning id"
                    , new String[] {"name", "text", "address", "accident_type_id"});
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        accident.setId(keyHolder.getKey().intValue());
        saveAccidentRule(accident);
        return accident;
    }

    public List<Accident> getAll() {
        List<Accident> accidents =
                jdbc.query(
                        "select distinct a.id, a.name, a.text,a. address, t.id as t_id, t.name as t_name from accident a "
                                + " left join accident_type t on a.accident_type_id = t.id",
                        rowMapping()
                );
        for (Accident accident : accidents) {
            accident.setRules(new HashSet<>(findAccidentRules(accident.getId())));
        }
        return accidents;
    }

    public Accident getById(int id) {
        List<Accident> accidents =
                jdbc.query(
                        "select distinct a.id, a.name, a.text,a. address, t.id as t_id, t.name as t_name from accident a "
                                + " left join accident_type t on a.accident_type_id = t.id "
                                + "where a.id =(?)",
                        new Object[] {id},
                        rowMapping());
        Accident accident;
        if (accidents.size() == 1) {
            accident = accidents.get(0);
            accident.setRules(findAccidentRules(accident.getId()));
            return accident;
        } else {
            return new Accident().setId(-1);
        }
    }

    public void update(Accident accident) {
        jdbc.update("delete from accident_rule "
                        + "where accident_id = (?)",
                new Object[] {accident.getId()});

        jdbc.update("update accident set name = ?, "
                        + " text = ?, "
                        + " address = ?, "
                        + " accident_type_id= ? "
                        + " where id = ?",
                new Object[] {accident.getName(), accident.getText(),
                        accident.getAddress(), accident.getType().getId(), accident.getId()});
        saveAccidentRule(accident);
    }

    public boolean deleteById(int elId) {
        return jdbc.update("delete from accident "
                        + "where id = (?)",
                new Object[] {elId}) == 1;
    }

    private void saveAccidentRule(Accident accident) {
        if (accident.getRules() != null && accident.getRules().size() > 0) {
            for (Rule rule : accident.getRules()) {
                jdbc.update("insert into accident_rule (accident_id, rule_id) values (?,?)",
                        accident.getId(),
                        rule.getId());
            }
        }
    }

    private Set<Rule> findAccidentRules(int accidentId) {
        List<Integer> rulesIds =
                jdbc.query("select rule_id from accident_rule "
                                + "where accident_id = (?)",
                        new Object[] {accidentId},
                        (rs, row) -> rs.getInt("rule_id"));

        Set<Rule> rules = new HashSet<>();
        for (int id : rulesIds) {
            rules.add(ruleJdbc.getById(id));
        }
        return rules;
    }


    private RowMapper<Accident> rowMapping() {
        return (rs, rowNum) -> {
            Accident accident = createAccidentFromRes(rs);
            accident.setType(AccidentType.of(rs.getInt("t_id"), rs.getString("t_name")));
            return accident;
        };
    }

    private Accident createAccidentFromRes(ResultSet rs) throws SQLException {
        Accident accident = new Accident()
                .setId(rs.getInt("id"))
                .setName(rs.getString("name"))
                .setText(rs.getString("text"))
                .setAddress(rs.getString("address"));
        return accident;
    }


}