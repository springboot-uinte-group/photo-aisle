package com.uinte.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UploadTools {

	public static final ReentrantLock lock = new ReentrantLock();

	private static final Logger logger = LoggerFactory.getLogger(UploadTools.class);

	public UploadTools() {

	}

	/**
	 * 获取根目录
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String getRootDirectory() throws FileNotFoundException {
		File direc = null;
		try {
			direc = new File(StaticConfigurationItem.UPLOAD_BOOT_DIR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (direc == null) {
			throw new FileNotFoundException(StaticConfigurationItem.UPLOAD_BOOT_DIR + "无效的根目录");
		}
		if (!direc.isDirectory()) {
			throw new FileNotFoundException("不是有效目录");
		}
		direc = null;
		return StaticConfigurationItem.UPLOAD_BOOT_DIR;
	}

	/**
	 * 判断多级路径是否存在，不存在就创建
	 * 
	 * @throws IOException
	 * 
	 */
	public static String createDirectory(String direcPath) throws IOException {
		String paths[] = { "" };
		String tempPath = new File(direcPath).getCanonicalPath();
		paths = tempPath.split("\\\\");
		if (paths.length == 1) {
			paths = tempPath.split(File.separator);
		}
		String root = paths[0];
		for (int i = 0; i < paths.length - 1; i++) {
			root = root + File.separator + paths[i + 1];
			File dirFile = new File(root);
			lock.lock();
			if (!dirFile.exists()) {
				dirFile.mkdir();
				logger.info("成功创建目录：" + dirFile.getCanonicalFile());
			}
			lock.unlock();
		}
		return StringUtils.join(paths, "\\");
	}
	
	public static String subdirectory(String subDirecPath) {
		if(subDirecPath.indexOf("\\") == -1) {
			return "\\" + subDirecPath;
		}
		return subDirecPath;
	}
	
	/**
	 * 固定根目录
	 * @param subDirecPath
	 * @return
	 * @throws IOException
	 */
	public static String createDirectoryByRoot(String subDirecPath) throws IOException {
		String rootpath = getRootDirectory();
		String direcPath = rootpath + subdirectory(subDirecPath);
		String paths[] = { "" };
		String tempPath = new File(direcPath).getCanonicalPath();
		paths = tempPath.split("\\\\");
		if (paths.length == 1) {
			paths = tempPath.split(File.separator);
		}
		String root = paths[0];
		for (int i = 0; i < paths.length - 1; i++) {
			root = root + File.separator + paths[i + 1];
			File dirFile = new File(root);
			lock.lock();
			if (!dirFile.exists()) {
				dirFile.mkdir();
				logger.info("成功创建目录：" + dirFile.getCanonicalFile());
			}
			lock.unlock();
		}
		return StringUtils.join(paths, "\\");
	}
	
	/**
	 * 删除文件
	 * @param subDirecPath
	 * @param fileName
	 */
	public static void deleteFile(String subDirecPath, String fileName) {
		try {
			String root = getRootDirectory();
			String path = root + subDirecPath + File.separator + fileName;
			File file = new File(path);
			synchronized (path) {
				if (file.exists()) {
					file.delete();
				}
			}
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		}
	}

	public static void main(String[] args) {
//		createDirectory("F:\\temp\\123\\sdf\\sdf\\43tg\\txt");
//		createDirectoryByRoot("\\0txt\\dfsdf");
//		System.out.println(subdirectory("\\getsubpath"));
	}

}
