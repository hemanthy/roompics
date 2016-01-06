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

public class UserController extends HttpServlet {
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
	

    public UserController() {
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
			
			String poolVal = connectionMap.get(10);
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
       // String forward="";
    	
        String forward="/error.jsp";
        String action = "";
        String parameter = request.getParameter("action");
		action  = parameter;
        if(action == null){
        	action = "";
        }
        
        action = action.toLowerCase();
        
        String requestURI = request.getRequestURI();

        
        if(requestURI.contains("UserController")){
        	requestURI = action;
        }
       
        System.out.println(requestURI);
        
        request.setAttribute("hostName", request.getServerName());
        request.setAttribute("requestURI", requestURI);
        //System.out.println("MobileService::"+this.MobileService.toString());
        try{
        	
        //	Connection conn =    DbUtil.getConnection();
        		DataSource dataSource = getDataSource();
        		connection = dataSource.getConnection();
        		this.MobileService.setConnection(connection);
        	request.setAttribute("requestURI", requestURI);
        	System.out.println("connection:::"+connection.toString());
        	
        if (action.contains("indexpage")){
        		forward = "/index1.jsp";
	            request.setAttribute("latestMobilesList", this.MobileService.getLatestMobile());
	            request.setAttribute("topBrandMobilesList", this.MobileService.getTopBrandMobileList());
	            request.setAttribute("upcomingMobilesList", this.MobileService.getUpcomingMobileList());
	            request.setAttribute("companyList", this.MobileService.getCompaniesList());
	            request.setAttribute("mobiless", "LandingPage");
         //   view.forward(request, response);
        }else if (action.contains("all-brands") || requestURI.contains("all-brands")) {
			forward = "/mobiles.jsp";
			request.setAttribute("companyList", this.MobileService.getCompaniesList());
		} else if (action.contains("/advance-search/") || requestURI.contains("/advance-search/")) {
			forward = "/advanceSearch.jsp";
			request.setAttribute("companyList", this.MobileService.getCompaniesList());
			if (requestURI.contains((CharSequence)"search")) {
				request.setAttribute("mobilesList", "Mobiles");
	            String[] urlSplit = requestURI.split("search");
	            String searchURL = urlSplit[1];
	            String[] catageoryList = searchURL.split("/");
	            List<Mobile> mobilesBySearchCatageoryList = this.MobileService.getMobilesBySearchCatageory(catageoryList);
	            if(mobilesBySearchCatageoryList.isEmpty()){
	            	//request.red
	            	searchURL = "/brand-apple";
	            	 String[] catageoryList1 = searchURL.split("/");
	 	            mobilesBySearchCatageoryList = this.MobileService.getMobilesBySearchCatageory(catageoryList1);
	 	           request.setAttribute("NotFoundMessage", "Sorry! There are no mobile matches with your criteria :) ");
	            }
	            this.constructMobelAttribute(request, catageoryList);
	            request.setAttribute("companyList", (Object)this.MobileService.getCompaniesList());
	            request.setAttribute("mobilesBySearchCatageoryList", (Object)mobilesBySearchCatageoryList);
	            request.setAttribute("url", (Object)requestURI);
	        }
		} 
		
		else if (action.contains("/advance-search") || requestURI.contains("/advance-search")) {
				forward = "/advanceSearch.jsp";
			 	request.setAttribute("ramBasedmobiles", (Object)this.MobileService.getMobilesByRamSize(RAM_SIZE));
		        request.setAttribute("screensizeBasedmobiles", (Object)this.MobileService.getMobilesByScreenSize(SCREEN_SIZE));
		        request.setAttribute("secondaryCameraBasedmobiles", (Object)this.MobileService.getMobilesBySecondaryCamera(SECONDARY_CAMERA));
		        request.setAttribute("dualSimMobiles", (Object)this.MobileService.getDualMobiles());
		        request.setAttribute("companyList", (Object)this.MobileService.getCompaniesList());
		        request.setAttribute("mobilesList", "Mobiles");
		}
		
		else if (action.contains("compare") || requestURI.contains("compare")) {
			forward = "/compare.jsp";
	        //String requestURL = request.getRequestURI().toLowerCase();
	        if (requestURI.contains((CharSequence)"-vs-")) {
	            String[] splitUrl = requestURI.split("-vs-");
	            int mobileCount = 1;
	            for (String mobileName : splitUrl) {
	                if (mobileName.contains((CharSequence)"/compare/")) {
	                    String[] mobileNameSplit = mobileName.split("/");
	                    mobileName = mobileNameSplit[mobileNameSplit.length - 1];
	                }
	                String mobileName1 = mobileName.replaceAll("-", " ");
	                request.setAttribute("mobileUrl" + mobileCount,this.MobileService.getMobileByName(mobileName1));
	                request.setAttribute("mobile" + mobileCount,this.MobileService.getMobileByName(mobileName1));
	                ++mobileCount;
	            }
	            String s1 = splitUrl[0];
	            int index1 = s1.indexOf("/compare/");
	            s1 = s1.substring("/compare/".length() + index1);
	            request.setAttribute("m1", (Object)s1);
	            if (splitUrl.length >= 2) {
	                String url = splitUrl[1];
	                request.setAttribute("m2", (Object)url);
	            }
	            if (splitUrl.length >= 3) {
	                String url = splitUrl[2];
	                request.setAttribute("m3", (Object)url);
	            }
	            if (splitUrl.length >= 4) {
	                String url = splitUrl[3];
	                request.setAttribute("m4", (Object)url);
	            }
	        } else if (requestURI.contains((CharSequence)"/compare/")) {
	            int index1 = requestURI.indexOf("/compare/");
	            String mobileName = requestURI.substring("/compare/".length() + index1);
	            String mobileName1 = mobileName.replaceAll("-", " ");
	            request.setAttribute("mobile1", (Object)this.MobileService.getMobileByName(mobileName1));
	        }
		} else if (action.contains("mobiles/") || requestURI.contains("mobiles/")) {
			String mobileName = requestURI.split("mobiles/")[1];
			if (mobileName.endsWith("upcoming-phones")) {
				request.setAttribute("availability", (Object) "upcoming-mobiles");
				request.setAttribute("mobilesBySearchCatageoryList",
						(Object) this.MobileService.getUpcomingMobileList());
				request.setAttribute("companyList", (Object) this.MobileService.getCompaniesList());
				request.setAttribute("mobilesList", "Upcoming Mobile Phones");
				request.setAttribute("mobilesListdesc", "Find upcoming mobile phones with specifications, features and different brands.");
				forward = "/advanceSearch.jsp";
			}else if (mobileName.contains((CharSequence) "screen-size")) {
				request.setAttribute("screensize", (Object) "5.0-6.0");
				String url = "/mobiles/advance-search/screen-size-5.0-6.0";
				String[] catageorySplit = url.split("/");
				List mobilesBySearchCatageoryList = this.MobileService.getMobilesBySearchCatageory(catageorySplit);
				request.setAttribute("mobilesBySearchCatageoryList", (Object) mobilesBySearchCatageoryList);
				request.setAttribute("companyList", (Object) this.MobileService.getCompaniesList());
				request.setAttribute("mobilesList", "5 Inches Mobile Phones");
				request.setAttribute("mobilesListdesc", "Find 5 inches mobile phones with specifications, features and different brands.");
				forward = "/advanceSearch.jsp";
			}else if (mobileName.contains((CharSequence) "selfie")) {
				request.setAttribute("secondarycamera", (Object) "above-5");
				String url = "/mobiles/advance-search/secondary-camera-above-5";
				String[] catageorySplit = url.split("/");
				List mobilesBySearchCatageoryList = this.MobileService.getMobilesBySearchCatageory(catageorySplit);
				request.setAttribute("mobilesBySearchCatageoryList", (Object) mobilesBySearchCatageoryList);
				request.setAttribute("companyList", (Object) this.MobileService.getCompaniesList());
				request.setAttribute("mobilesList", "Best Selfie Mobile Phones");
				request.setAttribute("mobilesListdesc", "Find best selfie mobile phones with specifications, features and different brands.");
				forward = "/advanceSearch.jsp";
			}else if (mobileName.contains((CharSequence) "dual-sim")) {
				request.setAttribute("sim", (Object) "dual-sim");
				request.setAttribute("mobilesBySearchCatageoryList", (Object) this.MobileService.getDualMobiles());
				request.setAttribute("companyList", (Object) this.MobileService.getCompaniesList());
				request.setAttribute("mobilesList", "Dual Sim Mobile Phones");
				request.setAttribute("mobilesListdesc", "Find dual sim mobile phones with specifications, features and different brands.");
				forward = "/advanceSearch.jsp";
			}else if (mobileName.contains((CharSequence) "2-gb-ram-phones")) {
				String url = "/mobiles/advance-search/ram-2-gb";
				String[] catageorySplit = url.split("/");
				List mobilesBySearchCatageoryList = this.MobileService.getMobilesBySearchCatageory(catageorySplit);
				request.setAttribute("mobilesBySearchCatageoryList", (Object) mobilesBySearchCatageoryList);
				request.setAttribute("companyList", (Object) this.MobileService.getCompaniesList());
				request.setAttribute("ram", (Object) "2-gb");
				request.setAttribute("mobilesList", " 2 GB Ram Mobile Phones");
				request.setAttribute("mobilesListdesc", "Find 2 gb ram mobile phones with specifications, features and different brands.");
				forward = "/advanceSearch.jsp";
			}else if (mobileName.contains((CharSequence) "5-inches-phones")) {
				String url = "/mobiles/advance-search/screen-size-5.0-6.0";
				request.setAttribute("url", (Object) "5.0-6.0");
				String[] catageorySplit = url.split("/");
				List mobilesBySearchCatageoryList = this.MobileService.getMobilesBySearchCatageory(catageorySplit);
				request.setAttribute("mobilesBySearchCatageoryList", (Object) mobilesBySearchCatageoryList);
				request.setAttribute("companyList", (Object) this.MobileService.getCompaniesList());
				request.setAttribute("mobilesList", "5 Inches Mobile Phones");
				request.setAttribute("mobilesListdesc", "Find 5 inches mobile phones with specifications, features and different brands.");
				forward = "/advanceSearch.jsp";
			}else if (mobileName.endsWith("-phones")) {
				String[] brandName = mobileName.split("-phones");
				request.setAttribute("brand", (Object) brandName[0].toLowerCase());
				getBrandDetails(request, brandName[0]);
				String brandTitleName = convertFirstLetterIntoUpperCase(brandName[0]);
				request.setAttribute("mobilesList", brandTitleName +" Mobile Phones");
				request.setAttribute("mobilesListdesc", "Find "+ brandName[0] +" mobile phones with specifications, features.");
				forward = "/advanceSearch.jsp";
			} else {
				String mobileName1 = mobileName.replaceAll("-", " ");
				Mobile mobile = this.MobileService.getMobileByName(mobileName1);
				request.setAttribute("mobile", (Object) mobile);
				if (mobile != null) {
					request.setAttribute("sameBrandMobiles", (Object) this.MobileService.getMobilesByCompanyId(mobile.getCompany_Id()));
					request.setAttribute("relatedMobiles", (Object) this.MobileService.getRelatedMobilesList(mobile));
				}
				forward = "/mobile.jsp";
			}
		}
        request.setAttribute("excep", mobileexception);
        }catch(Exception e){
        	System.out.println("errormsg........"+e.getMessage());
        	// forward = "/error.jsp";
        //	forward = requestURI;
	        	 try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
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
				//forward = "/error.jsp";
				forward = requestURI;
	        	 try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
	        	request.setAttribute("excep1", e.getCause());
	        	request.setAttribute("msg1", e.getMessage());
	        	request.setAttribute("block", "finally block");
				e.printStackTrace();
			}
		}
        
        
        request.setAttribute("aaa","1234");
        
