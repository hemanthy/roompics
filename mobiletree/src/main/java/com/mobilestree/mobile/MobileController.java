package com.mobilestree.mobile;

import com.google.gson.Gson;
import com.mobilestree.mobile.DataMobileVO;
import com.mobilestree.mobile.model.Company;
import com.mobilestree.mobile.model.Mobile;
import com.mobilestree.mobile.model.Person;
import com.mobilestree.mobile.service.MobileService;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MobileController {
    public static final Integer RAM_SIZE = 2;
    public static final Integer SCREEN_SIZE = 5;
    public static final Integer SECONDARY_CAMERA = 5;
    public static final String COMPARE_URL = "/compare/";
    private static final Logger logger = LoggerFactory.getLogger((Class)MobileController.class);
    private MobileService MobileService;

    @Autowired(required=true)
    @Qualifier(value="MobileService")
    public void setMobileService(MobileService ps) {
        this.MobileService = ps;
    }

    @RequestMapping(value={"/index", "/"}, method={RequestMethod.GET})
    public String indexPageInfo(Model model) {
        model.addAttribute("latestMobilesList", (Object)this.MobileService.getLatestMobile());
        model.addAttribute("topBrandMobilesList", (Object)this.MobileService.getTopBrandMobileList());
        model.addAttribute("upcomingMobilesList", (Object)this.MobileService.getUpcomingMobileList());
        model.addAttribute("companyList", (Object)this.MobileService.getCompaniesList());
        return "index";
    }

    @RequestMapping(value={"/mobiles/all-brands"}, method={RequestMethod.GET})
    public String getMobileList(Model model) {
        model.addAttribute("companyList", (Object)this.MobileService.getCompaniesList());
        return "mobiles";
    }

    @RequestMapping(value={"/mobiles/compare/*", "/mobiles/compare"}, method={RequestMethod.GET})
    public String compareMobiles(Model model, HttpServletRequest request) {
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
                model.addAttribute("mobileUrl" + mobileCount, (Object)this.MobileService.getMobileByName(mobileName1));
                model.addAttribute("mobile" + mobileCount, (Object)this.MobileService.getMobileByName(mobileName1));
                ++mobileCount;
            }
            String s1 = splitUrl[0];
            int index1 = s1.indexOf("/compare/");
            s1 = s1.substring("/compare/".length() + index1);
            model.addAttribute("m1", (Object)s1);
            if (splitUrl.length >= 2) {
                String url = splitUrl[1];
                model.addAttribute("m2", (Object)url);
            }
            if (splitUrl.length >= 3) {
                String url = splitUrl[2];
                model.addAttribute("m3", (Object)url);
            }
            if (splitUrl.length >= 4) {
                String url = splitUrl[3];
                model.addAttribute("m4", (Object)url);
            }
        } else if (requestURL.contains((CharSequence)"/compare/")) {
            int index1 = requestURL.indexOf("/compare/");
            String mobileName = requestURL.substring("/compare/".length() + index1);
            String mobileName1 = mobileName.replaceAll("-", " ");
            model.addAttribute("mobile1", (Object)this.MobileService.getMobileByName(mobileName1));
        }
        return "compare";
    }

    @RequestMapping(value={"/mobiles/{mobileName}"}, method={RequestMethod.GET})
    public String getMobileDetails(Model model, @PathVariable(value="mobileName") String mobileName, HttpServletRequest request) {
        if (mobileName.endsWith("upcoming-phones")) {
            model.addAttribute("availability", (Object)"upcoming-mobiles");
            model.addAttribute("mobilesBySearchCatageoryList", (Object)this.MobileService.getUpcomingMobileList());
            model.addAttribute("companyList", (Object)this.MobileService.getCompaniesList());
            return "advanceSearch";
        }
        if (mobileName.contains((CharSequence)"screen-size")) {
            model.addAttribute("screensize", (Object)"5.0-6.0");
            String url = "/mobiles/advance-search/screen-size-5.0-6.0";
            String[] catageorySplit = url.split("/");
            List mobilesBySearchCatageoryList = this.MobileService.getMobilesBySearchCatageory(catageorySplit);
            model.addAttribute("mobilesBySearchCatageoryList", (Object)mobilesBySearchCatageoryList);
            model.addAttribute("companyList", (Object)this.MobileService.getCompaniesList());
            return "advanceSearch";
        }
        if (mobileName.contains((CharSequence)"selfie")) {
            model.addAttribute("secondarycamera", (Object)"above-5");
            String url = "/mobiles/advance-search/secondary-camera-above-5";
            String[] catageorySplit = url.split("/");
            List mobilesBySearchCatageoryList = this.MobileService.getMobilesBySearchCatageory(catageorySplit);
            model.addAttribute("mobilesBySearchCatageoryList", (Object)mobilesBySearchCatageoryList);
            model.addAttribute("companyList", (Object)this.MobileService.getCompaniesList());
            return "advanceSearch";
        }
        if (mobileName.contains((CharSequence)"dual-sim")) {
            model.addAttribute("sim", (Object)"dual-sim");
            model.addAttribute("mobilesBySearchCatageoryList", (Object)this.MobileService.getDualMobiles());
            model.addAttribute("companyList", (Object)this.MobileService.getCompaniesList());
            return "advanceSearch";
        }
        if (mobileName.contains((CharSequence)"2-gb-ram-phones")) {
            String url = "/mobiles/advance-search/ram-2-gb";
            String[] catageorySplit = url.split("/");
            List mobilesBySearchCatageoryList = this.MobileService.getMobilesBySearchCatageory(catageorySplit);
            model.addAttribute("mobilesBySearchCatageoryList", (Object)mobilesBySearchCatageoryList);
            model.addAttribute("companyList", (Object)this.MobileService.getCompaniesList());
            model.addAttribute("ram", (Object)"2-gb");
            return "advanceSearch";
        }
        if (mobileName.contains((CharSequence)"5-inches-phones")) {
            String url = "/mobiles/advance-search/screen-size-5.0-6.0";
            model.addAttribute("url", (Object)"5.0-6.0");
            String[] catageorySplit = url.split("/");
            List mobilesBySearchCatageoryList = this.MobileService.getMobilesBySearchCatageory(catageorySplit);
            model.addAttribute("mobilesBySearchCatageoryList", (Object)mobilesBySearchCatageoryList);
            model.addAttribute("companyList", (Object)this.MobileService.getCompaniesList());
            return "advanceSearch";
        }
        if (mobileName.endsWith("-phones")) {
            String[] brandName = mobileName.split("-phones");
            model.addAttribute("brand", (Object)brandName[0].toLowerCase());
            return this.getBrandDetails(model, brandName[0]);
        }
        String[] urlArray = mobileName.split("-");
        String deviceId = urlArray[urlArray.length - 1];
        String mobileName1 = mobileName.replaceAll("-", " ");
        Mobile mobile = this.MobileService.getMobileByName(mobileName1);
        model.addAttribute("mobile", (Object)mobile);
        if (mobile != null) {
            model.addAttribute("company", (Object)this.MobileService.getCompanyById(mobile.getCompany().getId()));
            model.addAttribute("relatedMobiles", (Object)this.MobileService.getRelatedMobilesList(mobile));
        }
        return "mobile";
    }

    @RequestMapping(value={"/brands/{brandName}"}, method={RequestMethod.GET})
    public String getBrandDetails(Model model, @PathVariable(value="brandName") String brandName) {
        Company company = this.MobileService.getBrandByName(brandName);
        model.addAttribute("mobilesBySearchCatageoryList", (Object)company.getMobile());
        model.addAttribute("companyList", (Object)this.MobileService.getCompaniesList());
        return "advanceSearch";
    }

    @RequestMapping(value={"/mobiles/advance-search"}, method={RequestMethod.GET})
    public String getAdvanceSearchLandingPageDetails(Model model) {
        logger.info("Entered getAdvanceSearchLandingPageDetails Method");
        model.addAttribute("ramBasedmobiles", (Object)this.MobileService.getMobilesByRamSize(RAM_SIZE));
        model.addAttribute("screensizeBasedmobiles", (Object)this.MobileService.getMobilesByScreenSize(SCREEN_SIZE));
        model.addAttribute("secondaryCameraBasedmobiles", (Object)this.MobileService.getMobilesBySecondaryCamera(SECONDARY_CAMERA));
        model.addAttribute("dualSimMobiles", (Object)this.MobileService.getDualMobiles());
        model.addAttribute("companyList", (Object)this.MobileService.getCompaniesList());
        logger.info("Exit getAdvanceSearchLandingPageDetails Method");
        return "advanceSearch";
    }

    @RequestMapping(value={"/mobiles/advance-search/*/**"}, method={RequestMethod.GET})
    public String getAdvanceSearchDetails(Model model, HttpServletRequest request) {
        logger.info("Entered getAdvanceSearchDetails Method");
        String requestURL = request.getRequestURI().toLowerCase();
        if (requestURL.contains((CharSequence)"search")) {
            String[] urlSplit = requestURL.split("search");
            String searchURL = urlSplit[1];
            String[] catageoryList = searchURL.split("/");
            List mobilesBySearchCatageoryList = this.MobileService.getMobilesBySearchCatageory(catageoryList);
            model.addAttribute("companyList", (Object)this.MobileService.getCompaniesList());
            model.addAttribute("mobilesBySearchCatageoryList", (Object)mobilesBySearchCatageoryList);
            model.addAttribute("url", (Object)requestURL);
            this.constructMobelAttribute(model, catageoryList);
        }
        logger.info("Exit getAdvanceSearchDetails Method");
        return "advanceSearch";
    }

    private void constructMobelAttribute(Model model, String[] catageoryList) {
        for (String cat : catageoryList) {
            if (cat.startsWith("brand-")) {
                model.addAttribute("brand", (Object)cat);
                continue;
            }
            if (cat.startsWith("ram-")) {
                model.addAttribute("ram", (Object)cat);
                continue;
            }
            if (cat.startsWith("primary-camera-")) {
                model.addAttribute("primarycamera", (Object)cat);
                continue;
            }
            if (cat.startsWith("secondary-camera-")) {
                model.addAttribute("secondarycamera", (Object)cat);
                continue;
            }
            if (cat.startsWith("screen-size-")) {
                model.addAttribute("screensize", (Object)cat);
                continue;
            }
            if (cat.startsWith("sim-")) {
                model.addAttribute("sim", (Object)cat);
                continue;
            }
            if (cat.startsWith("availability-")) {
                model.addAttribute("availability", (Object)cat);
                continue;
            }
            if (cat.startsWith("internal-memory-")) {
                model.addAttribute("internalmemory", (Object)cat);
                continue;
            }
            if (!cat.startsWith("external-memory-")) continue;
            model.addAttribute("externalmemory", (Object)cat);
        }
    }

    @RequestMapping(value={"/queryString"}, method={RequestMethod.GET})
    @ResponseBody
    public String autoCompleteSearch(Model model, HttpServletRequest request) {
        String queryString = request.getParameter("q");
        DataMobileVO dataMobileVO = this.MobileService.getMobileDetailsByQueryString(queryString);
        Gson gson = new Gson();
        String jsonData = gson.toJson((Object)dataMobileVO);
        logger.info("Json Data : " + jsonData);
        model.addAttribute("jsonData", (Object)jsonData);
        return jsonData;
    }

    @RequestMapping(value={"/company/add"}, method={RequestMethod.POST})
    public String addCopmany(@ModelAttribute(value="company") Company c) {
        if (c.getId() == 0) {
            this.MobileService.addCompany(c);
        } else {
            this.MobileService.updateCompany(c);
        }
        return "redirect:/company";
    }

    @RequestMapping(value={"/company"}, method={RequestMethod.GET})
    public String listCompany(Model model) {
        model.addAttribute("company", (Object)new Company());
        model.addAttribute("listCompanies", (Object)this.MobileService.listCompanies());
        return "updateCompany";
    }

    @RequestMapping(value={"/persons"}, method={RequestMethod.GET})
    public String listPersons(Model model) {
        model.addAttribute("person", (Object)new Person());
        model.addAttribute("listPersons", (Object)this.MobileService.listPersons());
        return "person";
    }

    @RequestMapping(value={"/person/add"}, method={RequestMethod.POST})
    public String addPerson(@ModelAttribute(value="person") Person p) {
        if (p.getId() == 0) {
            this.MobileService.addPerson(p);
        } else {
            this.MobileService.updatePerson(p);
        }
        return "redirect:/persons";
    }

    @RequestMapping(value={"/remove/{id}"})
    public String removePerson(@PathVariable(value="id") int id) {
        this.MobileService.removePerson(id);
        return "redirect:/persons";
    }

    @RequestMapping(value={"/edit/{id}"})
    public String editPerson(@PathVariable(value="id") int id, Model model) {
        model.addAttribute("person", (Object)this.MobileService.getPersonById(id));
        model.addAttribute("listPersons", (Object)this.MobileService.listPersons());
        return "person";
    }

    @RequestMapping(value={"/company/{id}"})
    public String getMobilesList(@PathVariable(value="id") int id, Model model) {
        model.addAttribute("mobileList", (Object)this.MobileService.getMobilesList(id));
        return "showMobile";
    }

    @RequestMapping(value={"/mobile/edit/{id}"})
    public String getMobileDetail(@PathVariable(value="id") Integer id, Model model) {
        model.addAttribute("mobile", (Object)this.MobileService.getMobileById(id));
        return "showMobile";
    }
}