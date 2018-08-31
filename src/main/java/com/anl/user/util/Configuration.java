package com.anl.user.util;

/**
 * 这个类表示配置文件。
 * 
 * @version 1.0.0 2009-11-27
 *
 */
public class Configuration {

	public String smsUrl;
	public String smsUser;
	public String smsPass;
	public String smsId;
	public String appSecret;
	public String packageName;

	public String getSmsUrl() {
		return smsUrl;
	}

	public void setSmsUrl(String smsUrl) {
		this.smsUrl = smsUrl;
	}

	public String getSmsUser() {
		return smsUser;
	}

	public void setSmsUser(String smsUser) {
		this.smsUser = smsUser;
	}

	public String getSmsPass() {
		return smsPass;
	}

	public void setSmsPass(String smsPass) {
		this.smsPass = smsPass;
	}

	public String getSmsId() {
		return smsId;
	}

	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

}