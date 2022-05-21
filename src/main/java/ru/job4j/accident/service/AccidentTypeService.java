package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeJdbcTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentTypeService {

    private final AccidentTypeJdbcTemplate typeJdbc;

    public AccidentTypeService(AccidentTypeJdbcTemplate accidentMem) {
        this.typeJdbc = accidentMem;
    }

    public void save(AccidentType accident) {
        if (typeJdbc.getById(accident.getId()) == null) {
            typeJdbc.save(accident);
        } else {
            typeJdbc.update(accident);
        }
    }

    public boolean deleteById(int elId) {
        return typeJdbc.deleteById(elId);
    }

    public AccidentType findById(int id) {
        return typeJdbc.getById(id);
    }

    public List<AccidentType> getAll() {
        return new ArrayList<>(typeJdbc.getAll());
    }

}
