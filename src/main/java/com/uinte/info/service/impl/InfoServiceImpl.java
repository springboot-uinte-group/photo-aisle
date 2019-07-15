package com.uinte.info.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.uinte.common.service.BaseService;
import com.uinte.info.service.IInfoService;
import com.uinte.model.TInfomation;

import tk.mybatis.mapper.entity.Example;

/**
 * 
 * @author hzc 2017年2月12日
 *
 */
@Service(value = "infoService")
public class InfoServiceImpl extends BaseService<TInfomation> implements IInfoService {

	@Override
	public List<TInfomation> getAllInfo() {
		Example example = new Example(TInfomation.class);
		example.createCriteria().andCondition("status=",0);
		return mapper.selectByExample(example);
	}

}
