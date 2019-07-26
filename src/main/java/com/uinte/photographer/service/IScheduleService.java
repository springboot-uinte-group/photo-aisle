package com.uinte.photographer.service;


import com.uinte.common.service.IService;
import com.uinte.model.TSchedule;
import com.uinte.vo.ScheduleVO;

/**
 * 
 * @author admin 2017年2月12日
 *
 */
public interface IScheduleService extends IService<TSchedule> {

	ScheduleVO getScheduleByPhotographerId(Integer photoer_id, String year, String month);
	

}
