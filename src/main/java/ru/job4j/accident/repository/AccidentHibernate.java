package ru.job4j.accident.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.List;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;
    private final Hbm hbm = new Hbm();

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident save(Accident accident) {
        return hbm.execute(session -> {
            session.save(accident);
            return accident;
        }, sf.openSession());
    }

    public List<Accident> getAll() {
        return hbm.execute(session ->
                        session
                                .createQuery("select distinct ac from Accident ac "
                                        + "left join fetch ac.rules "
                                        + "left join fetch ac.type "
                                )
                                .list()
                , sf.openSession());
    }

    public Accident getById(int id) {
        return hbm.execute(session -> (Accident) session
                .createQuery("select distinct ac from Accident ac "
                        + "left join fetch ac.rules "
                        + "left join fetch ac.type "
                        + "where ac.id =: id"
                ).setParameter("id", id)
                .list().get(0), sf.openSession());
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

    public void update(Accident accident) {
        hbm.execute(session -> session.merge(accident), sf
                .openSession());
    }
}