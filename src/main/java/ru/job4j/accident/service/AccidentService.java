package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentService {

    private final AccidentHibernate accidentHbm;

    public AccidentService(AccidentHibernate accidentJdbc) {
        this.accidentHbm = accidentJdbc;
    }

    public void save(Accident accident) {
        if (accident.getId() == 0) {
            this.accidentHbm.save(accident);
        } else {
            this.accidentHbm.update(accident);
        }
    }

    public boolean deleteById(int elId) {
        return accidentHbm.deleteById(elId);
    }

    public Accident findById(int id) {
        return accidentHbm.getById(id);
    }

    public List<Accident> getAll() {
        return new ArrayList<>(accidentHbm.getAll());
    }

}
