package com.jbit.controller;

import com.jbit.entity.*;
import com.jbit.service.*;
import com.jbit.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2018/4/19.
 */
@Controller
@RequestMapping("/dev")
public class DevController {

    @Resource
    private DevUserService devUserService;
    @Resource
    private AppInfoService appInfoService;
    @Resource
    private AppCategoryService appCategoryService;
    @Resource
    private DataDictionaryService dataDictionaryService;
    @Resource
    private AppVersionService appVersionService;

    @RequestMapping(value = "/devlogin",method = RequestMethod.POST)
    public String devlogin(String devCode, String devPassword, HttpSession session, Model model){
        DevUser devUser=devUserService.findlogin(devCode,devPassword);
        if(devUser!=null){
            session.setAttribute("devUser",devUser);
            return "redirect:/jsp/developer/main.jsp";
        }else{
            model.addAttribute("error","用户名或密码错误！");
            return "devlogin";
        }
    }

    @RequestMapping("/applist")
    public String applist(Integer pageIndex,String querySoftwareName,Integer queryStatus,Integer queryFlatformId,Integer queryCategoryLevel1,Integer queryCategoryLevel2,Integer queryCategoryLevel3,Model model){
        //APP状态
        List<DataDictionary> statusList=dataDictionaryService.findtypeCode("APP_STATUS");
        //APP所属平台
        List<DataDictionary> flatFormList=dataDictionaryService.findtypeCode("APP_FLATFORM");
        //分页加模糊查询
        Page pages=new Page();
        if(pageIndex!=null){
            pages.setCurrentPageNo(pageIndex);
        }
        List<AppInfo> appInfoList=appInfoService.findAppList(queryStatus,querySoftwareName,queryFlatformId,queryCategoryLevel1,queryCategoryLevel2,queryCategoryLevel3,pages);
        //三级分类
        List<AppCategory> categoryLevel1List=appCategoryService.findcategoryLevel(null);
        if(queryCategoryLevel1==null&&queryCategoryLevel2==null){
            queryCategoryLevel1=0;
            queryCategoryLevel2=0;
        }
        List<AppCategory> categoryLevel2List=appCategoryService.findcategoryLevel(queryCategoryLevel1);
        List<AppCategory> categoryLevel3List=appCategoryService.findcategoryLevel(queryCategoryLevel2);
        model.addAttribute("statusList",statusList);
        model.addAttribute("flatFormList",flatFormList);
        model.addAttribute("categoryLevel1List",categoryLevel1List);
        model.addAttribute("categoryLevel2List",categoryLevel2List);
        model.addAttribute("categoryLevel3List",categoryLevel3List);
        model.addAttribute("appInfoList",appInfoList);
        model.addAttribute("querySoftwareName",querySoftwareName);
        model.addAttribute("queryStatus",queryStatus);
        model.addAttribute("queryFlatformId",queryFlatformId);
        model.addAttribute("queryCategoryLevel1",queryCategoryLevel1);
        model.addAttribute("queryCategoryLevel2",queryCategoryLevel2);
        model.addAttribute("queryCategoryLevel3",queryCategoryLevel3);
        model.addAttribute("pages",pages);
        return "developer/appinfolist";
    }

    @RequestMapping("/categorylevellist.json")
    @ResponseBody
    public List<AppCategory> categorylevellist(Integer pid){
            return appCategoryService.findcategoryLevel(pid);
    }

    @RequestMapping("/datadictionarylist.json")
    @ResponseBody
    public List<DataDictionary> datadictionarylist(String tcode){ return dataDictionaryService.findtypeCode(tcode);}

    @RequestMapping("/apkexist.json")
    @ResponseBody
    public String apkexist(String APKName){
       AppInfo appInfo=appInfoService.findAPKName(APKName);
        if(APKName==null || APKName==""){
             return "empty";
        }
        if(appInfo!=null){
            return "exist";
        }else{
            return "noexist";
        }
    }

    @RequestMapping("/appinfoadd")
    public String appinfoadd(){
        return "developer/appinfoadd";
    }


    @RequestMapping("/appversionadd")
    public String appversionadd(Long id,Model model){
        List<AppVersion> appVersionList=appVersionService.findAppVersion(id);
        model.addAttribute("appVersionList",appVersionList);
        model.addAttribute("appid",id);
        return "developer/appversionadd";
    }

    @RequestMapping("/addversionsave")
    public String addversionsave(HttpSession session,Model model,Long appid,AppVersion appVersion,@RequestParam("a_downloadlink") MultipartFile file){
         String downloadlink=session.getServletContext().getContextPath()+"/statics/uploadfiles/"+file.getOriginalFilename();
         String apklocpath=session.getServletContext().getRealPath("statics/uploadfiles")+File.separator+file.getOriginalFilename();
         String apkfilename=file.getOriginalFilename();
         appVersion.setDownloadlink(downloadlink);
         appVersion.setApklocpath(apklocpath);
         appVersion.setApkfilename(apkfilename);
         int res=appVersionService.insertAppVersion(appVersion);
         if(res==1){
             try {
                 file.transferTo(new File(apklocpath));
                 AppVersion applast=appVersionService.findAppVersionlast();
                 appInfoService.UpdateAppVersion(applast.getId(),appid);
             } catch (IOException e) {
                 model.addAttribute("fileUploadError","上传失败,请重试!");
             }
             return "redirect:/dev/applist";
         }else{
             return "developer/appversionadd";
         }
    }


