package com.example.nikitadeveloper.rentdb.dao;

import com.example.nikitadeveloper.rentdb.entity.Apartment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class ApartmentDAO {
    private final SessionFactory sessionFactory;

    public ApartmentDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Apartment> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Apartment a left join fetch a.landlord left join fetch a.rentals", Apartment.class)
                .getResultList();
        }
    }

    public List<Apartment> findByCriteria(String district, Integer rooms, Double price, String status) {
        try (Session session = sessionFactory.openSession()) {
            StringBuilder hql = new StringBuilder("from Apartment a left join fetch a.landlord left join fetch a.rentals where 1=1");
            if (district != null && !district.isEmpty()) {
                hql.append(" and lower(a.district) like :district");
            }
            if (rooms != null) {
                hql.append(" and a.rooms = :rooms");
            }
            if (price != null) {
                hql.append(" and a.price <= :price");
            }
            Query<Apartment> query = session.createQuery(hql.toString(), Apartment.class);
            if (district != null && !district.isEmpty()) {
                query.setParameter("district", "%" + district.toLowerCase() + "%");
            }
            if (rooms != null) {
                query.setParameter("rooms", rooms);
            }
            if (price != null) {
                query.setParameter("price", price);
            }
            List<Apartment> apartments = query.getResultList();
            // Filter by status (rented/free)
            LocalDate now = LocalDate.now();
            if (!"all".equals(status)) {
                apartments = apartments.stream()
                    .filter(a -> "rented".equals(status) == hasActiveRental(a, now))
                    .toList();
            }
            // Set activeRental for each apartment
            apartments.forEach(a -> setActiveRental(a, now));
            return apartments;
        }
    }

    public Optional<Apartment> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Apartment apartment = session.createQuery("from Apartment a left join fetch a.landlord left join fetch a.rentals where a.id = :id", Apartment.class)
                .setParameter("id", id)
                .uniqueResult();
            if (apartment != null) {
                setActiveRental(apartment, LocalDate.now());
            }
            return Optional.ofNullable(apartment);
        }
    }

    public void save(Apartment apartment) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            if (apartment.getId() == null) {
                session.persist(apartment);
            } else {
                session.merge(apartment);
            }
            session.getTransaction().commit();
        }
    }

    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Apartment apartment = session.get(Apartment.class, id);
            if (apartment != null) {
                session.remove(apartment);
            }
            session.getTransaction().commit();
        }
    }

    private boolean hasActiveRental(Apartment apartment, LocalDate now) {
        return apartment.getRentals().stream()
            .anyMatch(r -> !r.getStartDate().isAfter(now) && !r.getEndDate().isBefore(now));
    }

    private void setActiveRental(Apartment apartment, LocalDate now) {
        apartment.setActiveRental(apartment.getRentals().stream()
            .filter(r -> !r.getStartDate().isAfter(now) && !r.getEndDate().isBefore(now))
            .findFirst()
            .orElse(null));
    }
}