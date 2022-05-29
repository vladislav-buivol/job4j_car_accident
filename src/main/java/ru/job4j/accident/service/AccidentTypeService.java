package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeHbm;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentTypeService {

    private final AccidentTypeHbm accidentTypeHbm;

    public AccidentTypeService(AccidentTypeHbm accidentTypeHbm) {
        this.accidentTypeHbm = accidentTypeHbm;
    }

    public void save(AccidentType accident) {
        if (accidentTypeHbm.getById(accident.getId()) == null) {
            accidentTypeHbm.save(accident);
        } else {
            accidentTypeHbm.update(accident);
        }
    }

    public boolean deleteById(int elId) {
        return accidentTypeHbm.deleteById(elId);
    }

    public AccidentType findById(int id) {
        return accidentTypeHbm.getById(id);
    }

    public List<AccidentType> getAll() {
        return new ArrayList<>(accidentTypeHbm.getAll());
    }

}
