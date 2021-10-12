package com.demo.javademo.io.excel.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Excel的操作工具类
 */
public class ExcelUtil {
    private static Logger log = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * 获取某个File文件对应的Workbook工作簿对象
     */
    public static Workbook gainWorkbook(File file) throws ExcelException {
        if (!isExcel(file)) {
            throw new ExcelException("文件不是Excel类型");
        }
        //如果文件不存在则新建
        if (!file.exists()) {
            try {
                OutputStream os = new FileOutputStream(file);
                Workbook workbook = isOlderEdition(file) ? new HSSFWorkbook() : new XSSFWorkbook();
                workbook.write(os);
                log.debug("文件不存在，新建该Excel文件");
                os.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            InputStream is = new FileInputStream(file);
            return isOlderEdition(file) ? new HSSFWorkbook(is) : new XSSFWorkbook(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断某个Excel文件是否是2003-2007通用旧版
     */
    private static boolean isOlderEdition(File file) {
        return file.getName().matches(".+\\.(?i)xls");
    }

    /**
     * 判断文件是否是一个Excel文件
     */
    private static boolean isExcel(File file) {
        String fileName = file.getName();
        String regXls = ".+\\.(?i)xls";
        String regXlsx = ".+\\.(?i)xlsx";
        return fileName.matches(regXls) || fileName.matches(regXlsx);
    }

    /**
     * 将某个对象的List转换为Excel工作簿
     */
    public static <E> Workbook createExcel(List<E> list, File file) {
        String sheetName = "default";
        if (list.size() == 0) {
            return null;
        }
        Workbook workbook = null;
        try {
            Class clazz = list.get(0).getClass();
            Field[] fields = clazz.getDeclaredFields();
            if (clazz.isAnnotationPresent(Excel.class)) {
                Excel excel = (Excel) clazz.getAnnotation(Excel.class);
                sheetName = excel.name();
            }
            workbook = gainWorkbook(file);
            Sheet sheet = workbook.createSheet(sheetName);
            //创建首行
            Row line = sheet.createRow(0);
            for (int k = 0; k < fields.length; k++) {
                Cell cell = line.createCell(k);
                String columnName = fields[k].getName();
                if (fields[k].isAnnotationPresent(Excel.class)) {
                    Excel excel = fields[k].getAnnotation(Excel.class);
                    columnName = excel.name();
                }
                cell.setCellValue(columnName);
            }
            //创建数据
            for (int i = 1; i <= list.size(); i++) {
                Row row = sheet.createRow(i);
                for (int j = 1; j <= fields.length; j++) {
                    Cell cell = row.createCell(j - 1);
                    String fieldName = fields[j - 1].getName();
                    String fieldFirstLetterUpper = fieldName.substring(0, 1).toUpperCase();
                    String prefix = "get";
                    if ("boolean".equals(fields[j - 1].getType().getName())) {
                        prefix = "is";
                    }
                    String methodName = prefix + fieldFirstLetterUpper + fieldName.substring(1);
                    Method method = clazz.getMethod(methodName);
                    cell.setCellValue(String.valueOf(method.invoke(list.get(i - 1))));
                }
            }
            log.debug("List读入完毕");
            OutputStream os = new FileOutputStream(file);
            workbook.write(os);
            os.close();
        } catch (ExcelException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }
}