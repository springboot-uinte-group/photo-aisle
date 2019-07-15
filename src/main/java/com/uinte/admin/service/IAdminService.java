package com.uinte.admin.service;

import com.uinte.common.service.IService;
import com.uinte.model.TAdmin;

/**
 * 
 * @author hzc 2017年2月12日
 *
 */
public interface IAdminService extends IService<TAdmin> {
	
	TAdmin login(TAdmin admin);

}
