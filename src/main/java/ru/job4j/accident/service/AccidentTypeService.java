package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccidentTypeService {

    private final AccidentTypeRepository accidentTypeRepo;

    public AccidentTypeService(AccidentTypeRepository accidentTypeRepo) {
        this.accidentTypeRepo = accidentTypeRepo;
    }

    public void save(AccidentType accident) {
        accidentTypeRepo.save(accident);
    }

    public void deleteById(int elId) {
        accidentTypeRepo.deleteById(elId);
    }

    public AccidentType findById(int id) {
        Optional<AccidentType> type = accidentTypeRepo.findById(id);
        return type.orElseGet(AccidentType::new);
    }

    public List<AccidentType> getAll() {
        return new ArrayList<>(accidentTypeRepo.findAll());
    }

}
