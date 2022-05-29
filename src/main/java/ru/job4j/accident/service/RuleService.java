package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleHbm;

import java.util.ArrayList;
import java.util.List;

@Service
public class RuleService {
    private final RuleHbm ruleHbm;

    public RuleService(RuleHbm ruleHbm) {
        this.ruleHbm = ruleHbm;
    }

    public void save(Rule rule) {
        if (ruleHbm.getById(rule.getId()) == null) {
            ruleHbm.save(rule);
        } else {
            ruleHbm.update(rule);
        }
    }

    public boolean deleteById(int elId) {
        return ruleHbm.deleteById(elId);
    }

    public Rule findById(int id) {
        return ruleHbm.getById(id);
    }

    public List<Rule> getAll() {
        return new ArrayList<>(ruleHbm.getAll());
    }
}
