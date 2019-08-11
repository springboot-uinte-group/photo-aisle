package com.uinte.common.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uinte.common.util.OperationFileUtil;
import com.uinte.common.util.UploadTools;

/**
 * 
 * @author admin 2017年2月12日
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/imgview")
public class PreviewController {

	private static final Logger logger = LoggerFactory.getLogger(PreviewController.class);

	/**
	 * 添加标签
	 * 
	 * @param Label
	 * @return
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "preview", method = RequestMethod.GET)
	public void preview(String path, String fileName, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
//		Long s = System.currentTimeMillis();
		response.setContentType("text/html; charset=UTF-8");
//		response.setContentType("image/jpeg");
		response.setContentType("image/jpeg/jpg/png/gif/bmp/tiff/svg");
		ServletOutputStream out = null;
		String root = UploadTools.getRootDirectory();
		String realPath = root + File.separator + path + File.separator + fileName;
		logger.info(realPath);
		out = response.getOutputStream();
		byte[] fileBytes = OperationFileUtil.downloadFile(realPath);
		out.write(fileBytes);
//		Long e = System.currentTimeMillis();
//		System.out.println("excTime:"+ (e-s));
	}

	/**
	 * @deprecated
	 * @param path
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "previewSpe", method = RequestMethod.GET)
	public void previewSpe(String path, HttpServletRequest request, HttpServletResponse response) {
		Long s = System.currentTimeMillis();
		response.setContentType("text/html; charset=UTF-8");
//		response.setContentType("image/jpeg");
		response.setContentType("image/jpeg/jpg/png/gif/bmp/tiff/svg");
		FileInputStream fis = null;
		OutputStream outPut = null;
		try {
			String root = UploadTools.getRootDirectory();
			String realPath = root + path;
			fis = new FileInputStream(realPath);
			outPut = response.getOutputStream();
			int count = 0;
			byte[] buffer = new byte[1024 * 1024];
			while ((count = fis.read(buffer)) != -1) {
				outPut.write(buffer, 0, count);
			}
			outPut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (outPut != null) {
					outPut.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Long e = System.currentTimeMillis();
		System.out.println("spe-excTime:"+ (e-s));
	}

}
