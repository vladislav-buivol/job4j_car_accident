package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleMem;

import java.util.ArrayList;
import java.util.List;

@Service
public class RuleService {
    private final RuleMem mem;

    public RuleService(RuleMem ruleMem) {
        this.mem = ruleMem;
    }

    public void save(Rule rule) {
        if (mem.getById(rule.getId()) == null) {
            mem.save(rule);
        } else {
            mem.update(rule);
        }
    }

    public Rule deleteById(int elId) {
        return mem.deleteById(elId);
    }

    public Rule findById(int id) {
        return mem.getById(id);
    }

    public List<Rule> getAll() {
        return new ArrayList<>(mem.getAll().values());
    }
}
