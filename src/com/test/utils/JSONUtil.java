package com.test.utils;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @author 1202211 jsonπ§æﬂ¿‡
 */
public class JSONUtil {

	public static void getJSON(HttpServletResponse response, Object object) {

		JSONArray jsons = JSONArray.fromObject(object);
		response.setContentType("application/json; charset=utf-8");
		response.setContentType("json");
		try {
			PrintWriter out = response.getWriter();
			out.print(jsons);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String changeJSON(Object object) {
		JSONArray json = JSONArray.fromObject(object);
		String str = json.toString();
		return str;
	}

	public static Object changeObjerct(String str, Object object) {
		JSONObject json = JSONObject.fromObject(str);
		return JSONObject.toBean(json, object.getClass());

	}
}
