package com.uinte.banner.service.impl;


import org.springframework.stereotype.Service;

import com.uinte.banner.service.IBannerService;
import com.uinte.common.service.BaseService;
import com.uinte.model.TBanner;

/**
 * 
 * @author hzc 2017年2月12日
 *
 */
@Service(value = "bannerService")
public class BannerServiceImpl extends BaseService<TBanner> implements IBannerService {

}
