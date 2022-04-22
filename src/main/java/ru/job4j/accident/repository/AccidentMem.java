package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final AtomicInteger id = new AtomicInteger(0);
    private HashMap<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        add(new Accident(id.get(), "Accident" + id.get(), "Desc", "Address"));
        add(new Accident(id.get(), "Accident" + id.get(), "Desc", "Address"));
        add(new Accident(id.get(), "Accident" + id.get(), "Desc", "Address"));
    }

    public void add(Accident accident) {
        accidents.putIfAbsent(id.getAndIncrement(), accident);
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
