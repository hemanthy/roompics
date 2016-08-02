package com.ezone;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezone.dao.EzoneHelper;
import com.ezone.pojo.Category;
import com.ezone.pojo.Item;
import com.ezone.pojo.Product;
import com.ezone.service.MobilesServiceImpl;

@Controller
public class MobileController {
   
    private static final Logger logger = LoggerFactory.getLogger((Class)MobileController.class);
    
    private MobilesServiceImpl mobilesservice =  new MobilesServiceImpl();

   /* @Autowired(required=true)
    @Qualifier(value="MobileService")
    public void setMobileService(MobileService ps) {
        this.MobileService = ps;
    }*/
    
 //   @Autowired(required=true)
    /*@Qualifier(value="mobilesservice")
    public void setMobilesservice(MobilesService mobilesservice) {
                this.mobilesservice = mobilesservice;
        }*/
    
    @RequestMapping(value={"/index", "/"}, method={RequestMethod.GET})
    public String indexPageInfo(Model model) {
        System.out.println("mobilesservice::"+mobilesservice);
  //      model.addAttribute("companyList", (Object)this.MobileService.getCompaniesList());
        return "index";
    }
    
    @RequestMapping(value={"/queryString"}, method={RequestMethod.GET})
    @ResponseBody
    public String searchQuery(Model model,HttpServletRequest req) {
    	String jsonData = null;
    	String queryString = req.getParameter("q");
    	if(queryString!=null && !queryString.trim().isEmpty()){
    		jsonData =	mobilesservice.searchProductByQueryString(queryString.trim());
    		System.out.println(jsonData);
    		model.addAttribute("jsonData", jsonData);
    	}
		return jsonData;
    }
   
    
    @RequestMapping(value={"/electronics/*/**","/electronics"}, method={RequestMethod.GET})
    public String electronicsItem(Model model,HttpServletRequest req) {
        
        System.out.println("request URI ::"+req.getRequestURL());
        System.out.println("request URL ::"+req.getRequestURI());
        System.out.println("mobilesservice::"+mobilesservice);
        ArrayList<String> urlList = extractUrlParams(req);
        
        String  url = req.getRequestURI().substring(1);
        List<Product> productList = null;
        
        
        model.addAttribute("url", url);
        //model.addAttribute("productList", productList);
        
        String category1 = url.substring(0);
        
        Item itemSubCategory = EzoneHelper.getSubCategory(category1);
        if(itemSubCategory!=null){
                if(itemSubCategory.getSubCategoryList().size() > 0){
                        model.addAttribute("categoryList",  itemSubCategory.getSubCategoryList());
                        model.addAttribute("brandNames",  itemSubCategory.getSubCategoryList().get(0).getBrandNames());
                        model.addAttribute("title",  itemSubCategory.getTitle());
                }
        }
        ArrayList<Product> electronicsList = new ArrayList<Product>();
                for (Category category : itemSubCategory.getSubCategoryList()) {
                        mobilesservice.setCategory(category.getName());
                        List<Product> mobilesAccessoriesList = null;
                        if(category.getNodeId()!=null){
                                mobilesAccessoriesList = mobilesservice.getProductsByCategoryName(category.getNodeId());
                        }else{
                                mobilesAccessoriesList = mobilesservice.geMobilesProductItems();
                        }
                        if(mobilesAccessoriesList!=null){
                                electronicsList.addAll(mobilesAccessoriesList);
                        }
                }
                if(!electronicsList.isEmpty()){
                        Set<Product> productset = new HashSet<Product>(electronicsList);
                        ArrayList<Product> randomList = new ArrayList<Product>(productset);
                        if(randomList.size() > 201){
                                model.addAttribute("productList", randomList.subList(0, 200));          
                        }else{
                                model.addAttribute("productList", randomList);    
                        }
                }
        
    //  model.addAttribute("productList", productList);
        model.addAttribute("urlList", urlList);
        return "index1";
    }


        private Object getValueByFieldName(String selectedCategory,
                        Object constantsCategoryNodeId) {
                Object val = null;
                Class<?> c = constantsCategoryNodeId.getClass();
                selectedCategory = selectedCategory.replaceAll("-", "_");
                Field[] declaredFields = c.getDeclaredFields();
                for (Field field : declaredFields) {
                        System.out.println(field.getName());
                        if(selectedCategory.equalsIgnoreCase(field.getName())){
                                field.setAccessible(true);
                                try {
                                        val = field.get(constantsCategoryNodeId);
                                } catch (IllegalArgumentException e) {
                                        e.printStackTrace();
                                } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                }
                        }               
                        }
                return val;
        }


        


        private ArrayList<String> extractUrlParams(HttpServletRequest req) {
                String[] urlList = req.getRequestURI().split("/");
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String name : urlList) {
                if(name.trim().length()>1){
                        String s1 = name.substring(0, 1).toUpperCase() + name.substring(1);
                        arrayList.add(s1);
                }
                }
                return arrayList;
        }

        
}