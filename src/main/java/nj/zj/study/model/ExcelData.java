package nj.zj.study.model;

import lombok.Data;

import java.util.List;

/**
 * @author ZTY
 * @title: ExcelData
 * @description: 对应Excel操作的实体类
 * @date 2019/5/1317:29
 */
@Data
public class ExcelData {
    private String FileName;
    private String[] Head;
    private List<String[]> Data;
}