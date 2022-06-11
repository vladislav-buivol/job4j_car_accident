package ru.job4j.accident.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.List;

public class AccidentTypeHbm implements Hbm{
    private final SessionFactory sf;

    public AccidentTypeHbm(SessionFactory sf) {
        this.sf = sf;
    }

    public AccidentType save(AccidentType accidentType) {
        return execute(session -> {
            session.save(accidentType);
            return accidentType;
        }, sf.openSession());
    }

    public List<AccidentType> getAll() {
        return execute(session -> session
                .createQuery("from AccidentType", AccidentType.class)
                .list(), sf.openSession());
    }

    public AccidentType getById(int id) {
        return execute(session ->
                        session.get(AccidentType.class, id)
                , sf.openSession());
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

    public void update(AccidentType accidentType) {
        execute(session -> session.merge(accidentType), sf.openSession());
    }
}
