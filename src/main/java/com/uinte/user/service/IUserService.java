package com.uinte.user.service;

import com.uinte.common.service.IService;
import com.uinte.model.TUser;

/**
 * 
 * @author hzc 2017年2月12日
 *
 */
public interface IUserService extends IService<TUser> {
	
	TUser login(TUser user);

	int register(TUser user);
	
	boolean checkUserByName(String name);
	
	int updatePassword(TUser user);
}
