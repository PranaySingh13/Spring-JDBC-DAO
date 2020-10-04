package com.gk.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gk.dto.Student;
import com.gk.factory.StudentServiceFactory;
import com.gk.service.StudentService;

/*
 * This annotation are coming in deployment descriptor version 4 for direct calling .
 */
/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("*.do")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String req_path = request.getRequestURI();
		System.out.println("Request URI taken by controller: " + req_path);

		if (req_path.endsWith("add.do")) {
			String sid = request.getParameter("sid");//To get the parameter from JSPs we can get it from HttpServletRequest object.
			String sname = request.getParameter("sname");
			String saddr = request.getParameter("saddr");
			Student student = new Student();
			student.setSid(sid);
			student.setSname(sname);
			student.setSaddr(saddr);
			StudentService studentService = StudentServiceFactory.getStudentService();
			String status = studentService.addStudent(student);
			RequestDispatcher requestDispatcher = null;
			if (status.equals("success")) {
				requestDispatcher = request.getRequestDispatcher("success.html");
				requestDispatcher.forward(request, response);
			}
			if (status.equals("failure")) {
				requestDispatcher = request.getRequestDispatcher("failure.html");
				requestDispatcher.forward(request, response);
			}
			if (status.equals("existed")) {
				requestDispatcher = request.getRequestDispatcher("existed.html");
				requestDispatcher.forward(request, response);
			}
		}
		
		if (req_path.endsWith("search.do")) {
			String sid = request.getParameter("sid");
			StudentService studentService = StudentServiceFactory.getStudentService();
			Student student = studentService.searchStudent(sid);
			RequestDispatcher requestDispatcher = null;
			if (student == null) {
				requestDispatcher = request.getRequestDispatcher("notexisted.html");
				requestDispatcher.forward(request, response);
			} else {
				request.setAttribute("std", student);
				requestDispatcher = request.getRequestDispatcher("display.jsp");
				requestDispatcher.forward(request, response);
			}
		}
		
		if (req_path.endsWith("delete.do")) {
			String sid = request.getParameter("sid");
			StudentService studentService = StudentServiceFactory.getStudentService();
			String status = studentService.deleteStudent(sid);
			RequestDispatcher requestDispatcher = null;
			if (status.equals("success")) {
				requestDispatcher = request.getRequestDispatcher("success.html");
				requestDispatcher.forward(request, response);
			}
			if (status.equals("failure")) {
				requestDispatcher = request.getRequestDispatcher("success.html");
				requestDispatcher.forward(request, response);
			}
			if (status.equals("notexisted")) {
				requestDispatcher = request.getRequestDispatcher("notexisted.html");
				requestDispatcher.forward(request, response);
			}
		}
	}

}
