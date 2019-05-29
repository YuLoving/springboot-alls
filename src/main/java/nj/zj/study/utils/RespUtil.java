package nj.zj.study.utils;

import java.util.Arrays;

import com.alibaba.fastjson.JSONObject;

public class RespUtil {

	public static JSONObject getResp(Integer code,String msg,Object... content) {
		JSONObject json = new JSONObject();
		json.put("code", code);
		json.put("msg", msg);
		json.put("content", content);
		return json;
	}
}
