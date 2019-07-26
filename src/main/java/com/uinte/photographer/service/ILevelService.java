package com.uinte.photographer.service;

import java.util.List;

import com.uinte.common.service.IService;
import com.uinte.model.TLevel;

/**
 * 
 * @author admin 2017年2月12日
 *
 */
public interface ILevelService extends IService<TLevel> {

	List<TLevel> getAllLevel();

}
