package cn.jeeweb.modules.sys.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.utils.upload.exception.FileNameLengthLimitExceededException;
import cn.jeeweb.core.utils.upload.exception.InvalidExtensionException;
import cn.jeeweb.modules.sys.entity.Attachment;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface IAttachmentService extends ICommonService<Attachment> {
	/**
	 * 
	 * @title: upload
	 * @description: 文件上传
	 * @param request
	 * @param file
	 * @param compress 当时图片上传的时候，是否需要压缩，true需要压缩，false不需要压缩
	 * @return
	 * @return: Attachment
	 */
	public Attachment upload(HttpServletRequest request, MultipartFile file, boolean compress) throws FileSizeLimitExceededException,
			InvalidExtensionException, FileNameLengthLimitExceededException, IOException,Exception;

    List<Attachment> uploadFile(HttpServletRequest request) throws Exception;
    
    Attachment jcropPicture(HttpServletRequest request)  throws Exception ;

	/**
	 * 判断上传的图片是否满足条件
	 * @param file
	 * @return
	 */
	boolean isSatisfyCondition(File file, HttpServletRequest request);
}
