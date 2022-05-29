package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.function.Function;

public class Hbm {

    public Hbm() {
    }

    public <T> T execute(Function<Session, T> command, Session session) {
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
