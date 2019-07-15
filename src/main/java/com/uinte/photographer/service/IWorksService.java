package com.uinte.photographer.service;

import java.util.List;
import java.util.Map;

import com.uinte.common.service.IService;
import com.uinte.model.TWorks;
import com.uinte.vo.WorksVO;

/**
 * 
 * @author hzc 2017年2月12日
 *
 */
public interface IWorksService extends IService<TWorks> {

	List<Map<String,Object>> getWorksByPhotographerId(Integer id);

	WorksVO getWorks(Integer id);
	

	
}
