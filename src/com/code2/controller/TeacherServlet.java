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

import com.code2.dao.TeacherDAO;
import com.code2.model.Teacher;
import com.code2.model.User;

/**
 * Servlet implementation class TeacherServlet
 */
@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/AttendanceDemo")
	private DataSource dataSource;
	private TeacherDAO teacherDAO;

	public TeacherServlet() {

	}

	public void init() throws ServletException {
		// ProductDAO product = new ProductDAO(dataSource);
		teacherDAO = new TeacherDAO(dataSource);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User Adminuser = (User) session.getAttribute("adminUser");
		

		if(Adminuser!=null) {
			
		String command = request.getParameter("command");
		if (command == null) {
			command = "LIST";
		}
		switch (command) {
		case "LIST":
			showTeacherList(request, response);
			break;

		case "SAVE":
			saveTeacher(request, response);
			break;

		case "ADD":
			RequestDispatcher dispatcher2 = request.getRequestDispatcher("addTeacher.jsp");
			dispatcher2.forward(request, response);
			break;

		case "DELETE":
			deleteTeacher(request, response);
			break;

		case "EDIT":
			showEditTeacher(request, response);
			break;
			
		case "UPDATE":
			updateTeacher(request, response);
			break;

		}
		}
		
	}

	private void updateTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("teacherName");
		String imgUrl = request.getParameter("imgUrl");
		String dept = request.getParameter("department");
		try {
			Teacher teacher = teacherDAO.getTeacherById(id);
			teacher.setId(id);
			teacher.setName(name);
			teacher.setImg_url(imgUrl);
			teacher.setDepartment(dept);
			teacherDAO.updateTeacher(teacher);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/teachers");
	}

	private void showEditTeacher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			Teacher teacher = teacherDAO.getTeacherById(id);
			request.setAttribute("teacher", teacher);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("edit-teacher.jsp");
		dispatcher.forward(request, response);
	}

	private void deleteTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			teacherDAO.deleteTeacher(id);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/teachers");

	}

	private void showTeacherList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Teacher> teacherList = teacherDAO.getTeachers();
			//System.out.println(teacherList);
			request.setAttribute("teachers", teacherList);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("view-teacher-list.jsp");
		dispatcher.forward(request, response);
	}

	private void saveTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String name = request.getParameter("teacherName");
		String imgUrl = request.getParameter("imgUrl");
		String dept = request.getParameter("department");
		Teacher teacher = new Teacher(name, imgUrl, dept);
		try {
//			System.out.println(teacher.getName());
//			System.out.println(teacher.getDepartment());
//			System.out.println(teacher.getImg_url());
			teacherDAO.saveTeacher(teacher);

			//System.out.println("saved.");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/teachers");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
