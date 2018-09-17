package com.anl.user.service;

import com.anl.user.persistence.po.WxAccessToken;
/** 
 * 类名: WxAccessTokenService
 * 创建日期: 
 * 功能描述: 
 */
public interface WxAccessTokenService extends BaseService<WxAccessToken> {
    WxAccessToken getWxAccessTokenByAppId(String appId);
}