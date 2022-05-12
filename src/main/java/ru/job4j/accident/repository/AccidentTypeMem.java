package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentTypeMem {
    private final AtomicInteger id = new AtomicInteger(1);
    private final HashMap<Integer, AccidentType> types = new HashMap<>();

    public AccidentTypeMem() {
        save(AccidentType.of(1, "Две машины"));
        save(AccidentType.of(2, "Машина и человек"));
        save(AccidentType.of(3, "Машина и велосипед"));
    }

    public void save(AccidentType type) {
        type.setId(id.getAndIncrement());
        types.putIfAbsent(type.getId(), type);
    }

    public void update(AccidentType type) {
        types.replace(type.getId(), type);
    }

    public AccidentType deleteById(int elId) {
        return types.remove(elId);
    }

    public AccidentType getById(int id) {
        return types.get(id);
    }

    public Map<Integer, AccidentType> getAll() {
        return Collections.unmodifiableMap(types);
    }
}
