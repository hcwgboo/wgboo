package cn.jeeweb.modules.sys.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.exception.ExceptionResultInfo;
import cn.jeeweb.core.utils.IpUtils;
import cn.jeeweb.core.utils.PropertiesUtil;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.core.utils.upload.FileTools;
import cn.jeeweb.core.utils.upload.FileUploadUtils;
import cn.jeeweb.core.utils.upload.exception.FileNameLengthLimitExceededException;
import cn.jeeweb.core.utils.upload.exception.InvalidExtensionException;
import cn.jeeweb.modules.common.oss.OSSClientUtils;
import cn.jeeweb.modules.sys.entity.Attachment;
import cn.jeeweb.modules.sys.entity.User;
import cn.jeeweb.modules.sys.mapper.AttachmentMapper;
import cn.jeeweb.modules.sys.service.IAttachmentService;
import cn.jeeweb.modules.sys.utils.UserUtils;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

@Transactional(rollbackFor = Exception.class)
@Service("attachmentService")
public class AttachmentServiceImpl extends CommonServiceImpl<AttachmentMapper, Attachment>
		implements IAttachmentService {
	public static final String DEFAULT_CONFIG_FILE = "upload.properties";
	protected String configname = DEFAULT_CONFIG_FILE;
	private long maxSize = 0;
	private boolean needDatePathAndRandomName = true;
	private String[] allowedExtension;
	private String baseDir;

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(AttachmentServiceImpl.class);

	@Override
	public Page<Attachment> selectPage(Page<Attachment> page, Wrapper<Attachment> wrapper) {
		Page<Attachment> pageInfo = new Page<Attachment>();
		wrapper.eq("1", "1");
		int total = baseMapper.selectCount(wrapper);
		List<Attachment> records = baseMapper.selectAttachmentPage(page, wrapper);
		pageInfo.setTotal(total);
		pageInfo.setRecords(records);
		return pageInfo;
	}

	@PostConstruct
	public void initAttachement() {
		PropertiesUtil propertiesUtil = new PropertiesUtil(configname);
		maxSize = propertiesUtil.getLong("upload.max.size");
		baseDir = propertiesUtil.getString("upload.base.dir");
		String extension = propertiesUtil.getString("upload.allowed.extension");
		allowedExtension = extension.split(",");
	}

	@Override
	public Attachment upload(HttpServletRequest request, MultipartFile file, boolean compress) throws FileSizeLimitExceededException,
			InvalidExtensionException, FileNameLengthLimitExceededException, IOException,Exception {
		Map<String, Object> map = FileUploadUtils.upload(request, baseDir, file, allowedExtension, maxSize,
				needDatePathAndRandomName,compress);
		String filename = file.getOriginalFilename();
//		long size = file.getSize();
		String realFileName = StringUtils.getFileNameNoEx(filename);
		String fileext = StringUtils.getExtensionName(filename);
		// 保存上传的信息
		Attachment attachment = new Attachment();
		attachment.setFileext(fileext);
		attachment.setFilename(realFileName);
		attachment.setFilepath(map.get("url")+"");
		attachment.setPath(map.get("path")+"");
		attachment.setFilesize((long)map.get("size"));
		attachment.setStatus("1");
		attachment.setUploadip(IpUtils.getIpAddr(request));
		attachment.setUploadtime(new Date());
		attachment.setUser(UserUtils.getUser());
		insert(attachment);
		return attachment;
	}

	@Override
	public boolean deleteBatchIds(List<? extends Serializable> idList) {
		for (Object object : idList) {
			deleteById((Serializable) object);
		}
		return true;
	}

	@Override
	public boolean deleteById(Serializable id) {
		Attachment attachment = selectById(id);
		String filePath = attachment.getPath();
		OSSClientUtils.deleteObject(filePath);
		return super.deleteById(id);
	}


	/**
	 * 上传文件
	 */
	@Override
	public List<Attachment> uploadFile(HttpServletRequest request) throws Exception {
		List<String> urls = new ArrayList<>();
		List<Attachment> as = new ArrayList<>();
		User user = UserUtils.getUser();
		if (cn.jeeweb.core.utils.ObjectUtils.isNullOrEmpty(user)) throw new ExceptionResultInfo("用户不存在！");
		Date date = new Date();

		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());

		String baseUrl = request.getServletContext().getRealPath("/");
		String path;

		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iterator = multiRequest.getFileNames();
			while (iterator.hasNext()) {
				//一次遍历所有文件
				MultipartFile multipartFile = multiRequest.getFile(iterator.next().toString());
				//文件大小
				if (multipartFile.getSize() > 1024 * 1024 * 5) throw new ExceptionResultInfo("上传文件超过5MB！");

				if (multipartFile != null) {
					String[] allowSuffix = {".png", ".jpg", ".jpeg", ".gif", ".bmp",
							".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",
							".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid",
							".rar", ".zip", ".tar", ".gz", ".7z", ".bz2", ".cab", ".iso",
							".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".txt", ".md", ".xml"};
					if (!FileTools.checkSuffix(multipartFile.getOriginalFilename(), allowSuffix)) {
						throw new ExceptionResultInfo("文件后缀名不符合要求！");
					}
					path = FileUploadUtils.extractFilename(multipartFile, "upload", true);

					File file = FileUploadUtils.getAbsoluteFile(baseUrl, path);
					try {
						//存入硬盘
						multipartFile.transferTo(file);

						String src = OSSClientUtils.putObject(file, path);
						//存入附件表
						String filename = multipartFile.getOriginalFilename();
						long size = multipartFile.getSize();
						String realFileName = StringUtils.getFileNameNoEx(filename);
						String fileext = StringUtils.getExtensionName(filename);
						Attachment attachment = new Attachment();
						attachment.setFileext(fileext);
						attachment.setFilename(realFileName);
						attachment.setFilepath(src);
						attachment.setPath(path);
						attachment.setFilesize(size);
						attachment.setStatus("1");
						attachment.setUploadip(IpUtils.getIpAddr(request));
						attachment.setUploadtime(new Date());
						attachment.setUser(UserUtils.getUser());
						insert(attachment);
						//id，filePath
						as.add(attachment);
					} catch (Exception e) {
						logger.error("上传失败！");
					} finally {
						//删除临时文件
						file.delete();
					}
				}
			}
		}
		return as;
	}

	@Override
	public Attachment jcropPicture(HttpServletRequest request) throws Exception {
		if (request.getParameter("x")==null || "".equals(request.getParameter("x")) ||
    			request.getParameter("y")==null || "".equals(request.getParameter("y")) ||
    			request.getParameter("w")==null || "".equals(request.getParameter("w")) ||
    			request.getParameter("h")==null || "".equals(request.getParameter("h")) ||
    			request.getParameter("sw")==null || "".equals(request.getParameter("sw")) ||
    			request.getParameter("sh")==null || "".equals(request.getParameter("sh"))) {
			throw new ExceptionResultInfo("图片截取异常");
		}
    	Double x = Double.parseDouble(request.getParameter("x"));
    	Double y = Double.parseDouble(request.getParameter("y"));
    	Double w = Double.parseDouble(request.getParameter("w"));
    	Double h = Double.parseDouble(request.getParameter("h"));
        String scaleWidthString = request.getParameter("sw");
        int swIndex = scaleWidthString.indexOf("px");
        Double sw = Double.parseDouble(scaleWidthString.substring(0, swIndex));
        String scaleHeightString = request.getParameter("sh");
        int shIndex = scaleHeightString.indexOf("px");
        Double sh = Double.parseDouble(scaleHeightString.substring(0, shIndex));

        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());

        String baseUrl = request.getServletContext().getRealPath("/");
        String path ;
        Attachment attachment = new Attachment();
        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iterator = multiRequest.getFileNames();
            while (iterator.hasNext()) {
                //一次遍历所有文件
                MultipartFile multipartFile = multiRequest.getFile(iterator.next().toString());

                if (multipartFile != null) {
                    path =  FileUploadUtils.extractFilename(multipartFile, "upload", true);
                    //存入硬盘
                    File file = FileUploadUtils.getAbsoluteFile(baseUrl,path);
                    multipartFile.transferTo(file);
                    //图片截取
                    if (FileTools.imgCut(file, x, y, w, h, sw, sh)) {
                    	
//                    	Thumbnails.of(file).scale(1f).outputQuality(0.5f).toFile(file);
                        String src = OSSClientUtils.putObject(file, path);
                        //存入附件表
                        String filename = multipartFile.getOriginalFilename();
                        long size = multipartFile.getSize();
                        String realFileName = StringUtils.getFileNameNoEx(filename);
                        String fileext = StringUtils.getExtensionName(filename);
                        
                        attachment.setFileext(fileext);
                        attachment.setFilename(realFileName);
                        attachment.setFilepath(src);
                        attachment.setPath(path);
                        attachment.setFilesize(size);
                        attachment.setStatus("1");
                        attachment.setUploadip(IpUtils.getIpAddr(request));
                        attachment.setUploadtime(new Date());
                        attachment.setUser(UserUtils.getUser());
                        baseMapper.insert(attachment);
                        //返回
                    }
                }
            }
        }
		return attachment;
	}

	@Override
	public boolean isSatisfyCondition(File file, HttpServletRequest request) {
		String width = request.getParameter("width");
		String height = request.getParameter("height");
		String type = request.getParameter("type");
		if(!StringUtils.isEmpty(width) && !StringUtils.isEmpty(height)){
			try {
				BufferedImage bi = ImageIO.read(file);
				if(bi.getWidth() != Integer.parseInt(width) || bi.getHeight() != Integer.parseInt(height)){
					return false;
				}
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

}
