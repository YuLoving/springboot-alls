package nj.zj.study.utils;

import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * 姓名,身份证,手机号参数校验工具类
 * Created by chenjian on 2017/1/9.
 */
public class CheckParamUtil {
    /**
     * 身份证校验规则最后一位是字母则必须大写,长度15或者18位
     *
     * @param idCard
     * @return
     */
    public static boolean checkIdCard(String idCard) {
        //非空判断
        if (StringUtils.isEmpty(idCard)) {
            return true;
        }
        idCard = idCard.toUpperCase();
        //身份证号码如果最后一位是字母,必须是大写
        String regex = "[1-9]\\d{13,16}[X|0-9]{1}";
        if (idCard.trim().length() != 15 && idCard.trim().length() != 18) {
            return true;
        }
        if(!new IdcardValidator(idCard.trim().toUpperCase()).validate()){
            return true;
        }
        if (Pattern.matches(regex, idCard)==false) {
            //如果最后一位是字母且不是大写,校验不通过
            return true;
        }else {
            //规则匹配,校验通过
            return false;
        }
    }

    /**
     * 手机号码校验
     * @param phone
     * @return
     */
    public static boolean checkPhone(String phone) {
        if(StringUtils.isEmpty(phone)){
            return true;
        }
        //手机号码11位,并且只能是数字
        String regex = "^[1][3,4,5,6,7,8,9][0-9]{9}$";
        if (Pattern.matches(regex, phone) == false) {
            //号码不合法
            return true;
        } else {
            //号码合法
            return false;
        }
    }


    /**
     * 1.可以是中文
     2.可以是英文，允许输入点（英文名字中的那种点）， 允许输入空格
     3.中文和英文不能同时出现
     4.长度在20个字符以内
     * @param name
     * @return
     */
    public static Boolean checkName(String name) {
        if(StringUtils.isEmpty(name)){
            return true;
        }
        String regex="^([\\u4e00-\\u9fa5]{1,20}|[a-zA-Z\\.\\s]{1,20})$";
        if (Pattern.matches(regex, name) == false) {
            //不合法
            return true;
        } else {
            //合法
            return false;
        }
    }

}
