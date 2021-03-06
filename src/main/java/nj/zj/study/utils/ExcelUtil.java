package nj.zj.study.utils;


import lombok.extern.slf4j.Slf4j;
import nj.zj.study.model.ExcelData;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static org.apache.poi.ss.usermodel.CellType.*;

/**
 * @author ZTY
 * @title: ExcelUtil
 * @description: 对Excel进行导入导出的工具类
 * @date 2019/5/1317:27
 */
@Slf4j
public class ExcelUtil {
    /**
     * 方法名：exportExcel
     * 功能：导出Excel
     * 描述：
     * 创建人：zty
     * 创建时间：2019/5/1317:27
     * 修改人：
     * 修改描述：
     * 修改时间：
     */
    public static void exportExcel(HttpServletResponse response, HttpServletRequest request,ExcelData data) {
        log.info("导出解析开始，fileName:{}",data.getFileName());
        try {
            //实例化HSSFWorkbook
            HSSFWorkbook workbook = new HSSFWorkbook();
            //创建一个Excel表单，参数为sheet的名字
            HSSFSheet sheet = workbook.createSheet("sheet");
            //设置表头
            setTitle(workbook, sheet, data.getHead());
            //设置单元格并赋值
            //setData(sheet, data.getData());
            setData(workbook,sheet, data.getData());
            //设置浏览器下载
            setBrowser(response, request,workbook, data.getFileName());
            log.info("导出解析成功!");
        } catch (Exception e) {
            log.info("导出解析失败!");
            e.printStackTrace();
        }
    }

    /**
     * 方法名：setTitle
     * 功能：设置表头
     * 描述：
     * 创建人：zty
     * 创建时间：2019/5/1317:27
     * 修改人：
     * 修改描述：
     * 修改时间：
     */
    private static void setTitle(HSSFWorkbook workbook, HSSFSheet sheet, String[] str) {
        try {
            HSSFRow row = sheet.createRow(0);
            //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
            for (int i = 0; i <= str.length; i++) {
                sheet.setColumnWidth(i, 15 * 256);
            }
            //设置为居中加粗,格式化时间格式
            HSSFCellStyle style = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
           /* //设置单元格格式为"文本"
            HSSFDataFormat format = workbook.createDataFormat();
            style.setDataFormat(format.getFormat("@"));*/
            //创建表头名称
            HSSFCell cell;
            for (int j = 0; j < str.length; j++) {
                cell = row.createCell(j);
                cell.setCellValue(str[j]);
                cell.setCellStyle(style);
            }

        } catch (Exception e) {
            log.info("导出时设置表头失败！");
            e.printStackTrace();
        }
    }

    /**
     * 方法名：setData
     * 功能：表格赋值
     * 描述：
     * 创建人：zty
     * 创建时间：2019/5/1317:27
     * 修改人：
     * 修改描述：
     * 修改时间：
     */
    private static void setData( HSSFWorkbook workbook,HSSFSheet sheet, List<String[]> data) {
        try{
            int rowNum = 1;
                if(data!=null && !data.isEmpty()) {
                    for (int i = 0; i < data.size(); i++) {
                        HSSFRow row = sheet.createRow(rowNum);
                        for (int j = 0; j < data.get(i).length; j++) {
                            row.createCell(j).setCellValue(data.get(i)[j]);
                        }
                        rowNum++;
                    }
                }else{
                    //设置单元格格式为"文本"
                    HSSFCellStyle textStyle = workbook.createCellStyle();
                    HSSFDataFormat format = workbook.createDataFormat();
                    textStyle.setDataFormat(format.getFormat("@"));
                    sheet.setDefaultColumnStyle(2,textStyle);
                }
            log.info("表格赋值成功！");
        }catch (Exception e){
            log.info("表格赋值失败！");
            e.printStackTrace();
        }
    }

    /**
     * 方法名：setBrowser
     * 功能：使用浏览器下载
     * 描述：
     * 创建人：zty
     * 创建时间：2019/5/1317:27
     * 修改人：
     * 修改描述：
     * 修改时间：
     */
    private static void setBrowser(HttpServletResponse response, HttpServletRequest request, HSSFWorkbook workbook, String fileName) {
        try {
            //清空response
            response.reset();
            //设置response的Header
            String encode = URLEncoder.encode(fileName, "UTF-8");
            //response.addHeader("Content-Disposition", "attachment;filename=" + encode);
            /**
             * 处理火狐不兼容
             */
            String agent = request.getHeader("USER-AGENT").toLowerCase();
            response.setCharacterEncoding("utf-8");
            if (agent.contains("firefox")) {
                response.addHeader("Content-Disposition", "attachment;filename="+ new String(encode.getBytes("GB2312"),"ISO-8859-1"));
            } else {
                response.addHeader("content-disposition", "attachment;filename=" + encode );
            }


            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel");
            //将excel写入到输出流中
            workbook.write(os);
            os.flush();
            os.close();
            log.info("设置浏览器下载成功！");
        } catch (Exception e) {
            log.info("设置浏览器下载失败！");
            e.printStackTrace();
        }

    }


    /**
     * 方法名：importExcel
     * 功能：导入
     * 描述：
     * 创建人：zty
     * 创建时间：2019/5/1317:27
     * 修改人：
     * 修改描述：
     * 修改时间：
     */
    public static List<Object[]> importExcel(MultipartFile fileName) {
        log.info("导入解析开始，fileName:{}",fileName);
        try {
            List<Object[]> list = new ArrayList<>();
            //InputStream inputStream = new FileInputStream(fileName);
            InputStream inputStream = fileName.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            //获取sheet的行数
            int rows = sheet.getPhysicalNumberOfRows();
            for (int i = 0; i < rows; i++) {
                //过滤表头行
                if (i == 0) {
                    continue;
                }
                //获取当前行的数据
                Row row = sheet.getRow(i);
                Object[] objects = new Object[row.getPhysicalNumberOfCells()];
                int index = 0;
                for (Cell cell : row) {
                    if (cell.getCellType().equals(NUMERIC)) {
                        objects[index] = (long) cell.getNumericCellValue();
                    }
                    if (cell.getCellType().equals(STRING)) {
                        objects[index] = cell.getStringCellValue();
                    }
                    if (cell.getCellType().equals(BOOLEAN)) {
                        objects[index] = cell.getBooleanCellValue();
                    }
                    if (cell.getCellType().equals(ERROR)) {
                        objects[index] = cell.getErrorCellValue();
                    }
                    index++;
                }
                list.add(objects);
            }
            log.info("导入文件解析成功！");
            return list;
        }catch (Exception e){
            log.info("导入文件解析失败！");
            e.printStackTrace();
        }
        return null;
    }

    //测试
    public static void main(String[] args) {
        try {
            //导入测试
           /* String fileName = "f:/test.xlsx";
            List<Object[]> list = importExcel(fileName);
            for (int i = 0; i < list.size(); i++) {
                User user = new User();
                user.setId((Integer) list.get(i)[0]);
                user.setYourname((String) list.get(i)[1]);
                user.setAge((Integer) list.get(i)[2]);
                System.out.println(user.toString());
            }*/
            //导出测试

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
