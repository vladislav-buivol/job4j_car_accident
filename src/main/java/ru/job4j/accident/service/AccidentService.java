package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentService {

    private final AccidentJdbcTemplate accidentJdbc;

    public AccidentService(AccidentJdbcTemplate accidentJdbc) {
        this.accidentJdbc = accidentJdbc;
    }

    public void save(Accident accident) {
        if (accidentJdbc.getById(accident.getId()).getId() == -1) {
            accidentJdbc.save(accident);
        } else {
            accidentJdbc.update(accident);
        }
    }

    public boolean  deleteById(int elId) {
        return accidentJdbc.deleteById(elId);
    }

    public Accident findById(int id) {
        return accidentJdbc.getById(id);
    }

    public List<Accident> getAll() {
        return new ArrayList<>(accidentJdbc.getAll());
    }

}