        System.out.println("Request URL ::"+forward);
        
       RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
        
   //     getServletConfig().getServletContext().getRequestDispatcher(forward).forward(request,response);
    }

    private void getBrandDetails(HttpServletRequest request, String brandName) throws MobileException {
    	 //request.setAttribute("mobilesBySearchCatageoryList", MobileService.getMobilesByCompanyId(company.getId()));
    	 request.setAttribute("mobilesBySearchCatageoryList", MobileService.getMobilesByBrandName(brandName));
         request.setAttribute("companyList", (Object)this.MobileService.getCompaniesList());
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
    
    public void indexPageInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MobileException {
	 	RequestDispatcher view = request.getRequestDispatcher("/mobile.jsp");
        request.setAttribute("latestMobilesList", this.MobileService.getLatestMobile());
        request.setAttribute("topBrandMobilesList", this.MobileService.getTopBrandMobileList());
        request.setAttribute("upcomingMobilesList", this.MobileService.getUpcomingMobileList());
        view.forward(request, response);
}
    
    private void constructMobelAttribute(HttpServletRequest request, String[] catageoryList) {
        for (String cat : catageoryList) {
            if (cat.startsWith("brand-")) {
                request.setAttribute("brand", (Object)cat);
                String brandName = cat.split("brand-")[1];
                
                if(brandName.contains("_")){
                	StringBuffer sb = new StringBuffer();
                	String[] brandNames = brandName.split("_");
                	for (String name : brandNames) {
                		sb.append(convertFirstLetterIntoUpperCase(name)+", ");
					}
                	String substring = sb.substring(0, sb.length()-2);
                	request.setAttribute("mobilesList", (Object)substring +" Mobiles");
                }else{
                	
                	request.setAttribute("mobilesList", (Object)convertFirstLetterIntoUpperCase(brandName) +" Mobiles");
                }
                continue;
            }
            if (cat.startsWith("ram-")) {
                request.setAttribute("ram", (Object)cat);
                continue;
            }
            if (cat.startsWith("primary-camera-")) {
                request.setAttribute("primarycamera", (Object)cat);
                continue;
            }
            if (cat.startsWith("secondary-camera-")) {
                request.setAttribute("secondarycamera", (Object)cat);
                continue;
            }
            if (cat.startsWith("screen-size-")) {
                request.setAttribute("screensize", (Object)cat);
                continue;
            }
            if (cat.startsWith("sim-")) {
                request.setAttribute("sim", (Object)cat);
                continue;
            }
            if (cat.startsWith("availability-")) {
                request.setAttribute("availability", (Object)cat);
                continue;
            }
            if (cat.startsWith("internal-memory-")) {
                request.setAttribute("internalmemory", (Object)cat);
                continue;
            }
            if (!cat.startsWith("external-memory-")) continue;
            request.setAttribute("externalmemory", (Object)cat);
        }
    }

	private String convertFirstLetterIntoUpperCase(String brandName) {
		return brandName.substring(0, 1).toUpperCase() + brandName.substring(1);
	}
}