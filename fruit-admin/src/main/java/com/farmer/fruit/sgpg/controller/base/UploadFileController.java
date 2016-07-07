package com.farmer.fruit.sgpg.controller.base;

import com.farmer.fruit.utils.RandomStrUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping(value = "/commons/upload")
public class UploadFileController {
    static final String root_img =ReloadableConfig.getStringProperty("upload.img.path");
    static final String root_file = ReloadableConfig.getStringProperty("upload.file.path");
    static final String root_video = ReloadableConfig.getStringProperty("upload.video.path");

    @RequestMapping(value = "/question")
    public String uploadQuestionPicture(@RequestParam(value = "upload", required = true) MultipartFile file, String type, String CKEditorFuncNum, HttpServletResponse response) {
        StringBuffer path = new StringBuffer();
        if ("image".equals(type)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            path.append("question").append("/");
            path.append(calendar.get(Calendar.YEAR)).append("/");
            path.append(calendar.get(Calendar.MONTH) + 1).append("/");
            path.append(calendar.get(Calendar.DAY_OF_MONTH)).append("/");
            try {
                //String fileName = file.getOriginalFilename();
                String fileType = file.getContentType();
                if (!fileType.contains("image")) {
                    return "error";
                }
                String fileName = UUID.randomUUID() + "." + fileType.split("/")[1];
                uploadFile(root_img,file, path.toString(), fileName.replace("-", ""));
                String resultPath = ReloadableConfig.getStringProperty("staticFileDomain")+"/imgs/" + path + fileName.replace("-", "");
                resultPath = getReturnContent(CKEditorFuncNum, resultPath);
                PrintWriter out = response.getWriter();
                out.print(resultPath);
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }


    private String getReturnContent(String callback, String filePath) {
        StringBuffer sb = new StringBuffer();
        sb.append("<script type=\"text/javascript\">");
        sb.append("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + filePath + "','')");
        sb.append("</script>");
        return sb.toString();
    }

    @RequestMapping(value = "/image")
    public String uploadImage(@RequestParam(value = "upload", required = true) MultipartFile file) {
        String fileType = file.getContentType();
        if (!fileType.contains("image")) {
            return "error";
        }
        StringBuffer path = new StringBuffer();
        path.append("other").append("/");
        Calendar calendar = Calendar.getInstance();
        //calendar.setTime(new Date());
        path.append(calendar.get(Calendar.YEAR)).append("/");
        path.append(calendar.get(Calendar.MONTH) + 1).append("/");
        path.append(calendar.get(Calendar.DAY_OF_MONTH)).append("/");
        try {
            String fileName = RandomStrUtil.getRandomInt(10)+"."+fileType.split("/")[1];
            String resultPath = uploadFile(root_img,file, path.toString(), fileName);
            return resultPath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    //上传头像
    @RequestMapping(value = "/user/image",method = RequestMethod.POST)
    @ResponseBody
    public String uploadUserImages(@RequestParam(value = "upload", required = true) MultipartFile file, long userId) {
        String fileType = file.getContentType();
        if (!fileType.contains("image")) {
            return "error";
        }
        try{
            StringBuffer path = new StringBuffer();
            path.append("photo").append("/");
            path.append(userId/1000).append("/");
            String fileName =userId+"_"+RandomStrUtil.getRandomInt(10)+"."+fileType.split("/")[1];
            String resultPath = uploadFile(root_img,file, path.toString(), fileName);
            return resultPath;
        }catch (Exception e){
            return  "error";
        }

    }
    @RequestMapping(value = "/file",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> uploadFile(@RequestParam(value = "upload", required = true) MultipartFile file, String semesterCode, String teacherCode, String subjectCode) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        String fileType = file.getContentType();
        if (!fileType.contains("application")) {
            resultMap.put("flag",false);
            resultMap.put("msg","文件不符合要求");
            return resultMap;
        }
        try{
            StringBuffer path = new StringBuffer();
            path.append("files").append("/");
            path.append(semesterCode).append("/");
            path.append(teacherCode).append("/");
            path.append(subjectCode).append("/");
            String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),file.getOriginalFilename().length());
            if(".exe".equals(fileSuffix)){
                resultMap.put("flag",false);
                resultMap.put("msg","文件不符合要求");
                return resultMap;
            }
            long size = Long.parseLong(ReloadableConfig.getStringProperty("upload.file.size"));
            if(file.getSize() >size){
                resultMap.put("flag",false);
                resultMap.put("msg","文件过大，不能超过20M");
                return resultMap;
            }
            String fileName = subjectCode+"_"+RandomStrUtil.getRandomInt(10)+fileSuffix;
            String resultPath = uploadFile(root_file,file, path.toString(), fileName);
            resultMap.put("flag",true);
            resultMap.put("filePath",resultPath);
            resultMap.put("suffix",fileSuffix);
            resultMap.put("fileName",file.getOriginalFilename());
            return resultMap;
        }catch(Exception e){
            resultMap.put("flag",false);
            resultMap.put("msg","系统繁忙，请稍后再试！");
            return resultMap;
        }

    }
    //上传文件 ppt word excel
    //上传视频video
    @RequestMapping(value = "/video",method = RequestMethod.POST)
    @ResponseBody
    public String uploadQuestionPicture(@RequestParam(value = "upload", required = true) MultipartFile file,String semesterCode,String teacherCode, String subjectCode) {
        String fileType = file.getContentType();
        if (!fileType.contains("video")) {
            return "error";
        }
        String suffix = fileType.split("/")[1];
        if(!"mp4".equalsIgnoreCase(suffix)&&!"avi".equalsIgnoreCase(suffix)){
            return "error";
        }
        long size = file.getSize();
        if(size>1024*1024*1024){
            return "too_large";
        }
        StringBuffer path = new StringBuffer();
        path.append("videos").append("/");
        path.append(semesterCode).append("/");
        path.append(teacherCode).append("/");
        path.append(subjectCode).append("/");
        String fileName =subjectCode+"_760x428_600kbps_"+RandomStrUtil.getRandomInt(10)+"."+suffix;
        String resultPath = uploadFile(root_video,file, path.toString(), fileName);
        return resultPath;
    }
    public static String uploadFile(String rootPath ,MultipartFile file, String path, String fileName) {
        File f = new File(rootPath + "/" + path);
        if (!f.exists()) {
            f.mkdirs();
        }
        f = new File(rootPath + "/" + path, fileName);
        // 保存
        try {
            file.transferTo(f);
            return ReloadableConfig.getStringProperty("staticFileDomain")+"/" + path + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }
}