    @RequestMapping("/appversionmodify")
    public String appversionmodify(Long vid,Long aid,Model model){
        List<AppVersion> appVersionList=appVersionService.findAppVersion(aid);
        AppVersion appVersion=appVersionService.findAppVersionOne(vid);
        model.addAttribute("appVersionList",appVersionList);
        model.addAttribute("appVersion",appVersion);
        return "developer/appversionmodify";
    }
    @RequestMapping("/appversionmodifysave")
    public String appversionmodifysave(AppVersion appVersion,@RequestParam("attach") MultipartFile file,HttpSession session,Model model){
        String path=session.getServletContext().getRealPath("/statics/uploadfiles");  //服务器路径
        String path1=session.getServletContext().getContextPath()+"/statics/uploadfiles/"; //项目路径
           if(!file.getOriginalFilename().equals("")){
               appVersion.setApkfilename(file.getOriginalFilename());
           }
            appVersion.setApklocpath(path+File.separator+file.getOriginalFilename());
            appVersion.setDownloadlink(path1+file.getOriginalFilename());
        int res=appVersionService.updateAppVersion(appVersion);
        if(res==1){
            try {
                file.transferTo(new File(path+File.separator+file.getOriginalFilename()));
            } catch (IOException e) {
                model.addAttribute("fileUploadError","上传失败！");
            }
            return "redirect:/dev/applist";
        }
        return "redirect:/dev/appversionmodify?vid="+appVersion.getId()+"&aid="+appVersion.getAppid();
    }
    @RequestMapping("/delfile.json")
    @ResponseBody
    public String delfile(Long id,String flag){
        if(flag.equals("apk")&&flag!=null){
            AppVersion appVersion= appVersionService.findAppVersionOne(id);
            File file=new File(appVersion.getApklocpath());
            if(file.exists()){
                file.delete();
                Integer res=appVersionService.updatedel(id);
                if(res==1){
                    return "success";
                }else{
                    return "failed";
                }
            }
        }else if(flag.equals("logo")&& flag!=null){
           AppInfo appInfo= appInfoService.findAppOne(id);
           File file=new File(appInfo.getLogolocpath());
            if(file.exists()){
                file.delete();
                Integer res=appInfoService.updateApp(id);
                if(res==1){
                    return "success";
                }else{
                    return "failed";
                }
            }
        }
        return null;
    }

    @RequestMapping("/appview/{id}")
    public String appview(@PathVariable Long id,Model model){
        AppInfo appInfo=appInfoService.findAppOne(id);
        List<AppVersion> appVersionList=appVersionService.findAppVersion(id);
        model.addAttribute("appInfo",appInfo);
        model.addAttribute("appVersionList",appVersionList);
        return "developer/appinfoview";
    }

    @RequestMapping("/appinfomodify")
    public String appinfomodify(Long id,Model model){
        AppInfo appInfo=appInfoService.findAppOne(id);
        model.addAttribute("appInfo",appInfo);
        return "developer/appinfomodify";
    }

    @RequestMapping("/appinfomodifysave")
    public String appinfomodifysave(AppInfo appInfo,@RequestParam("attach") MultipartFile file,HttpSession session,Model model){
        String path=session.getServletContext().getRealPath("statics/uploadfiles");  //服务器路径
        String path1=session.getServletContext().getContextPath()+"/statics/uploadfiles/"; //项目路径
        if(!file.isEmpty()){
            appInfo.setLogopicpath(path1+file.getOriginalFilename());
            appInfo.setLogolocpath(path+File.separator+file.getOriginalFilename());
        }
        if(appInfo.getStatus()!=null){
            appInfoService.upStatus(appInfo.getId(),appInfo.getStatus());
        }
        Integer res=appInfoService.updateAppinfo(appInfo);
        if(res==1){
            try {
                file.transferTo(new File(path+File.separator+file.getOriginalFilename()));
            } catch (IOException e) {
                model.addAttribute("fileUploadError","上传失败！请重试！");
            }
            return "redirect:/dev/applist";
        }
        return null;
    }

    @RequestMapping("/delapp.json")
    @ResponseBody
    public String delapp(Long id){
        AppInfo appInfo=appInfoService.findAppOne(id);
        if(appInfo==null){
            return "notexist";
        }
        Integer res=appInfoService.delApp(id);
        if(res==1){
            if(appInfo.getVersionid()!=null){
                int res1=appVersionService.deleteAppversion(id);
                if(res1>0){
                    return "true";
                }
                else{
                    return "false";
                }
            }
            return "true";

        }
        return "false";
    }


    @RequestMapping("/appinfoaddsave")
    public String appinfoaddsave(AppInfo appInfo,@RequestParam("a_logoPicPath") MultipartFile file, HttpSession session,Model model){
        String path1=session.getServletContext().getContextPath()+"/statics/uploadfiles/";  //项目路径
        String logoPicPath=path1+file.getOriginalFilename();
        String path2=session.getServletContext().getRealPath("statics/uploadfiles");//服务器路径
        String logoLocPath=path2+File.separator+file.getOriginalFilename();
        appInfo.setLogolocpath(logoLocPath);
        appInfo.setLogopicpath(logoPicPath);
        int res=appInfoService.insertApp(appInfo);
        if(res==1){
            try {
            file.transferTo(new File(logoLocPath));
           } catch (IOException e) {
               model.addAttribute("fileUploadError","上传失败,请重试!");
           }
          return "redirect:/dev/applist";
       }else{
            return "developer/appinfoadd";
       }
    }


    @RequestMapping("{appid}/sale.json")
    @ResponseBody
    public String sale(@PathVariable Long appid){
       AppInfo appInfo= appInfoService.findAppOne(appid);
        if(appInfo.getStatus()==4){
            appInfo.setStatus(5L);
        }else{
            appInfo.setStatus(4L);
        }
        int res=appInfoService.upStatusSale(appInfo);
        if(res==1){
            return "success";
        }else{
            return "failed";
        }
    }
}
