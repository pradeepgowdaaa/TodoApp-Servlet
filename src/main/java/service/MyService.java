package service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyDao;
import dto.Customer;
import dto.Task;
import helper.AES;

public class MyService {

	public void saveUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		long mobile = Long.parseLong(req.getParameter("mobile"));
		LocalDate dob = LocalDate.parse(req.getParameter("dob"));

		int age = LocalDate.now().getYear() - dob.getYear();

		if (age < 18) {
			resp.getWriter().print(
					"<h1 align='center' style='color:red'>Sorry! You are not eligible for Creating Account</h1>");
			req.getRequestDispatcher("Signup.html").include(req, resp);
		} else {
			MyDao dao = new MyDao();
			Customer customer2 = dao.findByEmail(email);

			if (customer2 == null) {
				Customer customer = new Customer();
				customer.setName(name);
				customer.setAge(age);
				customer.setDob(dob);
				customer.setEmail(email);
				customer.setGender(gender);
				customer.setMobile(mobile);
				customer.setPassword(AES.encrypt(password, "123"));

				dao.saveCustomer(customer);

				resp.getWriter().print("<h1 align='center' style='color:green'>Account Created Success </h1>");
				req.getRequestDispatcher("Login.html").include(req, resp);
			} else {
				resp.getWriter().print("<h1 align='center' style='color:red'>Account Already Exists with the Email : "
						+ email + " </h1>");
				req.getRequestDispatcher("Signup.html").include(req, resp);
			}
		}

	}

	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		MyDao dao = new MyDao();
		Customer customer = dao.findByEmail(email);
		if(customer==null)
		{
			resp.getWriter().print("<h1 align='center' style='color:red'>Invalid Email</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		}
		else {
			if(password.equals(AES.decrypt(customer.getPassword(),"123")))
			{
				resp.getWriter().print("<h1 align='center' style='color:green'>Login Success</h1>");
				req.getRequestDispatcher("Home.jsp").include(req, resp);
			}
			else {
				resp.getWriter().print("<h1 align='center' style='color:red'>Invalid Password</h1>");
				req.getRequestDispatcher("Login.html").include(req, resp);
			}
		}
	}

	public void addTask(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String name=req.getParameter("tname");
		String description=req.getParameter("tdescription");
		
		Task task=new Task();
		task.setName(name);
		task.setDescription(description);
		task.setCreatedTime(LocalDateTime.now());
		
		MyDao dao=new MyDao();
		dao.saveTask(task);
		
		resp.getWriter().print("<h1 align='center' style='color:green'>Task Saved Success</h1>");
		req.getRequestDispatcher("Home.jsp").include(req, resp);
	}
}
