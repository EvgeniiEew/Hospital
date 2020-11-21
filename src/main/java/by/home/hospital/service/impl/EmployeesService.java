//package by.home.hospital.service.impl;
//
//import by.home.hospital.service.EmployeesRepository;
//import by.home.hospital.service.HibernateUtil;
//import lombok.AccessLevel;
//import lombok.NoArgsConstructor;
//import org.hibernate.Session;
//
//import javax.persistence.EntityManager;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import java.util.List;
//
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
//public class EmployeesService implements EmployeesRepository {
//
//	private static EmployeesRepository employeesService;
//
//	public static EmployeesRepository getService() {
//		if (employeesService == null) {
//			employeesService = new EmployeesService();
//		}
//		return employeesService;
//	}
//
//	@Override
//	public void addEmployees(Employee employee) {
//		Session entityManager = HibernateUtil.getEntityManager().getCurrentSession();
//
//		entityManager.getTransaction().begin();
//		entityManager.persist(employee);
//		entityManager.getTransaction().commit();
//
//	}
//
//	@Override
//	public List<Employee> getEmployees() {
//
//		EntityManager entityManager = HibernateUtil.getEntityManager().createEntityManager();
//
//		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//
//		CriteriaQuery<Employee> cr = cb.createQuery(Employee.class);
//
//		return entityManager.createQuery(cr.select(cr.from(Employee.class))).getResultList();
//
//	}
//
//	@Override
//	public void deleteEmployees(Integer number) {
//
//		EntityManager entityManager = HibernateUtil.getEntityManager().createEntityManager();
//
//		entityManager.getTransaction().begin();
//		entityManager.remove(new Employee());
//		entityManager.getTransaction().commit();
//
//	}
//}
