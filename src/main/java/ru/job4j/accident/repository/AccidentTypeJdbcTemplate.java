package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AccidentTypeJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentTypeJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<AccidentType> getAll() {
        return jdbc.query("select id, name from accident_type",
                (rs, row) -> {
                    return createTypeFromRes(rs);
                });
    }

    public AccidentType save(AccidentType type) {
        jdbc.update("insert into accident_type (name) values (?)",
                type.getName());
        return type;
    }

    public AccidentType getById(int id) {
        return
                jdbc.query("select id, name from accident_type "
                                + "where id = (?)",
                        new Object[] {id},
                        (rs, row) -> {
                            return createTypeFromRes(rs);
                        }).get(0);
    }

    private AccidentType createTypeFromRes(ResultSet rs) throws SQLException {
        return AccidentType.of(rs.getInt("id"), rs.getString("name"));
    }

    public void update(AccidentType type) {
        jdbc.update("update accident_type set name = ? "
                        + "where id = ? ",
                new Object[] {type.getName(), type.getId()});
    }

    public boolean deleteById(int elId) {
        return jdbc.update("delete from accident_type "
                        + "where id = (?)",
                new Object[] {elId}) == 1;
    }
}
