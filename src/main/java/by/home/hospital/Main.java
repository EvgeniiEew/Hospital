package by.home.hospital;

import by.home.hospital.service.HibernateUtil;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        Session entityManager = HibernateUtil.getEntityManager().getCurrentSession();
        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();
        System.out.println("x");
    }
}
