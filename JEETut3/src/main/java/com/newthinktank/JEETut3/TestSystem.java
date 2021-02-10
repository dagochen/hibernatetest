package com.newthinktank.JEETut3;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.function.Consumer;

public class TestSystem {
    // Create an EntityManagerFactory when you start the application
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("JEETut3");

    public static void main(String[] args) {
        final Adresse adresse = new Adresse("Hamburger Strasse", "1", 22083);
        Customer cust = new Customer();
        cust.setID(1);
        cust.setFName("Max");
        cust.setLName("Mustermann");
        cust.setAdresse(adresse);
        addCustomer(cust);

        System.out.println("----");

        getCustomers();
        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addCustomer(Customer cust) {
        // The EntityManager class allows operations such as create, read, update, delete
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        // Used to issue transactions on the EntityManager
        EntityTransaction et = null;

        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Create and set values for new customer

            // Save the adress object
            if (cust.getAdresse() != null)
                em.persist(cust.getAdresse());

            // Save the customer object
            em.persist(cust);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }
    }

    public static void getCustomer(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String query = "SELECT c FROM Customer c WHERE c.id = :custID";

        // Issue the query and get a matching Customer
        TypedQuery<Customer> tq = em.createQuery(query, Customer.class);
        tq.setParameter("custID", id);

        Customer cust = null;
        try {
            // Get matching customer object and output
            cust = tq.getSingleResult();
            System.out.println(cust.getFName() + " " + cust.getLName());
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void getCustomers() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String strQuery = "SELECT c FROM Customer c WHERE c.id IS NOT NULL";

        // Issue the query and get a matching Customer
        TypedQuery<Customer> tq = em.createQuery(strQuery, Customer.class);
        List<Customer> custs;
        try {
            // Get matching customer object and output
            custs = tq.getResultList();
            custs.forEach(new Consumer<Customer>() {
                public void accept(Customer cust) {
                    System.out.println(cust.getFName() + " " + cust.getLName());
                }
            });
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void changeFName(int id, String fname) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        Customer cust = null;

        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find customer and make changes
            cust = em.find(Customer.class, id);
            cust.setFName(fname);

            // Save the customer object
            em.persist(cust);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }
    }

    public static void deleteCustomer(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Customer cust = null;

        try {
            et = em.getTransaction();
            et.begin();
            cust = em.find(Customer.class, id);
            em.remove(cust);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }
    }
} // Right click and run this file as a Java Application