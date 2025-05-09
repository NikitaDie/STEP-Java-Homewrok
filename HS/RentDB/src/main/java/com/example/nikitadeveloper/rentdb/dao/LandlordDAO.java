package com.example.nikitadeveloper.rentdb.dao;

import com.example.nikitadeveloper.rentdb.entity.Landlord;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LandlordDAO {
    private final SessionFactory sessionFactory;

    public LandlordDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Landlord> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Landlord l left join fetch l.apartments", Landlord.class)
                .getResultList();
        }
    }

    public Optional<Landlord> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.createQuery("from Landlord l left join fetch l.apartments where l.id = :id", Landlord.class)
                .setParameter("id", id)
                .uniqueResult());
        }
    }

    public void save(Landlord landlord) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            if (landlord.getId() == null) {
                session.persist(landlord);
            } else {
                session.merge(landlord);
            }
            session.getTransaction().commit();
        }
    }

    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Landlord landlord = session.get(Landlord.class, id);
            if (landlord != null) {
                session.remove(landlord);
            }
            session.getTransaction().commit();
        }
    }
}