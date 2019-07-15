package com.uinte.info.service;

import java.util.List;

import com.uinte.common.service.IService;
import com.uinte.model.TInfomation;

/**
 * 
 * @author hzc 2017年2月12日
 *
 */
public interface IInfoService extends IService<TInfomation> {
	
	List<TInfomation> getAllInfo();
}
