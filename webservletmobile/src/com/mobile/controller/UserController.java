package com.mobile.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobile.dao.UserDao;
import com.mobile.model.Company;
import com.mobile.model.Mobile;
import com.mobile.model.User;
import com.mobile.service.MobileService;
import com.mobile.service.MobileServiceImpl;

public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/user.jsp";
    private static String LIST_USER = "/listUser.jsp";
    private UserDao dao;
    
    public static final Integer RAM_SIZE = 2;
    public static final Integer SCREEN_SIZE = 5;
    public static final Integer SECONDARY_CAMERA = 5;
    public static final String COMPARE_URL = "/compare/";

    public UserController() {
        super();
        dao = new UserDao();
        MobileService = new MobileServiceImpl();
    }
    
    private MobileService MobileService;
	

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="/error.jsp";
        String action = "";
        action  = request.getParameter("action");
        if(action == null)
        	action = "";
        
        String requestURI = request.getRequestURI();
        
        System.out.println(requestURI);

        if (action.equalsIgnoreCase("indexPage")){
        		forward = "/index.jsp";
	            request.setAttribute("latestMobilesList", this.MobileService.getLatestMobile());
	            request.setAttribute("topBrandMobilesList", this.MobileService.getTopBrandMobileList());
	            request.setAttribute("upcomingMobilesList", this.MobileService.getUpcomingMobileList());
	            request.setAttribute("companyList", this.MobileService.getCompaniesList());
         //   view.forward(request, response);
        }else if (action.equalsIgnoreCase("all-brands") || requestURI.contains("all-brands")) {
			forward = "/mobiles.jsp";
			request.setAttribute("companyList", this.MobileService.getCompaniesList());
		} else if (action.equalsIgnoreCase("/advance-search/") || requestURI.contains("/advance-search/")) {
			forward = "/advanceSearch.jsp";
			request.setAttribute("companyList", this.MobileService.getCompaniesList());
			if (requestURI.contains((CharSequence)"search")) {
	            String[] urlSplit = requestURI.split("search");
	            String searchURL = urlSplit[1];
	            String[] catageoryList = searchURL.split("/");
	            List<Mobile> mobilesBySearchCatageoryList = this.MobileService.getMobilesBySearchCatageory(catageoryList);
	            request.setAttribute("companyList", (Object)this.MobileService.getCompaniesList());
	            request.setAttribute("mobilesBySearchCatageoryList", (Object)mobilesBySearchCatageoryList);
	            request.setAttribute("url", (Object)requestURI);
	            this.constructMobelAttribute(request, catageoryList);
	        }
		} 
		
		else if (action.equalsIgnoreCase("/advance-search") || requestURI.contains("/advance-search")) {
				forward = "/advanceSearch.jsp";
			 	request.setAttribute("ramBasedmobiles", (Object)this.MobileService.getMobilesByRamSize(RAM_SIZE));
		        request.setAttribute("screensizeBasedmobiles", (Object)this.MobileService.getMobilesByScreenSize(SCREEN_SIZE));
		        request.setAttribute("secondaryCameraBasedmobiles", (Object)this.MobileService.getMobilesBySecondaryCamera(SECONDARY_CAMERA));
		        request.setAttribute("dualSimMobiles", (Object)this.MobileService.getDualMobiles());
		        request.setAttribute("companyList", (Object)this.MobileService.getCompaniesList());
		}
		
		else if (action.equalsIgnoreCase("compare") || requestURI.contains("compare")) {
			forward = "/compare.jsp";
	        String requestURL = request.getRequestURI().toLowerCase();
	        if (requestURL.contains((CharSequence)"-vs-")) {
	            String[] splitUrl = requestURL.split("-vs-");
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
	        } else if (requestURL.contains((CharSequence)"/compare/")) {
	            int index1 = requestURL.indexOf("/compare/");
	            String mobileName = requestURL.substring("/compare/".length() + index1);
	            String mobileName1 = mobileName.replaceAll("-", " ");
	            request.setAttribute("mobile1", (Object)this.MobileService.getMobileByName(mobileName1));
	        }
		} else if (action.equalsIgnoreCase("mobiles/") || requestURI.contains("mobiles/")) {
			String mobileName = requestURI.split("mobiles/")[1];
			if (mobileName.endsWith("upcoming-phones")) {
				request.setAttribute("availability", (Object) "upcoming-mobiles");
				request.setAttribute("mobilesBySearchCatageoryList",
						(Object) this.MobileService.getUpcomingMobileList());
				request.setAttribute("companyList", (Object) this.MobileService.getCompaniesList());
				forward = "/advanceSearch.jsp";
			}else if (mobileName.contains((CharSequence) "screen-size")) {
				request.setAttribute("screensize", (Object) "5.0-6.0");
				String url = "/mobiles/advance-search/screen-size-5.0-6.0";
				String[] catageorySplit = url.split("/");
				List mobilesBySearchCatageoryList = this.MobileService.getMobilesBySearchCatageory(catageorySplit);
				request.setAttribute("mobilesBySearchCatageoryList", (Object) mobilesBySearchCatageoryList);
				request.setAttribute("companyList", (Object) this.MobileService.getCompaniesList());
				forward = "/advanceSearch.jsp";
			}else if (mobileName.contains((CharSequence) "selfie")) {
				request.setAttribute("secondarycamera", (Object) "above-5");
				String url = "/mobiles/advance-search/secondary-camera-above-5";
				String[] catageorySplit = url.split("/");
				List mobilesBySearchCatageoryList = this.MobileService.getMobilesBySearchCatageory(catageorySplit);
				request.setAttribute("mobilesBySearchCatageoryList", (Object) mobilesBySearchCatageoryList);
				request.setAttribute("companyList", (Object) this.MobileService.getCompaniesList());
				forward = "/advanceSearch.jsp";
			}else if (mobileName.contains((CharSequence) "dual-sim")) {
				request.setAttribute("sim", (Object) "dual-sim");
				request.setAttribute("mobilesBySearchCatageoryList", (Object) this.MobileService.getDualMobiles());
				request.setAttribute("companyList", (Object) this.MobileService.getCompaniesList());
				forward = "/advanceSearch.jsp";
			}else if (mobileName.contains((CharSequence) "2-gb-ram-phones")) {
				String url = "/mobiles/advance-search/ram-2-gb";
				String[] catageorySplit = url.split("/");
				List mobilesBySearchCatageoryList = this.MobileService.getMobilesBySearchCatageory(catageorySplit);
				request.setAttribute("mobilesBySearchCatageoryList", (Object) mobilesBySearchCatageoryList);
				request.setAttribute("companyList", (Object) this.MobileService.getCompaniesList());
				request.setAttribute("ram", (Object) "2-gb");
				forward = "/advanceSearch.jsp";
			}else if (mobileName.contains((CharSequence) "5-inches-phones")) {
				String url = "/mobiles/advance-search/screen-size-5.0-6.0";
				request.setAttribute("url", (Object) "5.0-6.0");
				String[] catageorySplit = url.split("/");
				List mobilesBySearchCatageoryList = this.MobileService.getMobilesBySearchCatageory(catageorySplit);
				request.setAttribute("mobilesBySearchCatageoryList", (Object) mobilesBySearchCatageoryList);
				request.setAttribute("companyList", (Object) this.MobileService.getCompaniesList());
				forward = "/advanceSearch.jsp";
			}else if (mobileName.endsWith("-phones")) {
				String[] brandName = mobileName.split("-phones");
				request.setAttribute("brand", (Object) brandName[0].toLowerCase());
				getBrandDetails(request, brandName[0]);
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
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    private void getBrandDetails(HttpServletRequest request, String brandName) {
    	 Company company = this.MobileService.getBrandByName(brandName);
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
    
    public void indexPageInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
}