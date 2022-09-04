package com.code2.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
import com.code2.dao.infoDAO;
import com.code2.dao.studentDAO;
import com.code2.model.Info;
import com.code2.model.Student;
import com.code2.model.User;

/**
 * Servlet implementation class TeacherAccount
 */
@WebServlet("/teacherAccount")
public class TeacherAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name="jdbc/AttendanceDemo")
    private DataSource dataSource;
    private UserDAO userDAO;
    private infoDAO infoDAO;
    private studentDAO sDAO;
    public TeacherAccount() {
       
    }
    @Override
    public void init() throws ServletException {
    	userDAO = new UserDAO(dataSource);
    	infoDAO = new infoDAO(dataSource);
    	sDAO = new studentDAO(dataSource);
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("teacherUser");
		
		if(user!=null) {
			 	int id = user.getId();
			 	request.setAttribute("id", id);
			 	
			 	try {
					List<Info> info = infoDAO.getInfoById(id);
					
					request.setAttribute("Info", info);
			} catch (SQLException e) {
					
					e.printStackTrace();
				}
			 	
				RequestDispatcher dispatcher =  request.getRequestDispatcher("teacher-home.jsp");
				dispatcher.forward(request, response);
			
		}
		else {
			response.sendRedirect(request.getContextPath()+"/login");
		}
		//		HttpSession session = request.getSession();
//		User teachUser = (User) session.getAttribute("teacherUser");
//		if(teachUser.getUsername().equals("teacher1")) {
//			
//		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String major = request.getParameter("major");
		String year = request.getParameter("year");
		
		
		//System.out.println(check);
		

		//System.out.println(major);
		//System.out.println(year);
		Student students = new Student(major,year);
		try {
			List<Student> s = sDAO.getStudentsBySubMj(students);
			
			request.setAttribute("mj", major);
			request.setAttribute("yr", year);
			request.setAttribute("student", s);
			RequestDispatcher dispatcher = request.getRequestDispatcher("teacher-home.jsp");
			dispatcher.forward(request, response);
		
		//method(sub)
		
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}}
	
