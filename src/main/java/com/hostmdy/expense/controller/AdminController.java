package com.hostmdy.expense.controller;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import com.hostmdy.expense.model.Mode;
import com.hostmdy.expense.model.User;
import com.hostmdy.expense.model.UserDAO;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin")
public class AdminController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/living_app")
	private DataSource dataSource;
	
	private UserDAO userDAO;
	
	@Override
	public void init() throws ServletException {
		userDAO = new UserDAO(dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String param = req.getParameter("mode");
		Mode mode = null;
		
		if(param == null) {
			mode = Mode.LIST;
		} else {
			mode = Mode.valueOf(param);
		}
		
		switch (mode) {
		case LIST:
			showUserList(req, resp);
			break;
			
		case ENABLE:
			enableUser(req, resp);
			break;
			
		case DISABLE:
			disableUser(req, resp);
			break;

		default:
			showUserList(req, resp);
			break;
		}
	}
	
	private void enableUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long userId = Long.parseLong(req.getParameter("userId"));
		boolean ok = userDAO.enableUser(userId);
		showUserList(req, resp);
	}

	private void disableUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long userId = Long.parseLong(req.getParameter("userId"));
		boolean ok = userDAO.disableUser(userId);
		showUserList(req, resp);
		
	}

	private void showUserList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<User> userList = userDAO.getAllUsers();
		req.setAttribute("userList", userList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/user/dashboard.jsp");
		dispatcher.forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
