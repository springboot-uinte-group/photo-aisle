package com.uinte.spots.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.uinte.common.service.BaseService;
import com.uinte.model.TSpots;
import com.uinte.spots.service.ISpotsService;

import tk.mybatis.mapper.entity.Example;

/**
 * 
 * @author hzc 2017年2月12日
 *
 */
@Service(value = "spotsService")
public class SpotsServiceImpl extends BaseService<TSpots> implements ISpotsService {

	@Override
	public List<TSpots> getAllSpots() {
		Example example = new Example(TSpots.class);
		example.createCriteria().andCondition("status=",0);
		
		return mapper.selectByExample(example);
	}

	
}
