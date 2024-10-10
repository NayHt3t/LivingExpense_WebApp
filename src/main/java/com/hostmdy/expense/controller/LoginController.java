package com.hostmdy.expense.controller;

import java.io.IOException;

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
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet {

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
		String param = req.getParameter("mode");
		Mode mode = null;
		
		if(param == null) {
			mode = Mode.LOGIN_FORM;
		} else {
			mode = Mode.valueOf(param);
		}
		
		switch (mode) {
		case LOGIN_FORM:
			showLoginForm(req, resp);
			break;
			
		case LOGIN:
			login(req, resp);
			break;
			
		case LOGOUT:
			logout(req, resp);
			break;

		default:
			showLoginForm(req, resp);
			break;
		}
	}

	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		boolean loginOk = userDAO.authenticate(username, password);
		
		if(loginOk) {
			User user = userDAO.getUserByUsernameOrEmail(username).get();
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			resp.sendRedirect("item");
		} else {
			req.setAttribute("loginOk", loginOk);
			showLoginForm(req, resp);
		}
	}
	
	private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		session.invalidate(); //session ထဲမှာ ရှိသမျှ attribute တွေရှင်းသွားမယ်။ 
//		session.removeAttribute("user"); //user attribute ကိုဖယ်တာ။ သုံးမယ့်အပေါ်မူတည်ပြီး 2 မျိုးသုံးလို့ရတယ်။
		resp.sendRedirect("login");
	}



	private void showLoginForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/user/sign-in.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
	
	

}
