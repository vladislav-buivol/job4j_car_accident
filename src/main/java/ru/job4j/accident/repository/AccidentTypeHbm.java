package ru.job4j.accident.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.List;

@Repository
public class AccidentTypeHbm {
    private final SessionFactory sf;
    private final Hbm hbm = new Hbm();

    public AccidentTypeHbm(SessionFactory sf) {
        this.sf = sf;
    }

    public AccidentType save(AccidentType accidentType) {
        return hbm.execute(session -> {
            session.save(accidentType);
            return accidentType;
        }, sf.openSession());
    }

    public List<AccidentType> getAll() {
        return hbm.execute(session -> session
                .createQuery("from AccidentType", AccidentType.class)
                .list(), sf.openSession());
    }

    public AccidentType getById(int id) {
        return hbm.execute(session ->
                        session.get(AccidentType.class, id)
                , sf.openSession());
    }

    public boolean deleteById(int elId) {
        return hbm.execute(session -> {
            try {
                session.delete(getById(elId));
                return true;
            } catch (Exception e) {
                return false;
            }
        }, sf.openSession());
    }

    public void update(AccidentType accidentType) {
        hbm.execute(session -> session.merge(accidentType), sf.openSession());
    }
}
