package com.example.nikitadeveloper.rentdb.dao;

import com.example.nikitadeveloper.rentdb.entity.Client;
import com.example.nikitadeveloper.rentdb.entity.dto.ClientSearchDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ClientDAO {
    private final SessionFactory sessionFactory;

    public ClientDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Client client) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(client);
    }

    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Client client = session.get(Client.class, id);
        if (client != null) {
            session.delete(client);
        }
    }

    public Client findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Client.class, id);
    }

    public List<Client> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Client", Client.class).list();
    }

    public List<Client> search(ClientSearchDTO searchDTO) {
        Session session = sessionFactory.getCurrentSession();
        StringBuilder hql = new StringBuilder("from Client where 1=1");
        if (searchDTO.getFullName() != null && !searchDTO.getFullName().isEmpty()) {
            hql.append(" and fullName like :fullName");
        }
        if (searchDTO.getContactPhone() != null && !searchDTO.getContactPhone().isEmpty()) {
            hql.append(" and contactPhone like :contactPhone");
        }
        if (searchDTO.getDesiredRooms() != null) {
            hql.append(" and desiredRooms = :desiredRooms");
        }
        if (searchDTO.getDesiredDistrict() != null && !searchDTO.getDesiredDistrict().isEmpty()) {
            hql.append(" and desiredDistrict like :desiredDistrict");
        }
        if (searchDTO.getDesiredPrice() != null) {
            hql.append(" and desiredPrice <= :desiredPrice");
        }

        Query<Client> query = session.createQuery(hql.toString(), Client.class);
        if (searchDTO.getFullName() != null && !searchDTO.getFullName().isEmpty()) {
            query.setParameter("fullName", "%" + searchDTO.getFullName() + "%");
        }
        if (searchDTO.getContactPhone() != null && !searchDTO.getContactPhone().isEmpty()) {
            query.setParameter("contactPhone", "%" + searchDTO.getContactPhone() + "%");
        }
        if (searchDTO.getDesiredRooms() != null) {
            query.setParameter("desiredRooms", searchDTO.getDesiredRooms());
        }
        if (searchDTO.getDesiredDistrict() != null && !searchDTO.getDesiredDistrict().isEmpty()) {
            query.setParameter("desiredDistrict", "%" + searchDTO.getDesiredDistrict() + "%");
        }
        if (searchDTO.getDesiredPrice() != null) {
            query.setParameter("desiredPrice", searchDTO.getDesiredPrice());
        }

        return query.list();
    }

    public List<Client> findByRecentRentals() {
        Session session = sessionFactory.getCurrentSession();
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        Query<Client> query = session.createQuery(
            "select c from Client c join c.rentals r where r.startDate >= :oneMonthAgo",
            Client.class
        );
        query.setParameter("oneMonthAgo", oneMonthAgo);
        return query.list();
    }

    public List<Client> findByUpcomingEndRentals() {
        Session session = sessionFactory.getCurrentSession();
        LocalDate oneMonthFromNow = LocalDate.now().plusMonths(1);
        Query<Client> query = session.createQuery(
            "select c from Client c join c.rentals r where r.endDate <= :oneMonthFromNow and r.endDate >= :now",
            Client.class
        );
        query.setParameter("oneMonthFromNow", oneMonthFromNow);
        query.setParameter("now", LocalDate.now());
        return query.list();
    }

    public List<Client> findByShortAverageRental() {
        Session session = sessionFactory.getCurrentSession();
        Query<Client> query = session.createQuery(
            "select c from Client c join c.rentals r " +
                "group by c having avg(datediff(r.endDate, r.startDate)) < 30",
            Client.class
        );
        return query.list();
    }

    public List<Client> findByLongAverageRental() {
        Session session = sessionFactory.getCurrentSession();
        Query<Client> query = session.createQuery(
            "select c from Client c join c.rentals r " +
                "group by c having avg(datediff(r.endDate, r.startDate)) > 365",
            Client.class
        );
        return query.list();
    }
}
