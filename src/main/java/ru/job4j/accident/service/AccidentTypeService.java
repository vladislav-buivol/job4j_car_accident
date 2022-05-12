package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeMem;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentTypeService {

    private final AccidentTypeMem mem;

    public AccidentTypeService(AccidentTypeMem accidentMem) {
        this.mem = accidentMem;
    }

    public void save(AccidentType accident) {
        if (mem.getById(accident.getId()) == null) {
            mem.save(accident);
        } else {
            mem.update(accident);
        }
    }

    public AccidentType deleteById(int elId) {
        return mem.deleteById(elId);
    }

    public AccidentType findById(int id) {
        return mem.getById(id);
    }

    public List<AccidentType> getAll() {
        return new ArrayList<>(mem.getAll().values());
    }

}
