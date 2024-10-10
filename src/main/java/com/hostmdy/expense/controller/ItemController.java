package com.hostmdy.expense.controller;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import com.hostmdy.expense.model.Item;
import com.hostmdy.expense.model.ItemDAO;
import com.hostmdy.expense.model.Mode;
import com.hostmdy.expense.model.User;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/item")
public class ItemController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/living_app")
	private DataSource dataSource;
	
	private ItemDAO itemDAO;
	
	private User user;
	
	@Override
	public void init() throws ServletException {
		itemDAO = new ItemDAO(dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		user = (User) session.getAttribute("user");
		
		String param = req.getParameter("mode");
		Mode mode = null;
		
		if(param == null) {
			mode = Mode.LIST;
		} else {
			mode = Mode.valueOf(param);
		}
		
		switch (mode) {
		case LIST:
			showAllList(req, resp);
			break;

		case SINGLE:
			showItem(req, resp);
			break;
			
		case ITEM_FORM:
			showNewItemForm(req, resp);
			break;
			
		case CREATE:
			createItem(req, resp);
			break;
			
		case LOAD:
			loadItem(req, resp);
			break;
			
		case UPDATE:
			updateItem(req, resp);
			break;
			
		case DELETE:
			deleteItem(req, resp);
			break;
			
		case SEARCH:
			searchItems(req, resp);
			break;

		default:
			showAllList(req, resp);
			break;
		}
	}
	
	private void searchItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String query = req.getParameter("query");
		List<Item> itemList = itemDAO.filterItem(query, user.getId());
		req.setAttribute("itemList", itemList);
		req.setAttribute("user", user);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/home.jsp");
		dispatcher.forward(req, resp);
		
	}

	private void showAllList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Item> itemList = itemDAO.getAllItemsByUser(user.getId());
		req.setAttribute("itemList", itemList);
		req.setAttribute("user", user);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/home.jsp");
		dispatcher.forward(req, resp);
	}
	
	private void showItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long itemId = Long.parseLong(req.getParameter("itemId"));
		Item item = itemDAO.getAllItemsByID(itemId);
		req.setAttribute("item", item);
		req.setAttribute("user", user);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/item/item-details.jsp");
		dispatcher.forward(req, resp);
	}
	
	private void showNewItemForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("user", user);
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/item/add-item.jsp");
		dispatcher.forward(req, resp);
	}
	
	private void createItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String category = req.getParameter("category");
		Double price = Double.parseDouble(req.getParameter("price"));
		Integer quantity = Integer.parseInt(req.getParameter("quantity"));
		Boolean essential = Boolean.parseBoolean(req.getParameter("essential"));
		String image = req.getParameter("image");
		String description = req.getParameter("description");
		
		Item item = new Item(title, category, price, quantity, essential, description, image, user.getId());
		req.setAttribute("insertOk", itemDAO.createItem(item));
		showNewItemForm(req, resp);	
		
	}
	
	private void loadItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long itemId = Long.parseLong(req.getParameter("itemId"));
		Item item = itemDAO.getAllItemsByID(itemId);
		req.setAttribute("item", item);
		req.setAttribute("user", user);
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/item/update-item.jsp");
		dispatcher.forward(req, resp);
	}
	
	private void updateItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("itemId"));
		String title = req.getParameter("title");
		String category = req.getParameter("category");
		Double price = Double.parseDouble(req.getParameter("price"));
		Integer quantity = Integer.parseInt(req.getParameter("quantity"));
		Boolean essential = Boolean.parseBoolean(req.getParameter("essential"));
		String image = req.getParameter("image");
		String description = req.getParameter("description");
		
		Item item = new Item(id, title, category, price, quantity, essential, description, image);
		
		boolean updateOk = itemDAO.updateItem(item);
		if(updateOk) {
			resp.sendRedirect("item?mode=SINGLE&itemId=" + id);
		} else {
			req.setAttribute("updateOk", updateOk);
			req.setAttribute("item", item);
			RequestDispatcher dispatcher = req.getRequestDispatcher("template/item/update-item.jsp");
			dispatcher.forward(req, resp);
		}		
		
	}
	
	private void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long itemId = Long.parseLong(req.getParameter("itemId"));
		boolean deleteOk = itemDAO.deleteItem(itemId);
		
		if(deleteOk) {
			resp.sendRedirect("item");
		} else {
			resp.sendRedirect("item?mode=SINGLE&itemId=" + itemId);
		}
		
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
