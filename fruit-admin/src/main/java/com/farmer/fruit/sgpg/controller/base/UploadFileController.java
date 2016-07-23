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
@RequestMapping(value = "/admin/commons/upload")
public class UploadFileController {
    static final String root_img =ReloadableConfig.getStringProperty("upload.img.path");

    @RequestMapping(value = "/images")
    public String uploadQuestionPicture(@RequestParam(value = "upload", required = true) MultipartFile file, String type, String CKEditorFuncNum, HttpServletResponse response) {
        StringBuffer path = new StringBuffer();
        if ("image".equals(type)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
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
                String resultPath = ReloadableConfig.getStringProperty("web_path","http://s.sangepg.com/")+"images/" + path + fileName.replace("-", "");
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


    public static String uploadFile(String rootPath ,MultipartFile file, String path, String fileName) {
        File f = new File(rootPath + "/" + path);
        if (!f.exists()) {
            f.mkdirs();
        }
        f = new File(rootPath + "/" + path, fileName);
        // 保存
        try {
            file.transferTo(f);
            return ReloadableConfig.getStringProperty("upload.img.path","I://temp")+"/" + path + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }
}


