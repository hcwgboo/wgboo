package cn.jeeweb.core.utils.upload;

import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.core.utils.upload.exception.FileNameLengthLimitExceededException;
import cn.jeeweb.core.utils.upload.exception.InvalidExtensionException;
import cn.jeeweb.modules.common.oss.OSSClientUtils;
import com.alibaba.simpleimage.ImageRender;
import com.alibaba.simpleimage.SimpleImageException;
import com.alibaba.simpleimage.render.ReadRender;
import com.alibaba.simpleimage.render.ScaleParameter;
import com.alibaba.simpleimage.render.ScaleRender;
import com.alibaba.simpleimage.render.WriteRender;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.aspectj.util.FileUtil;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FileUploadUtils {

	// 默认大小 5M
	public static final long DEFAULT_MAX_SIZE = 5242880;
	
	public static final long COMPRESS_SIZE = 524800;

	// 默认上传的地址
	private static String defaultBaseDir = "upload";

	// 默认的文件名最大长度
	public static final int DEFAULT_FILE_NAME_LENGTH = 200;

	public static final String[] IMAGE_EXTENSION = { "bmp", "gif", "jpg", "jpeg", "png" };

	public static final String[] FLASH_EXTENSION = { "swf", "flv" };

	public static final String[] MEDIA_EXTENSION = { "swf", "flv", "mp3", "wav", "wma", "wmv", "mid", "avi", "mpg",
			"asf", "rm", "rmvb" };

	public static final String[] DEFAULT_ALLOWED_EXTENSION = {
			// 图片
			"bmp", "gif", "jpg", "jpeg", "png",
			// word excel powerpoint
			"doc", "docx", "xls", "xlsx", "ppt", "pptx", "html", "htm", "txt",
			// 压缩文件
			"rar", "zip", "gz", "bz2",
			// pdf
			"pdf" };

	public static void setDefaultBaseDir(String defaultBaseDir) {
		FileUploadUtils.defaultBaseDir = defaultBaseDir;
	}

	public static String getDefaultBaseDir() {
		return defaultBaseDir;
	}

	/**
	 * 以默认配置进行文件上传
	 *
	 * @param request
	 *            当前请求
	 * @param file
	 *            上传的文件
	 * @param
	 *
	 * @return
	 * @throws IOException
	 * @throws FileNameLengthLimitExceededException
	 * @throws InvalidExtensionException
	 * @throws FileSizeLimitExceededException
	 */
	public static final Map<String, Object> upload(HttpServletRequest request, MultipartFile file)
			throws FileSizeLimitExceededException, InvalidExtensionException, FileNameLengthLimitExceededException,
			IOException ,Exception {
		return upload(request, file, DEFAULT_ALLOWED_EXTENSION);
	}

	/**
	 * 以默认配置进行文件上传
	 *
	 * @param request
	 *            当前请求
	 * @param file
	 *            上传的文件
	 * @param
	 *
	 * @param allowedExtension
	 *            允许上传的文件类型
	 * @return
	 * @throws IOException
	 * @throws FileNameLengthLimitExceededException
	 * @throws InvalidExtensionException
	 * @throws FileSizeLimitExceededException
	 */
	public static final Map<String, Object> upload(HttpServletRequest request, MultipartFile file,
			String[] allowedExtension) throws FileSizeLimitExceededException, InvalidExtensionException,
			FileNameLengthLimitExceededException, IOException,Exception {
		return upload(request, getDefaultBaseDir(), file, allowedExtension, DEFAULT_MAX_SIZE, true, false);
	}

	/**
	 * 文件上传
	 *
	 * @param request
	 *            当前请求 从请求中提取 应用上下文根
	 * @param baseDir
	 *            相对应用的基目录
	 * @param file
	 *            上传的文件
	 * @param allowedExtension
	 *            允许的文件类型 null 表示允许所有
	 * @param maxSize
	 *            最大上传的大小 -1 表示不限制
	 * @param needDatePathAndRandomName
	 *            是否需要日期目录和随机文件名前缀
	 * @param compress 当时图片上传的时候，是否需要压缩，true需要压缩，false不需要压缩
	 * 
	 * @return 返回上传成功的文件名
	 * @throws InvalidExtensionException
	 *             如果MIME类型不允许
	 * @throws FileSizeLimitExceededException
	 *             如果超出最大大小
	 * @throws FileNameLengthLimitExceededException
	 *             文件名太长
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 *             比如读写文件出错时
	 */
	public static final Map<String, Object> upload(HttpServletRequest request, String baseDir, MultipartFile file,
			String[] allowedExtension, long maxSize, boolean needDatePathAndRandomName, boolean compress)
			throws FileNameLengthLimitExceededException, FileSizeLimitExceededException, InvalidExtensionException,
			UnsupportedEncodingException,Exception {
		int fileNamelength = file.getOriginalFilename().length();
		if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH) {
			throw new FileNameLengthLimitExceededException(file.getOriginalFilename(), fileNamelength,
					FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
		}

		assertAllowed(file, allowedExtension, maxSize);
		String filename = extractFilename(file, baseDir, needDatePathAndRandomName);

		// File desc = getAbsoluteFile(extractUploadDir(request), filename);
		// file.transferTo(desc);
		String url = "";
		Map<String, Object> map = new HashMap<>();
		long size = file.getSize();
		if (compress && size>COMPRESS_SIZE) {
			File upFile = picCompress(file);
			url = OSSClientUtils.putObject(upFile, filename);
			map.put("size", upFile.length());
			upFile.delete();
		}else{
			CommonsMultipartFile cf = (CommonsMultipartFile) file;
			DiskFileItem fi = (DiskFileItem) cf.getFileItem();
			url = OSSClientUtils.putObject(fi.getStoreLocation(), filename);
			map.put("size", file.getSize());
		}
		map.put("url", url);
		map.put("path", filename);
		return map;
		// return filename;
	}
	
	/**
	 * 图片压缩，返回压缩后的file
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static File picCompress(MultipartFile file) throws IOException {
		CommonsMultipartFile cf = (CommonsMultipartFile) file;
		DiskFileItem fi = (DiskFileItem) cf.getFileItem();
		File in = fi.getStoreLocation();
		WriteRender wr = null;
		File out = null;
		FileInputStream input = null;
		synchronized (FileUploadUtils.class) {
			out = File.createTempFile(System.currentTimeMillis()+"", ".jpg");
		}
		try {
			BufferedImage image = ImageIO.read(in);
			int with = (int)(image.getWidth()*0.6);
			int height = (int)(image.getHeight()*0.6);
			if (with<1024 || height<1024) {
				with = 1024;
				height = 1024;
			}
			ScaleParameter scaleParam = new ScaleParameter(with, height);
			input = new FileInputStream(in);
			ImageRender rr = new ReadRender(input);
			ImageRender sr = new ScaleRender(rr, scaleParam);
			wr = new WriteRender(sr, out);

			wr.render(); // 触发图像处理
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != input) {
				try {
					input.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (wr != null) {
				try {
					wr.dispose(); // 释放simpleImage的内部资源
				} catch (SimpleImageException ignore) {
					// skip ...
				}
			}
		}
		/*	此图片压缩可能会有颜色产生
		ImageWriter writer;
		ImageWriteParam writeParam;
		BufferedImage image = null;
		FileOutputStream out = null;
		File temp = null;
		synchronized (FileUploadUtils.class) {
			temp = File.createTempFile(System.currentTimeMillis()+"", ".jpg");
		}
		writer = ImageIO.getImageWritersByFormatName("jpg").next();// 指定写图片的方式为 jpg
		writeParam = writer.getDefaultWriteParam(); // new JPEGImageWriteParam(null);
		writer.getDefaultWriteParam();
		writeParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);// 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
		writeParam.setCompressionQuality((float) 0.4);// 这里指定压缩的程度，参数qality是取值0~1范围内，
		writeParam.setProgressiveMode(ImageWriteParam.MODE_DISABLED);
		
		try {
			image = ImageIO.read(file.getInputStream());
			out = new FileOutputStream(temp);
			writer.reset();
			writer.setOutput(ImageIO.createImageOutputStream(out));
			writer.write(null, new IIOImage(image, null, null), writeParam);// 调用write方法，就可以向输入流写图片
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			out.flush();
			out.close();
		}*/
		return out;
	}

	public static final File getAbsoluteFile(String uploadDir, String filename) throws IOException {

		uploadDir = FilenameUtils.normalizeNoEndSeparator(uploadDir);

		File desc = new File(uploadDir + "/" + filename);

		if (!desc.getParentFile().exists()) {
			desc.getParentFile().mkdirs();
		}
		if (!desc.exists()) {
			desc.createNewFile();
		}
		return desc;
	}

	public static final String extractFilename(MultipartFile file, String baseDir, boolean needDatePathAndRandomName)
			throws UnsupportedEncodingException {
		String filename = file.getOriginalFilename();
		int slashIndex = filename.indexOf("/");
		if (slashIndex >= 0) {
			filename = filename.substring(slashIndex + 1);
		}
		synchronized(FileUploadUtils.class){
			if (needDatePathAndRandomName) {
				filename = baseDir + "/" + datePath() + "/" + StringUtils.randomUUID() + "."
						+ StringUtils.getExtensionName(filename);
			} else {
				filename = baseDir + "/" + filename;
			}
		}

		return filename;
	}

	/**
	 * 日期路径 即年/月/日 如2013/01/03
	 *
	 * @return
	 */
	private static final String datePath() {
		Date now = new Date();
		return DateFormatUtils.format(now, "yyyy/MM/dd");
	}

	/**
	 * 是否允许文件上传
	 *
	 * @param file
	 *            上传的文件
	 * @param allowedExtension
	 *            文件类型 null 表示允许所有
	 * @param maxSize
	 *            最大大小 字节为单位 -1表示不限制
	 * @return
	 * @throws InvalidExtensionException
	 *             如果MIME类型不允许
	 * @throws FileSizeLimitExceededException
	 *             如果超出最大大小
	 */
	public static final void assertAllowed(MultipartFile file, String[] allowedExtension, long maxSize)
			throws InvalidExtensionException, FileSizeLimitExceededException {

		String filename = file.getOriginalFilename();
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());

		if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension)) {
			if (allowedExtension == IMAGE_EXTENSION) {
				throw new InvalidExtensionException.InvalidImageExtensionException(allowedExtension, extension,
						filename);
			} else if (allowedExtension == FLASH_EXTENSION) {
				throw new InvalidExtensionException.InvalidFlashExtensionException(allowedExtension, extension,
						filename);
			} else if (allowedExtension == MEDIA_EXTENSION) {
				throw new InvalidExtensionException.InvalidMediaExtensionException(allowedExtension, extension,
						filename);
			} else {
				throw new InvalidExtensionException(allowedExtension, extension, filename);
			}
		}

		long size = file.getSize();
		if (maxSize != -1 && size > maxSize) {
			throw new FileSizeLimitExceededException("not allowed upload upload", size, maxSize);
		}
	}

	/**
	 * 判断MIME类型是否是允许的MIME类型
	 *
	 * @param extension
	 * @param allowedExtension
	 * @return
	 */
	public static final boolean isAllowedExtension(String extension, String[] allowedExtension) {
		for (String str : allowedExtension) {
			if (str.trim().equalsIgnoreCase(extension.trim())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 提取上传的根目录 默认是应用的根
	 *
	 * @param request
	 * @return
	 */
	public static final String extractUploadDir(HttpServletRequest request) {
		return request.getServletContext().getRealPath("/");
	}

	public static final void delete(HttpServletRequest request, String filename) throws IOException {
		if (StringUtils.isEmpty(filename)) {
			return;
		}
		File desc = getAbsoluteFile(extractUploadDir(request), filename);
		if (desc.exists()) {
			desc.delete();
		}
	}

	/**
	 * 以默认配置进行文件上传
	 *
	 * @param request
	 *            当前请求
	 * @param remoteUrl
	 *            上传的文件 添加出错信息
	 * @param allowedExtension
	 *            允许上传的文件类型
	 * @return
	 * @throws IOException
	 * @throws FileNameLengthLimitExceededException
	 * @throws InvalidExtensionException
	 * @throws FileSizeLimitExceededException
	 */
	public static String remote(HttpServletRequest request, String baseDir, String remoteUrl, String[] allowedExtension,
			long maxSize) throws FileSizeLimitExceededException, InvalidExtensionException,
			FileNameLengthLimitExceededException, IOException {
		return remote(request, baseDir, remoteUrl, allowedExtension, maxSize, true);
	}

	/**
	 * 文件上传
	 *
	 * @param request
	 *            当前请求 从请求中提取 应用上下文根
	 * @param baseDir
	 *            相对应用的基目录
	 * @param remoteUrl
	 *            上传的文件
	 * @param allowedExtension
	 *            允许的文件类型 null 表示允许所有
	 * @param maxSize
	 *            最大上传的大小 -1 表示不限制
	 * @param needDatePathAndRandomName
	 *            是否需要日期目录和随机文件名前缀
	 * @return 返回上传成功的文件名
	 * @throws InvalidExtensionException
	 *             如果MIME类型不允许
	 * @throws FileSizeLimitExceededException
	 *             如果超出最大大小
	 * @throws FileNameLengthLimitExceededException
	 *             文件名太长
	 * @throws IOException
	 *             比如读写文件出错时
	 */
	public static String remote(HttpServletRequest request, String baseDir, String remoteUrl, String[] allowedExtension,
			long maxSize, boolean needDatePathAndRandomName) throws InvalidExtensionException,
			FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException {
		URL url = new URL(remoteUrl);
		assertAllowed(remoteUrl, allowedExtension, maxSize);
		String filename = extractByFilename(remoteUrl, baseDir, needDatePathAndRandomName);
		FileUtil.copyStream(url.openStream(), new FileOutputStream(filename));
		return filename;
	}

	/**
	 * 是否允许文件上传
	 *
	 * @param remoteUrl
	 *            上传的文件
	 * @param allowedExtension
	 *            文件类型 null 表示允许所有
	 * @param maxSize
	 *            最大大小 字节为单位 -1表示不限制
	 * @return
	 * @throws InvalidExtensionException
	 *             如果MIME类型不允许
	 * @throws FileSizeLimitExceededException
	 *             如果超出最大大小
	 */
	public static void assertAllowed(String remoteUrl, String[] allowedExtension, long maxSize)
			throws InvalidExtensionException, FileSizeLimitExceededException {

		String extension = FilenameUtils.getExtension(remoteUrl);
		if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension)) {
			if (allowedExtension == IMAGE_EXTENSION) {
				throw new InvalidExtensionException.InvalidImageExtensionException(allowedExtension, extension,
						remoteUrl);
			} else if (allowedExtension == FLASH_EXTENSION) {
				throw new InvalidExtensionException.InvalidFlashExtensionException(allowedExtension, extension,
						remoteUrl);
			} else if (allowedExtension == MEDIA_EXTENSION) {
				throw new InvalidExtensionException.InvalidMediaExtensionException(allowedExtension, extension,
						remoteUrl);
			} else {
				throw new InvalidExtensionException(allowedExtension, extension, remoteUrl);
			}
		}
	}

	public static String extractByFilename(String remoteUrl, String baseDir, boolean needDatePathAndRandomName)
			throws UnsupportedEncodingException {
		String filename = remoteUrl;
		int slashIndex = filename.indexOf("/");
		if (slashIndex >= 0) {
			filename = filename.substring(slashIndex + 1);
		}
		synchronized (FileUploadUtils.class){
			if (needDatePathAndRandomName) {
				filename = baseDir + "/" + datePath() + "/" + StringUtils.randomUUID() + "."
						+ StringUtils.getExtensionName(filename);
			} else {
				filename = baseDir + "/" + filename;
			}
		}

		return filename;
	}
}
