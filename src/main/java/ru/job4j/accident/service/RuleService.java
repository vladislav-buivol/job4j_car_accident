package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RuleService {
    private final RuleRepository ruleRepo;

    public RuleService(RuleRepository ruleRepo) {
        this.ruleRepo = ruleRepo;
    }

    public void save(Rule rule) {
        ruleRepo.save(rule);
    }

    public void deleteById(int elId) {
        ruleRepo.deleteById(elId);
    }

    public Rule findById(int id) {
        Optional<Rule> rule = ruleRepo.findById(id);
        return rule.orElse(new Rule());
    }

    public List<Rule> getAll() {
        return new ArrayList<>(ruleRepo.findAll());
    }
}
