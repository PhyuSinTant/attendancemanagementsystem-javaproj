package com.code2.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.swing.text.StyledEditorKit.BoldAction;

import com.code2.dao.UserDAO;
import com.code2.dao.infoDAO;
import com.code2.dao.studentDAO;
import com.code2.model.Student;

/**
 * Servlet implementation class oop
 */
@WebServlet("/oop")
public class oop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/AttendanceDemo")
    private DataSource dataSource;
    private UserDAO userDAO;
    private infoDAO infoDAO;
    private studentDAO sDAO;
    public oop() {
        super();
      
    }
    @Override
    public void init() throws ServletException {
    	userDAO = new UserDAO(dataSource);
    	infoDAO = new infoDAO(dataSource);
    	sDAO = new studentDAO(dataSource);
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		String major = request.getParameter("major");
		String year = request.getParameter("year");
		String sub = request.getParameter("sub");
		

		String stuId[] = request.getParameterValues("studentID");
		for(String id:stuId) {
			list1.add(id);
		}
		//System.out.println(list);
		Boolean isWhatever = Boolean.parseBoolean(request.getParameter("check"));
		System.out.println(isWhatever);
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
