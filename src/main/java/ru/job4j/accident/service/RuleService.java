package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleJdbcTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class RuleService {
    private final RuleJdbcTemplate ruleJdbc;

    public RuleService(RuleJdbcTemplate ruleMem) {
        this.ruleJdbc = ruleMem;
    }

    public void save(Rule rule) {
        if (ruleJdbc.getById(rule.getId()) == null) {
            ruleJdbc.save(rule);
        } else {
            ruleJdbc.update(rule);
        }
    }

    public boolean deleteById(int elId) {
        return ruleJdbc.deleteById(elId);
    }

    public Rule findById(int id) {
        return ruleJdbc.getById(id);
    }

    public List<Rule> getAll() {
        return new ArrayList<>(ruleJdbc.getAll());
    }
}
