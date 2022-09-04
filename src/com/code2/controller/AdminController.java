package com.code2.controller;

import java.io.IOException;
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


@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   @Resource(name="jdbc/AttendanceDemo")
   private DataSource dataSource;
   private UserDAO userDAO;
   
    public AdminController() {
       
    }

	@Override
	public void init() throws ServletException {
		userDAO = new UserDAO(dataSource);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("adminUser");
		if(user!=null) {
			 	int id = user.getId();
			 	request.setAttribute("id", id);
				RequestDispatcher dispatcher =  request.getRequestDispatcher("admin.jsp");
				dispatcher.forward(request, response);
			}
		else {
			response.sendRedirect(request.getContextPath()+"/login");
		}
		}
		
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
