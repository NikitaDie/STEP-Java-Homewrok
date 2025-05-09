package com.example.nikitadeveloper.rentdb.dao;

import com.example.nikitadeveloper.rentdb.entity.Rental;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class RentalDAO {
    private final SessionFactory sessionFactory;

    public RentalDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Rental> foundAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Rental r left join fetch r.client left join fetch r.apartment", Rental.class)
                .getResultList();
        }
    }

    public Optional<Rental> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.createQuery("from Rental r left join fetch r.client left join fetch r.apartment where r.id = :id", Rental.class)
                .setParameter("id", id)
                .uniqueResult());
        }
    }

    public void save(Rental rental) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(rental);
            session.getTransaction().commit();
        }
    }

    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Rental rental = session.get(Rental.class, id);
            if (rental != null) {
                session.delete(rental);
            }
            session.getTransaction().commit();
        }
    }
}