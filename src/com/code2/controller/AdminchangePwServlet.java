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
import com.code2.model.Teacher;
import com.code2.model.User;


@WebServlet("/AdminchangePwServlet")
public class AdminchangePwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Resource(name="jdbc/AttendanceDemo")
    private DataSource dataSource;
    private UserDAO userDAO;

    public AdminchangePwServlet() {
       
        
    }

    @Override
    public void init() throws ServletException {
    	userDAO = new UserDAO(dataSource);
    	
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("adminUser");

		if(user!=null) {
			
			String command = request.getParameter("command");
			if(command.equals("EDIT")) {
				int id = Integer.parseInt(request.getParameter("id"));
				//System.out.println(id);
				try {
					User u = userDAO.getUserById(id);
					request.setAttribute("User", u);
				} catch (SQLException e) {

					e.printStackTrace();
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher("admin-change-psw.jsp");
				dispatcher.forward(request, response);

		}	else {
			response.sendRedirect(request.getContextPath()+"/login");
		}
		}
		
		
		}
		
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		if(command.equals("UPDATE")) {
			int id=Integer.parseInt(request.getParameter("id"));
			System.out.println(id);
			String name = request.getParameter("username");
			String psw = request.getParameter("password");
			
			try {
				User user = userDAO.getUserById(id);
				user.setId(id);
				user.setUsername(name);
				user.setPassword(psw);
				System.out.println(user.getUsername());
				System.out.println(user.getPassword());
				userDAO.updateUser(user);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath()+"/admin");
		}
		}
	}


