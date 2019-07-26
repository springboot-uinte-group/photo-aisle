package com.uinte.photographer.service;

import java.util.List;

import com.uinte.common.service.IService;
import com.uinte.model.TLabel;

/**
 * 
 * @author admin 2017年2月12日
 *
 */
public interface ILabelService extends IService<TLabel> {
	
	List<TLabel> getAllLabel();

}
