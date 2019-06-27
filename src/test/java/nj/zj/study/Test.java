package nj.zj.study;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import nj.zj.study.utils.http.HttpRequestUtils;

/**

* <p>Description: </p>  

* @author ZTY  

* @date 2019年6月25日  

*/
public class Test {
	public static void main(String[] args) {
		String url="http://119.18.195.171/gsquery/companychange";


		String cname="北京真维嘉真空设备有限公司";

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("cname", cname);
	/*
		byte[] https = HttpsStatusUtil.postHttp2(url, jsonObject.toString(), "utf-8",50000);
		String dataStr = new String(https,"utf-8");*/
		JSONObject json = HttpRequestUtils.httpPost(url, jsonObject);


		//JSONObject json = JSONObject.parseObject(dataStr);
		JSONArray array=null;
		if(200==json.getInteger("code")) {
			array = json.getJSONArray("result");
			if(array.isEmpty()) {
				System.out.println("00000000000000000");
			}

		}/*else if() {
			reyrun.///
		}ele*/

		System.out.println(array);
	}
}
