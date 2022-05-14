package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class RuleMem {
    private final AtomicInteger id = new AtomicInteger(1);
    private final HashMap<Integer, Rule> rules = new HashMap<>();

    public RuleMem() {
        save(Rule.of(1, "Статья. 1"));
        save(Rule.of(2, "Статья. 2"));
        save(Rule.of(3, "Статья. 3"));
    }

    public void save(Rule rule) {
        rule.setId(id.getAndIncrement());
        rules.putIfAbsent(rule.getId(), rule);
    }

    public void update(Rule rule) {
        rules.replace(rule.getId(), rule);
    }

    public Rule deleteById(int elId) {
        return rules.remove(elId);
    }

    public Rule getById(int id) {
        return rules.get(id);
    }

    public Map<Integer, Rule> getAll() {
        return Collections.unmodifiableMap(rules);
    }
}
