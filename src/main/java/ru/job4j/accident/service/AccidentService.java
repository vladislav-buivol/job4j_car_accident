package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AccidentService {

    private final AccidentRepository accidentRepo;

    public AccidentService(AccidentRepository accidentJdbc) {
        this.accidentRepo = accidentJdbc;
    }

    public void save(Accident accident) {
        this.accidentRepo.save(accident);
    }

    public void deleteById(int elId) {
        accidentRepo.deleteById(elId);
    }

    public Accident findById(int id) {
        Optional<Accident> ac = accidentRepo.findById(id);
        return ac.orElse(new Accident());
    }

    public List<Accident> getAll() {
        return accidentRepo.findAll();
    }

}
