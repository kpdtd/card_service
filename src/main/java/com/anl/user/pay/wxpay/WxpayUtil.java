package com.anl.user.pay.wxpay;

import com.anl.user.util.HttpClient;
import com.anl.user.util.MD5Util;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class WxpayUtil {
	/**
	 * 随机串
	 * @return
	 */
	public static String getNonceStr() {
		Random random = new Random();
		return MD5Util.getMD5String(String.valueOf(random.nextInt(10000)));
	}
	/**
	 * 时间戳
	 * @return
	 */
	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}
	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strXml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> parseToMap(String strXml) throws JDOMException, IOException {
		strXml = strXml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
		if(null == strXml || "".equals(strXml)) {
			return null;
		}
		Map<String,String> m = new HashMap<>();
		
		InputStream in = new ByteArrayInputStream(strXml.getBytes("UTF-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List<Element> list = root.getChildren();
		Iterator<Element> it = list.iterator();
		while(it.hasNext()) {
			Element e = it.next();
			String k = e.getName();
			String v = "";
			List<Element> children = e.getChildren();
			if(children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = getChildrenText(children);
			}
			m.put(k, v);
		}
		//关闭流
		in.close();
		return m;
	}
	
	/**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	private static String getChildrenText(List<Element> children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator<Element> it = children.iterator();
			while(it.hasNext()) {
				Element e = it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List<Element> list = e.getChildren();
				sb.append("<" + name + ">");
				if(!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		return sb.toString();
	}
	/**
	 * 只支持String,并且value非空
	 * @param map
	 * @return
	 */
	public static String parseToXml(Map<String,String> map){
		StringBuffer sb = new StringBuffer("<xml>");
		for(Map.Entry<String, String> entry : map.entrySet()){
			sb.append("<"+entry.getKey()+">");
			sb.append("<![CDATA["+entry.getValue()+"]]>");
			sb.append("</"+entry.getKey()+">");
		}
		sb.append("</xml>");
		return sb.toString();
	}
	
	/** 
     * POST提交XML对象 
     * @param xmlParams
     */  
    public static String postXmlClient(String url,String xmlParams) throws Exception {  
//    	return HttpUtils.postXmlEntity(url, xmlParams).getContent();
    	return HttpClient.postRequest(url, xmlParams);
    }  
}
