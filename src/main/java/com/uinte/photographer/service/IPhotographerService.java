package com.uinte.photographer.service;

import java.text.ParseException;
import java.util.List;

import com.uinte.common.service.IService;
import com.uinte.model.TPhotographer;

/**
 * 
 * @author hzc 2017年2月12日
 *
 */
public interface IPhotographerService extends IService<TPhotographer> {

	List<String> selectByStartEnd(String start, String end) throws IllegalArgumentException, ParseException;
}
