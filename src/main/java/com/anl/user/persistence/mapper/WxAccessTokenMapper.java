package com.anl.user.persistence.mapper;

import com.anl.user.persistence.mapper.BaseMapper;
import com.anl.user.persistence.po.WxAccessToken;
/** 
 * 类名: WxAccessToken
 * 创建日期: 
 * 功能描述: 
 */
public interface WxAccessTokenMapper extends BaseMapper<WxAccessToken> {
    WxAccessToken getWxAccessTokenByAppId(String appId);

}