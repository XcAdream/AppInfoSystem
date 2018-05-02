package com.jbit.controller;

import com.jbit.entity.*;
import com.jbit.service.*;
import com.jbit.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Xc on 2018/4/19.
 */
@Controller
@RequestMapping("/backend")
public class BackendController {

    @Resource
    private BackendUserService backendUserService;
    @Resource
    private DataDictionaryService dataDictionaryService;
    @Resource
    private AppCategoryService appCategoryService;
    @Resource
    private AppInfoService appInfoService;
    @Resource
    private AppVersionService appVersionService;

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(String userCode, String userPassword, HttpSession session,Model model){
        BackendUser user=backendUserService.findlogin(userCode,userPassword);
        if(user!=null){
            session.setAttribute("user",user);
            return "redirect:/jsp/backend/main.jsp";
        }else{
            model.addAttribute("error","用户名或密码错误！");
            return "backendlogin";
        }
    }

    @RequestMapping("/applist")
    public String applist(String querySoftwareName,Integer pageIndex,Integer queryFlatformId,Integer queryCategoryLevel1,Integer queryCategoryLevel2,Integer queryCategoryLevel3,Model model){
        List<DataDictionary> flatFormList=dataDictionaryService.findtypeCode("APP_FLATFORM");
        List<AppCategory> categoryLevel1List=appCategoryService.findcategoryLevel(null);
        if(queryCategoryLevel1==null && queryCategoryLevel2==null){
            queryCategoryLevel1=0;
            queryCategoryLevel2=0;
        }
        List<AppCategory> categoryLevel2List=appCategoryService.findcategoryLevel(queryCategoryLevel1);
        List<AppCategory> categoryLevel3List=appCategoryService.findcategoryLevel(queryCategoryLevel2);
        Page pages=new Page();
        if(pageIndex!=null){
            pages.setCurrentPageNo(pageIndex);
        }
        List<AppInfo> appInfoList=appInfoService.findBackendAppList(querySoftwareName,queryFlatformId,queryCategoryLevel1,queryCategoryLevel2,queryCategoryLevel3,pages);
        model.addAttribute("appInfoList",appInfoList);
        model.addAttribute("flatFormList",flatFormList);
        model.addAttribute("categoryLevel1List",categoryLevel1List);
        model.addAttribute("categoryLevel2List",categoryLevel2List);
        model.addAttribute("categoryLevel3List",categoryLevel3List);
        model.addAttribute("querySoftwareName",querySoftwareName);
        model.addAttribute("queryFlatformId",queryFlatformId);
        model.addAttribute("queryCategoryLevel1",queryCategoryLevel1);
        model.addAttribute("queryCategoryLevel2",queryCategoryLevel2);
        model.addAttribute("queryCategoryLevel3",queryCategoryLevel3);
        model.addAttribute("pages",pages);
        return "backend/applist";
    }

    @RequestMapping("/categorylevellist.json")
    @ResponseBody
    public List<AppCategory> categorylevellist(Integer pid){
        return appCategoryService.findcategoryLevel(pid);
    }


    @RequestMapping("/check")
    public String checkApp(Long aid,Long vid,Model model){
        AppInfo appInfo= appInfoService.findAppOne(aid);
        AppVersion appVersion= appVersionService.findAppVersionOne(vid);
        model.addAttribute("appInfo",appInfo);
        model.addAttribute("appVersion",appVersion);
       return "backend/appcheck";
    }

    @RequestMapping("/checksave")
    public String checksave(Long aid,Integer vid,Long status){
       Integer res =appInfoService.upStatus(aid,status);
        if(res==1){
            return "redirect:/backend/applist";
        }
       return "redirect:/baeckend/check?aid="+aid+"&vid="+vid;
    }

}
