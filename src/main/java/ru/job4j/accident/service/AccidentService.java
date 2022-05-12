package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentService {

    private final AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public void save(Accident accident) {
        if (accidentMem.getById(accident.getId()) == null) {
            accidentMem.save(accident);
        } else {
            accidentMem.update(accident);
        }
    }

    public Accident deleteById(int elId) {
        return accidentMem.deleteById(elId);
    }

    public Accident findById(int id) {
        return accidentMem.getById(id);
    }

    public List<Accident> getAll() {
        return new ArrayList<>(accidentMem.getAll().values());
    }

}
