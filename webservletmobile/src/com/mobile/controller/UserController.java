package com.mobile.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobile.dao.UserDao;
import com.mobile.model.User;
import com.mobile.service.MobileService;
import com.mobile.service.MobileServiceImpl;

public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/user.jsp";
    private static String LIST_USER = "/listUser.jsp";
    private UserDao dao;

    public UserController() {
        super();
        dao = new UserDao();
        MobileService = new MobileServiceImpl();
    }
    
    private MobileService MobileService;
	

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int userId = Integer.parseInt(request.getParameter("userId"));
            dao.deleteUser(userId);
            forward = LIST_USER;
            request.setAttribute("users", dao.getAllUsers());    
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int userId = Integer.parseInt(request.getParameter("userId"));
            User user = dao.getUserById(userId);
            request.setAttribute("user", user);
        } else if (action.equalsIgnoreCase("listUser")){
            forward = LIST_USER;
            request.setAttribute("users", dao.getAllUsers());
        }else if (action.equalsIgnoreCase("mobile")){
        		forward = "/mobile.jsp";
        		request.setAttribute("users", dao.getAllUsers());
            	//indexPageInfo(request, response);
        		//RequestDispatcher view = request.getRequestDispatcher("/mobile.jsp");
	            request.setAttribute("latestMobilesList", this.MobileService.getLatestMobile());
	            request.setAttribute("topBrandMobilesList", this.MobileService.getTopBrandMobileList());
	            request.setAttribute("upcomingMobilesList", this.MobileService.getUpcomingMobileList());
         //   view.forward(request, response);
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        try {
            Date dob = new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("dob"));
            user.setDob(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setEmail(request.getParameter("email"));
        String userid = request.getParameter("userid");
        if(userid == null || userid.isEmpty())
        {
            dao.addUser(user);
        }
        else
        {
            user.setUserid(Integer.parseInt(userid));
            dao.updateUser(user);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
        request.setAttribute("users", dao.getAllUsers());
        view.forward(request, response);
    }
    
    public void indexPageInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	RequestDispatcher view = request.getRequestDispatcher("/mobile.jsp");
        request.setAttribute("latestMobilesList", this.MobileService.getLatestMobile());
        request.setAttribute("topBrandMobilesList", this.MobileService.getTopBrandMobileList());
        request.setAttribute("upcomingMobilesList", this.MobileService.getUpcomingMobileList());
        view.forward(request, response);
}
}