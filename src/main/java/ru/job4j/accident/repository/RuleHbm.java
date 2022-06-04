package ru.job4j.accident.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.List;

@Repository
public class RuleHbm implements Hbm {
    private final SessionFactory sf;

    public RuleHbm(SessionFactory sf) {
        this.sf = sf;
    }

    public Rule save(Rule rule) {
        return execute(session -> {
            session.save(rule);
            return rule;
        }, sf.openSession());
    }

    public List<Rule> getAll() {
        return execute(session -> session
                .createQuery("from Rule", Rule.class)
                .list(), sf.openSession());
    }

    public Rule getById(int id) {
        return execute(session -> session.get(Rule.class, id), sf.openSession());
    }

    public boolean deleteById(int elId) {
        return execute(session -> {
            try {
                session.delete(getById(elId));
                return true;
            } catch (Exception e) {
                return false;
            }
        }, sf.openSession());
    }

    public void update(Rule rule) {
        execute(session -> session.merge(rule), sf.openSession());
    }
}
