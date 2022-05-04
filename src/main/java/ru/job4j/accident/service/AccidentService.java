package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentService {

    private AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public void add(Accident accident) {
        accidentMem.add(accident);
    }

    public Accident deleteById(int elId) {
        return accidentMem.deleteById(elId);
    }

    public Accident getById(int id) {
        return accidentMem.getById(id);
    }

    public List<Accident> getAll() {
        return new ArrayList<>(accidentMem.getAll().values());
    }

}
