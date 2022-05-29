package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.job4j.accident.model.Rule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RuleJdbcTemplate {
    private final JdbcTemplate jdbc;

    public RuleJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Rule> getAll() {
        return jdbc.query("select id, name from rule",
                (rs, row) -> {
                    return createRuleFromRes(rs);
                });
    }

    public Rule save(Rule rule) {
        jdbc.update("insert into rule (name) values (?)",
                rule.getName());
        return rule;
    }

    public Rule getById(int id) {
        return
                jdbc.query("select id, name from rule "
                                + "where id = (?)",
                        new Object[] {id},
                        (rs, row) -> {
                            return createRuleFromRes(rs);
                        }).get(0);
    }

    private Rule createRuleFromRes(ResultSet rs) throws SQLException {
        return Rule.of(rs.getInt("id"), rs.getString("name"));
    }

    public void update(Rule rule) {
        jdbc.update("update rule set name = ? "
                        + "where id = ? ",
                new Object[] {rule.getName(), rule.getId()});
    }

    public boolean deleteById(int elId) {
        return jdbc.update("delete from rule "
                        + "where id = (?)",
                new Object[] {elId}) == 1;
    }
}
