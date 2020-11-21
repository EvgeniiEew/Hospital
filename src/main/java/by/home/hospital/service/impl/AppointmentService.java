//package by.home.hospital.service.impl;
//
//import by.home.hospital.domain.Appointment;
//import by.home.hospital.service.AppointmentRepository;
//import by.home.hospital.service.HibernateUtil;
//import org.hibernate.Session;
//
//import javax.persistence.EntityManager;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import java.util.List;
//
//public class AppointmentService implements AppointmentRepository {
//    private static AppointmentRepository appointmentService;
//
//    public static AppointmentRepository getService() {
//        if (appointmentService == null) {
//            appointmentService = new AppointmentService();
//        }
//        return appointmentService;
//    }
//
//    @Override
//    public void addAppointment(Appointment appointment) {
//        Session entityManager = HibernateUtil.getEntityManager().getCurrentSession();
//
//        entityManager.getTransaction().begin();
//        entityManager.persist(appointment);
//        entityManager.getTransaction().commit();
//
//    }
//
//    @Override
//    public List<Appointment> getAppointment() {
//
//        EntityManager entityManager = HibernateUtil.getEntityManager().createEntityManager();
//
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//
//        CriteriaQuery<Appointment> cr = cb.createQuery(Appointment.class);
//
//        return entityManager.createQuery(cr.select(cr.from(Appointment.class))).getResultList();
//
//    }
//
//    @Override
//    public void deleteAppointment(Integer number) {
//
//        EntityManager entityManager = HibernateUtil.getEntityManager().createEntityManager();
//
//        entityManager.getTransaction().begin();
//        entityManager.remove(new Appointment());
//        entityManager.getTransaction().commit();
//
//    }
//}
