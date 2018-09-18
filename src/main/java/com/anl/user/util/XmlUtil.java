package com.anl.user.util;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class XmlUtil {

	public static String getXmlFromMap(Map<String, Object> params, String root) {
		JSON JSON = JSONObject.fromObject(params);
		XMLSerializer xmlSerializer = new XMLSerializer();
		xmlSerializer.setRootName(root);
		xmlSerializer.setTypeHintsEnabled(false);
		xmlSerializer.setTypeHintsCompatibility(false);
		String xml = xmlSerializer.write(JSON);
		return xml;
	}

	public static String getJsonFromXml(String xml) {
		XMLSerializer xmlSerializer = new XMLSerializer();
		JSONObject json = (JSONObject) xmlSerializer.read(xml);
		return json.toString();
	}

	@SuppressWarnings("unchecked")
	public static <T> T fromXml(String xml, Class<T> clazz) {
		XMLSerializer xmlSerializer = new XMLSerializer();
		JSONObject json = (JSONObject) xmlSerializer.read(xml);
		return (T) JSONObject.toBean(json, clazz);
	}
	
	/**
	 * 
	 * @Author lianzhifei 2016年9月8日 deleteBOM 方法描述: 判断返回String中是否存在BOM头，若存在则去除
	 *         逻辑描述:
	 * @param value
	 * @return
	 */
	public static String deleteBOM(String value) {
		String tmpStr2 = new String(value.substring(0, 1));
		if (strtoHex(tmpStr2).equals("0xfeff")) {
			value = new String(value.getBytes(), 3, value.getBytes().length - 3, Charset.defaultCharset());
		}
		return value;
	}
	
	/**
	 * 
	 * @Author lianzhifei 2016年9月8日 strtoHex 方法描述: 字符串转换为16进制 逻辑描述:
	 * @param s
	 * @return
	 */
	public static String strtoHex(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return "0x" + str;// 0x表示十六进制
	}
	public  static  void main(String args[])throws  Exception{
		Map<String,Object> map=new HashMap<>();
		map.put("ToUserName","aaaaaaaa");
		map.put("FromUserName","vvvvvvvvv");
		map.put("CreateTime", System.currentTimeMillis()/1000);
		map.put("MsgType","text");
		map.put("Content","哈哈,我成功回复了你");
		String xml =getXmlFromMap(map,"xml");



		System.out.println(xml.substring(xml.indexOf("<xml>")));
	}
}
