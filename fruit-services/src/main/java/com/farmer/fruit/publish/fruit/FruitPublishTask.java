package com.farmer.fruit.publish.fruit;

import com.farmer.fruit.interfaces.task.Task;
import com.farmer.fruit.models.Constants;
import com.farmer.fruit.models.fruit.Fruit;
import com.farmer.fruit.utils.StringUtils;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzhi on 2016/7/28.
 */
public class FruitPublishTask implements Task<Fruit> {
    FreeMarkerConfigurer freeMarkerConfigurer;
    String rootPath;

    @Override
    public String getTaskName() {
        return "publish static html task";
    }
    @Override
    public void execute(Fruit fruit) {
        //调用freemarker 发布静态页面
        String templateName = fruit.getType().toLowerCase() + "_detail.ftl";
        Writer out = null;
        try {
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
            String fileName = fruit.getFruitCode() + ".html";
            String htmlFilePath = rootPath +"/"+ fruit.getFarmerId() + "/" + StringUtils.getYear()+ "/" + fruit.getId() / Constants.IMAGES_RANGE_INDEX + "/" + fileName;
            File htmlFile = new File(htmlFilePath);
            if (!htmlFile.getParentFile().exists()) {
                htmlFile.getParentFile().mkdirs();
            }
            if (!htmlFile.exists()) {
                htmlFile.createNewFile();
            }
            Map<String, Fruit> map = new HashMap<String, Fruit>();
            map.put("fruit", fruit);
            out = new OutputStreamWriter(new FileOutputStream(htmlFilePath), "UTF-8");
            template.process(map, out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public FreeMarkerConfigurer getFreeMarkerConfigurer() {
        return freeMarkerConfigurer;
    }

    public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
        this.freeMarkerConfigurer = freeMarkerConfigurer;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }
}
