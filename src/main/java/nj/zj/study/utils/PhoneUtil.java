package nj.zj.study.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author: limang
 * date: 2016/7/25 17:07
 */
public class PhoneUtil
{
    //电信号段 133 149 153 173 177 180 181 189 199
    private static final String[] TELECOM_PHONE_ARRY = {"199","133", "153", "180", "181", "189", "177", "173","149","191"};
    //联通号段 '130','131','132','155','156','185','186','176','175','166'
    private static final String[] UNICOM_PHONE_ARRY = {"166","130", "131", "132", "155", "156", "185", "186", "176","175"};
    //移动号段 134 135 136 137 138 139 147 150 151 152 157 158 159 172 178 182 183 184 187 188 198 148
    private static final String[] CMCC_PHONE_ARRY = {"148","198","134", "135", "136", "137", "138", "139", "150", "151", "152", "157", "158", "159", "182", "183", "184", "187", "188", "178", "147", "172"};
    //联通实时城市核验接口支持虚拟号段
    private static final String[] UNICOM_PHONE_ARRY2 = {"130","131", "132", "155", "156", "185", "186", "176", "175", "166","170","171"};
    //匹配手机号
    public static boolean isMobileNO(String mobiles) {

         Pattern p = Pattern.compile("^1[3,4,5,6,7,8,9]\\d{9}$");
         Matcher m = p.matcher(mobiles);
         return m.matches();
    }


    public static boolean isTelecomPhone(String phone)
    {
        if (null == phone || phone.trim().isEmpty())
        {
            return false;
        }
        boolean isMobileNo = isMobileNO(phone);
        if(!isMobileNo){
            return false;
        }

        for (String t : TELECOM_PHONE_ARRY)
        {
            if (phone.indexOf(t) == 0)
            {
                return true;
            }
        }
        return false;
    }


    public static boolean isUnicomPhone(String phone)
    {
        if (null == phone || phone.trim().isEmpty())
        {
            return false;
        }
        boolean isMobileNo = isMobileNO(phone);
        if(!isMobileNo){
            return false;
        }
        if(phone.indexOf("1718") == 0||phone.indexOf("1719")==0){//联通 不支持1718 1719号段
            return false;
        }
        for (String t : UNICOM_PHONE_ARRY)
        {
            if (phone.indexOf(t) == 0)
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isUnicomPhoneFF(String phone)
    {
        if (null == phone || phone.trim().isEmpty())
        {
            return false;
        }
        boolean isMobileNo = isMobileNO(phone);
        if(!isMobileNo){
            return false;
        }
        for (String t : UNICOM_PHONE_ARRY2)
        {
            if (phone.indexOf(t) == 0)
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isCmccPhone(String phone)
    {
        if (null == phone || phone.trim().isEmpty())
        {
            return false;
        }
        boolean isMobileNo = isMobileNO(phone);
        if(!isMobileNo){
            return false;
        }
        for (String t : CMCC_PHONE_ARRY)
        {
            if (phone.indexOf(t) == 0)
            {
                return true;
            }
        }
        return false;
    }

    public static boolean is198(String phone)
    {

            if (phone.indexOf("198") == 0)
            {
                return true;
            }

        return false;
    }

    /**
     * 1:移动 2：联通 3：电信  0：无结果 -1:手机号为空等异常情况
     * @param phone
     * @return
     */
    public static int getPhoneType(String phone)
    {

        if (null == phone || phone.trim().isEmpty()
                || phone.trim().length() != 11|| CheckParamUtil.checkPhone(phone))
        {
            return -1;
        }
        if (isCmccPhone(phone))
        {
            return 1;
        }
        if (isUnicomPhone(phone))
        {
            return 2;
        }
        if (isTelecomPhone(phone))
        {
            return 3;
        }
        return 0;
    }
}


