package com.uinte.info.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uinte.common.util.OperationFileUtil;
import com.uinte.common.util.StaticConfigurationItem;
import com.uinte.info.service.IInfoService;
import com.uinte.model.TBanner;
import com.uinte.model.TInfomation;
import com.uinte.vo.PageVO;
import com.uinte.vo.ReturnCodeType;
import com.uinte.vo.ReturnResult;

/**
 * 
 * @author admin 2017年2月12日
 *
 */
@Controller
@Scope("prototype")
public class InfoController {

	private static final Logger logger = LoggerFactory.getLogger(InfoController.class);
	private ReturnResult returnResult = new ReturnResult();

	@Resource(name = "infoService")
	private IInfoService infoService;

	/**
	 * 添加咨询
	 * 
	 * @param info
	 * @param HttpServletRequest
	 * @return
	 */
	@RequestMapping(value = "addInfo", method = RequestMethod.POST)
	@ResponseBody
	public ReturnResult addInfo(TInfomation info, HttpServletRequest request) {
		returnResult.setStatus(ReturnCodeType.FAILURE);
		try {

			String sub = StaticConfigurationItem.INFO;
			Map<String, String> map = OperationFileUtil.multiFileUpload(request, sub);
			String filePath = "";
			for (Map.Entry<String, String> entry : map.entrySet()) {
				filePath = entry.getValue();
			}
			filePath = filePath.replace(request.getServletContext().getRealPath("/"), "/");
			info.setPath(filePath);
			info.setCreatetime(new Date());
			infoService.insert(info);
			returnResult.setStatus(ReturnCodeType.SUCCESS);
		} catch (Exception e) {
			logger.error("新增info失败" + e);
		}
		return returnResult;

	}

	
	
	/**
	 * 修改info的title和content
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "updateInfo", method = RequestMethod.POST)
	@ResponseBody
	public ReturnResult updateInfo(TInfomation info) {
		returnResult.setStatus(ReturnCodeType.FAILURE);
		try {
			infoService.updateBySQL("UPDATE t_infomation SET title='" + info.getTitle() + "', content='"+info.getContent()+"', status="+info.getStatus()+" WHERE id=" + info.getId());
			returnResult.setStatus(ReturnCodeType.SUCCESS);
		} catch (Exception e) {
			logger.error("修改info失败" + e);
		}
		return returnResult;
	}
	
	/**
	 * 分页获取info
	 * @return
	 */
	@RequestMapping(value = "getInfoListByPage", method = RequestMethod.POST)
	@ResponseBody
	public ReturnResult getInfoListByPage(PageVO page) {
		returnResult.setStatus(ReturnCodeType.FAILURE);
		try {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			StringBuffer sql = new StringBuffer("SELECT DISTINCT * FROM t_infomation WHERE 1=1");
		
			
			List<Map<String, Object>> results = infoService.selectPageBySQL(sql.toString(), page.getPage() - 1,
					page.getRows());
			if (!results.isEmpty() && results != null) {
				int total = infoService.selectCount(new TInfomation());
				int rows = page.getRows();
				rows = rows == 0 ? 10 : rows;
				resultMap.put("total", (total % rows != 0 ? (total / rows + 1) : (total / rows)));
				resultMap.put("page", page.getPage());
				resultMap.put("records", total);
				resultMap.put("rows", results);
				returnResult.setStatus(ReturnCodeType.SUCCESS).setData(resultMap);
			}
		}catch (Exception e) {
			logger.error("分页获取info失败" + e);
		}
		return returnResult;
	}
	/**
	 * 根据获取id info
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getInfoById", method = RequestMethod.POST)
	@ResponseBody
	public ReturnResult getInfoById(Integer id) {
		returnResult.setStatus(ReturnCodeType.FAILURE);
		try {
				returnResult.setStatus(ReturnCodeType.SUCCESS).setData(infoService.selectByPrimaryKey(id));
		}catch (Exception e) {
			logger.error("根据获取info失败" + e);
		}
		return returnResult;
	}
	/**
	 * 获取所有启用的info
	 * @return
	 */
	@RequestMapping(value = "getAllInfo", method = RequestMethod.POST)
	@ResponseBody
	public ReturnResult getAllInfo() {
		returnResult.setStatus(ReturnCodeType.FAILURE);
		try {
			returnResult.setStatus(ReturnCodeType.SUCCESS).setData(infoService.getAllInfo());
		}catch (Exception e) {
			logger.error("根据获取启用info失败" + e);
		}
		return returnResult;
	}
	/**
	 * 获取所有最新6条启用的info
	 * @return
	 */
	@RequestMapping(value = "getSixInfo", method = RequestMethod.POST)
	@ResponseBody
	public ReturnResult getSixInfo() {
		returnResult.setStatus(ReturnCodeType.FAILURE);
		try {
			returnResult.setStatus(ReturnCodeType.SUCCESS).setData(infoService.selectBySQL("select * from t_infomation WHERE `status` = 0  ORDER BY id DESC limit 0,6"));
		}catch (Exception e) {
			logger.error("获取所有最新6条启用的info失败" + e);
		}
		return returnResult;
	}
	/**
	 * 富文本上头本地图片
	 * 
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@ResponseBody
	@RequestMapping(value = "info/upload")
	public Map<String, Object> upload(HttpServletRequest request) throws IllegalStateException, IOException {
		Map<String, String> map = OperationFileUtil.multiFileUpload(request,
				request.getServletContext().getRealPath("/") + "uploads/info/");
		String filePath = "";
		for (Map.Entry<String, String> entry : map.entrySet()) {
			filePath = entry.getValue();
		}
		filePath = filePath.replace(request.getServletContext().getRealPath("/"), "/");
		filePath = filePath.replaceAll("\\\\", "/");
		Map<String, Object> result = new HashMap<String,Object>();
		result.put("data", filePath);
		result.put("status", 0);
		return result;
	}
	
	/**
	 * 删除info
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "deleteInfoById", method = RequestMethod.POST)
	@ResponseBody
	public ReturnResult deleteInfoById(Integer id,HttpServletRequest request) {
		returnResult.setStatus(ReturnCodeType.FAILURE);
		try {
//			String filePath = request.getServletContext().getRealPath("/");
			TInfomation info = infoService.selectByPrimaryKey(id);
//			filePath +=info.getPath();
			OperationFileUtil.deleteFileByRoot(info.getPath());
			infoService.deleteByPrimaryKey(id);
			returnResult.setStatus(ReturnCodeType.SUCCESS);
		} catch (Exception e) {
			logger.error("删除info失败" + e);
		}
		return returnResult;
	}

}
