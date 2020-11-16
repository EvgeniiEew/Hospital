package by.home.hospiital;

import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JstlServlet1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //	save();


        //UserRepository service = HibernateUserService.getService();

        //	List<OldUser> users = service.getUsers();

        //req.setAttribute("users", users);

        getServletContext().getRequestDispatcher("/jstl1.jsp").forward(req, resp);
    }

//	private void save() {
//		Session entityManager = HibernateUtil.getEntityManager().getCurrentSession();
//
//		entityManager.getTransaction().begin();
//
//
//		Credentials creds1 = new Credentials();
//		creds1.setActive(true);
//		creds1.setCreateDate(new Date());
//		creds1.setPassword("SomePass");
//
//		entityManager.persist(creds1);
//
//		Credentials creds2 = new Credentials();
//		creds2.setActive(false);
//		creds2.setCreateDate(new Date());
//		creds2.setPassword("SomePass");
//		entityManager.persist(creds2);
//
//
//		User user = new User();
//		user.setEmail("bla@bla.bla");
//		user.setLastName("adgha");
//		user.setLogin("1234567");
//		user.setRole(Role.ADMIN);
//
//		ArrayList<Credentials> arrayList = new ArrayList<Credentials>();
//		arrayList.add(creds1);
//		arrayList.add(creds2);
//
//		user.setCredentials(arrayList);
//		entityManager.persist(user);
//
//		creds1.setOwnerUser(user);
//		creds2.setOwnerUser(user);
//
//		entityManager.persist(creds1);
//		entityManager.persist(creds2);
//
//		entityManager.getTransaction().commit();
//
//	}

}
