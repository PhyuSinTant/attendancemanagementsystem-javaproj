package com.code2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.code2.dao.UserDAO;
import com.code2.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name="jdbc/AttendanceDemo")
    private DataSource dataSource;
	private UserDAO userDAO;
	
    public LoginServlet() {
        super();
        
    }

    @Override
    	public void init() throws ServletException {
    		userDAO = new UserDAO(dataSource);
    	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		//int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("username");
		String psw = request.getParameter("password");
		try {
			User user = userDAO.getUserByUsernameAndPassword(name, psw);
			if(user!=null && user.getRole().equals("STUDENT")) {
				HttpSession session = request.getSession();
				session.setAttribute("studentUser", user);
				response.sendRedirect(request.getContextPath()+"/student");
			}
			else if(user!=null && user.getRole().equals("TEACHER")) {
				HttpSession session = request.getSession();
				session.setAttribute("teacherUser", user);
				
				response.sendRedirect(request.getContextPath()+"/teacherAccount");
			}
			else if(user!=null && user.getRole().equals("ADMIN")) {
				HttpSession session = request.getSession();
				session.setAttribute("adminUser", user);
				
				 response.sendRedirect(request.getContextPath()+"/admin");
				
			}
			else{
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				out.println("<font color=red><h1>Either username or password is wrong.</h1></font>");
				dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}
