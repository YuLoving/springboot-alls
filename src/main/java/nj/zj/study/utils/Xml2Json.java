package nj.zj.study.utils;

import net.sf.json.JSONObject;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by qinghua on 2016/10/24.
 */
public class Xml2Json {

    /**
     * 转换一个xml格式的字符串到json格式
     *
     * @param xml
     *            xml格式的字符串
     * @return 成功返回json 格式的字符串;失败反回null
     * @throws IOException 
     * @throws JDOMException 
     */
    @SuppressWarnings("unchecked")
    public static  String xml2JSON(String xml) throws JDOMException, IOException {
        JSONObject obj = new JSONObject();
        InputStream is = new ByteArrayInputStream(xml.getBytes("utf-8"));
        SAXBuilder sb = new SAXBuilder();
        Document doc = sb.build(is);
        Element root = doc.getRootElement();
        obj.put(root.getName(), iterateElement(root));
        return obj.toString();
    }

    /**
     * 转换一个xml格式的字符串到json格式
     *
     * @param file
     *            java.io.File实例是一个有效的xml文件
     * @return 成功反回json 格式的字符串;失败反回null
     */
    @SuppressWarnings("unchecked")
    public static String xml2JSON(File file) {
        JSONObject obj = new JSONObject();
        try {
            SAXBuilder sb = new SAXBuilder();
            Document doc = sb.build(file);
            Element root = doc.getRootElement();
            obj.put(root.getName(), iterateElement(root));
            return obj.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 一个迭代方法
     *
     * @param element
     *            : org.jdom.Element
     * @return java.util.Map 实例
     */
    @SuppressWarnings("unchecked")
    private static Map iterateElement(Element element) {
        List jiedian = element.getChildren();
        Element et = null;
        Map obj = new HashMap();
        List list = null;
        for (int i = 0; i < jiedian.size(); i++) {
            list = new LinkedList();
            et = (Element) jiedian.get(i);
            if (et.getTextTrim().equals("")) {
                if (et.getChildren().size() == 0)
                    continue;
                if (obj.containsKey(et.getName())) {
                    list = (List) obj.get(et.getName());
                }
                list.add(iterateElement(et));
                obj.put(et.getName(), list);
            } else {
                if (obj.containsKey(et.getName())) {
                    list = (List) obj.get(et.getName());
                }
                list.add(et.getTextTrim());
                obj.put(et.getName(), list.get(0));
            }
        }
        return obj;
    }

    // 测试
    public static void main(String[] args) throws Exception {
    	String xml="<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" + 
				"<PSG_certIDverify xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://xiaoher.jzdata.com/\">\r\n" + 
				"    <ErrorRes>\r\n" + 
				"        <Err_code>200</Err_code>\r\n" + 
				"        <Err_content>身份证与姓名一致</Err_content>\r\n" + 
				"    </ErrorRes>\r\n" + 
				"</PSG_certIDverify>";
    	
    		String string = xml2JSON(xml);
    		System.out.println(string);
    	
    	
    	
    }
}
