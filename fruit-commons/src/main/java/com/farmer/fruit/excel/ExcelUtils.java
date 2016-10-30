package com.farmer.fruit.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzhi on 2016/7/18.
 */
public class ExcelUtils {
    public static List<Map<String, Object>> loadExcel2003Info(String xlsPath) throws IOException {
        List<Map<String, Object>> temp = null;
        FileInputStream fileIn = new FileInputStream(xlsPath);
        Workbook wb0 = new HSSFWorkbook(fileIn);
        Sheet sheet = wb0.getSheetAt(0);
        temp = getExcelContent(sheet);
        fileIn.close();
        return temp;
    }
    public static List<Map<String, Object>> loadExcel2007Info(String xlsPath) throws IOException {
        InputStream fs = new FileInputStream(xlsPath); //获取存在的excel文件
        XSSFWorkbook xs = new XSSFWorkbook(fs);
       // SXSSFWorkbook wb = new SXSSFWorkbook(xs); //写内容
        XSSFSheet sheet = xs.getSheetAt(0);
        List<Map<String, Object>> temp =getExcelContent(sheet);
        fs.close();
        return temp;
    }
    private static List<Map<String, Object>> getExcelContent(Sheet sheet) {
        List<Map<String, Object>> temp = new ArrayList<Map<String, Object>>();
        for (Row r : sheet) {
            if (r.getRowNum() < 1) {
                continue;
            }
            Map<String, Object> rowMap = new HashMap<String, Object>();
            for(int i=0;i<r.getLastCellNum();i++){
                rowMap.put("ID"+i, getCellValue(r.getCell(i)));
            }
            temp.add(rowMap);
        }
        return temp;
    }
    private static Object getCellValue(Cell cell) {
        if(cell == null){
            return null;
        }
        Object inputValue;
        int type = cell.getCellType();
        if (type == Cell.CELL_TYPE_NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) {
                inputValue = cell.getDateCellValue();
            } else {
                inputValue = cell.getNumericCellValue();
            }
        } else if (type == Cell.CELL_TYPE_STRING) {
            inputValue = cell.getStringCellValue();
        } else {
            inputValue = null;
        }
        return inputValue;
    }

    public static List<Map<String, Object>> loadCVSInfo(String xlsPath) throws IOException {
        List<Map<String, Object>> fileContentList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(xlsPath));
        String contentStr = null;
        int index = 0;
        while ((contentStr = br.readLine()) != null) {
            if(index >0) {
                fileContentList.add(getMapFromStr(contentStr));
            }
            index ++;
        }
        return fileContentList;
    }
    private static Map<String,Object> getMapFromStr(String contentStr){
        String[] contentArray = contentStr.split(",");
        Map<String, Object> content = new HashMap<>();
        for(int i=0;i<contentArray.length;i++){
            content.put("ID"+i, contentArray[i]);
        }
        return content;
    }
    @Test
    public void test() {
        try {
            //List<Map<String, Object>> temp = ExcelUtils.loadExcel2003Info("H:/sangepg_document/record123.xls");
            List<Map<String, Object>> temp = ExcelUtils.loadCVSInfo("H:/sangepg_document/record123.csv");
            int size = temp.size();
            System.out.print(temp.get(0).get("ID2"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
