package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final AtomicInteger id = new AtomicInteger(1);
    private final HashMap<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        save(new Accident(id.get(), "Accident" + id.get(), "Desc", "Address"));
        save(new Accident(id.get(), "Accident" + id.get(), "Desc", "Address"));
        save(new Accident(id.get(), "Accident" + id.get(), "Desc", "Address"));
    }

    public void save(Accident accident) {
        accident.setId(id.getAndIncrement());
        accidents.putIfAbsent(accident.getId(), accident);
    }

    public void update(Accident accident) {
        accidents.replace(accident.getId(), accident);
    }

    public Accident deleteById(int elId) {
        return accidents.remove(elId);
    }

    public Accident getById(int id) {
        return accidents.get(id);
    }

    public Map<Integer, Accident> getAll() {
        return Collections.unmodifiableMap(accidents);
    }
}
