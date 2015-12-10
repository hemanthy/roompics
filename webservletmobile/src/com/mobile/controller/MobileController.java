package com.mobile.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;
import com.mobile.dao.UserDao;
import com.mobile.exception.MobileException;
import com.mobile.model.Company;
import com.mobile.model.Mobile;
import com.mobile.model.User;
import com.mobile.service.MobileService;
import com.mobile.service.MobileServiceImpl;
import com.mobile.vo.DataMobileVO;


public class MobileController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/user.jsp";
    private static String LIST_USER = "/listUser.jsp";
    private UserDao dao;
    
    public static final Integer RAM_SIZE = 2;
    public static final Integer SCREEN_SIZE = 5;
    public static final Integer SECONDARY_CAMERA = 5;
    public static final String COMPARE_URL = "/compare/";
    
    public MobileException mobileexception;
	private Connection connection = null;
	
	Map<Integer,String> connectionMap = new HashMap<Integer,String>();
	

    public MobileController() {
        super();
        try {
        	dao = new UserDao();
		} catch (MobileException e) {
			this.mobileexception = e;
		}
        try {
			MobileService = new MobileServiceImpl();
		} catch (MobileException e) {
			this.mobileexception = e;
		}
        
    }

	private DataSource getDataSource() throws MobileException {
		InitialContext cxt = null;
		DataSource  lookup = null;
		try {
			cxt = new InitialContext();
		} catch (NamingException e) {
			e.printStackTrace();
			 throw new MobileException(e.getCause());
		}
		if ( cxt == null ) {
		 System.out.println( "Uh oh -- no context!");
		}
		
		try {
			connectionMap.put(0, "cla88inf_all6");
			connectionMap.put(1, "cla88inf_all1");
	        connectionMap.put(2, "cla88inf_all2");
	        connectionMap.put(3, "cla88inf_all3");
	        connectionMap.put(4, "cla88inf_all4");
	        connectionMap.put(5, "cla88inf_all5");
	        connectionMap.put(6, "cla88inf_all6");
	        connectionMap.put(7, "cla88inf_all7");
	        connectionMap.put(8, "cla88inf_all8");
	        connectionMap.put(9, "cla88inf_all9");
	        connectionMap.put(10, "cla88inf");
			
			Integer round = (int) Math.round(Math.random()*10);
			
			String poolVal = connectionMap.get(round);
			System.out.println("poolVal:::"+poolVal+"......"+round);
			 //lookup = (DataSource) cxt.lookup( "java:/comp/env/jdbc/cla88inf"+poolVal );
			lookup = (DataSource) cxt.lookup( "java:/comp/env/jdbc/"+poolVal );
		} catch (NamingException e) {
			e.printStackTrace();
			throw new MobileException(e);
		}if ( lookup == null ) {
			   throw new MobileException("Data source not found!");
		}
		
		return lookup;
	}
    
    private MobileService MobileService;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        String forward="/error.jsp";
        
        
        try{
        	
        		DataSource dataSource = getDataSource();
        		connection = dataSource.getConnection();
            		String mobileName = request.getParameter("q");
            		 this.MobileService.setConnection(connection);
            		 DataMobileVO dataMobileVO = this.MobileService.getMobileDetailsByQueryString(mobileName);
            		 	request.setAttribute("dataMobileVO", dataMobileVO);
            	        Gson gson = new Gson();
            	        String jsonData = gson.toJson((Object)dataMobileVO);
            	    //    System.out.println(jsonData);
            	        response.setContentType("application/json");
            	        response.getWriter().write(jsonData);
            	        response.getWriter().flush();
            			try {
            				if(connection !=null){
            					connection.close();
            				}
            			} catch (SQLException e) {
            				forward = "/error.jsp";
            	        	request.setAttribute("excep1", e.getCause());
            	        	request.setAttribute("msg1", e.getMessage());
            	        	request.setAttribute("block", "finally block111");
            				e.printStackTrace();
            			}
            	        return;
		
        }catch(Exception e){
        	System.out.println("errormsg........"+e.getMessage());
        	 forward = "/error.jsp";
        	 request.setAttribute("excep", e.getCause());
        	 request.setAttribute("msg", e.getMessage());
        	 request.setAttribute("block", "catch block");
        	 e.printStackTrace();
        }finally {
			try {
				if(connection !=null){
					connection.close();
				}
			} catch (SQLException e) {
				forward = "/error.jsp";
	        	request.setAttribute("excep1", e.getCause());
	        	request.setAttribute("msg1", e.getMessage());
	        	request.setAttribute("block", "finally block");
				e.printStackTrace();
			}
		}
        
        request.setAttribute("aaa","1234");
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
        
    }

}
