package com.uinte.spots.service;

import java.util.List;

import com.uinte.common.service.IService;
import com.uinte.model.TSpots;

/**
 * 
 * @author hzc 2017年2月12日
 *
 */
public interface ISpotsService extends IService<TSpots> {
	
	List<TSpots> getAllSpots();
}
