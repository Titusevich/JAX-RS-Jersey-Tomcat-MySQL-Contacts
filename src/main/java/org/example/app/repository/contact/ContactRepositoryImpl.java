package org.example.app.repository.contact;

import org.example.app.config.HibernateUtil;
import org.example.app.dto.ContactDtoRequest;
import org.example.app.entity.Contact;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContactRepositoryImpl implements ContactRepository {

    private static final Logger LOGGER =
            Logger.getLogger(ContactRepository.class.getName());

    @Override
    public void save(ContactDtoRequest request) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            MutationQuery mutQuery = session.createMutationQuery(
                    "INSERT INTO Contact (firstName, lastName, phoneNumber) " +
                            "VALUES (:firstName, :lastName, :phoneNumber)");
            mutQuery.setParameter("firstName", request.firstName());
            mutQuery.setParameter("lastName", request.lastName());
            mutQuery.setParameter("phoneNumber", request.phoneNumber());
            mutQuery.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    @Override
    public Optional<List<Contact>> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction;
            transaction = session.beginTransaction();
            List<Contact> list =
                    session.createQuery("FROM Contact", Contact.class).list();
            transaction.commit();
            return Optional.of(list);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Contact> getById(Long id) {
        Transaction transaction;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Contact> query =
                    session.createQuery("FROM Contact WHERE id = :id", Contact.class);
            query.setParameter("id", id);
            query.setMaxResults(1);
            Contact contact = query.uniqueResult();
            transaction.commit();
            return Optional.ofNullable(contact);

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
            return Optional.empty();
        }

    }

    @Override
    public void update(Long id, ContactDtoRequest request) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            MutationQuery mutQuery = session.createMutationQuery(
                    "UPDATE Contact SET firstName = :firstName, " +
                            "lastName = :lastName, phoneNumber = :phoneNumber " +
                            "WHERE id = :id");
            mutQuery.setParameter("firstName", request.firstName());
            mutQuery.setParameter("lastName", request.lastName());
            mutQuery.setParameter("phoneNumber", request.phoneNumber());
            mutQuery.setParameter("id", id);
            mutQuery.executeUpdate();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            MutationQuery mutQuery = session.createMutationQuery(
                    "DELETE FROM Contact WHERE id = :id");
            mutQuery.setParameter("id", id);
            mutQuery.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.log(Level.WARNING, e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean isIdExists(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Contact contact = new Contact();
            contact.setId(id);
            contact = session.find(Contact.class, contact.getId());

            if (contact != null) {
                Query<Contact> query =
                        session.createQuery("FROM Contact", Contact.class);
                query.setMaxResults(1);
                query.getResultList();
            }
            return contact != null;
        }
    }

    @Override
    public Optional<Contact> getLastEntity() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Contact> query =
                    session.createQuery("FROM Contact ORDER BY id DESC", Contact.class);
            query.setMaxResults(1);
            Contact contact = query.uniqueResult();
            transaction.commit();
            return Optional.of(contact);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.log(Level.WARNING, e.getMessage(), e);
            return Optional.empty();
        }
    }
}
