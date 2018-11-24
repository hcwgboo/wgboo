package cn.jeeweb.modules.common.oss;

import cn.jeeweb.core.utils.ObjectUtils;
import cn.jeeweb.core.utils.PropertiesUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * @author yuanw
 * @version V1.0
 * @Title: 获取去阿里云httpClient
 * @Description:
 * @date 2018-04-19 15:53
 */
public class OSSClientUtils {
    private static Logger logger = LoggerFactory.getLogger(OSSClientUtils.class);

    private static String properiesName = "oss.properties";
    private static PropertiesUtil propertiesUtil = new PropertiesUtil(properiesName);

    /**
     * 初始化
     */
    public static OSSClient init(){
         String accessKeyId = propertiesUtil.getString("accessKeyId");
         String accessKeySecret = propertiesUtil.getString("accessKeySecret");
         String endpoint = propertiesUtil.getString("endpoint");
        return  new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }


    /**
     * 上传文件
     */
    public static String putObject(File file, String path){
        try{
            logger.info("=======请求oss开始：上传文件"+path+"=======");
            String bucketName = propertiesUtil.getString("bucketName");
            OSSClientUtils utils = new OSSClientUtils();
            OSSClient ossClient = utils.init();
            if (ossClient.doesBucketExist(bucketName)) {
                logger.info("您已经创建Bucket：" + bucketName + "。");
            } else {
                logger.info("您的Bucket不存在，创建Bucket：" + bucketName + "。");
                ossClient.createBucket(bucketName);
            }

            InputStream inputStream = new FileInputStream(file);
            PutObjectResult result = ossClient.putObject(bucketName, path, inputStream);
            //获取上传路径url
            ossClient.shutdown();
            logger.info("=======上传文件成功=======");
            return getObjectUrl(path);
        }catch (Exception e){
            logger.info("=======上传文件失败=======");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取图片路径
     */
    public static String getObjectUrl(String path){
        try{
            logger.info("=======请求oss开始：获取文件"+path+"=======");
            String bucketName = propertiesUtil.getString("bucketName");
            Date expiration = new Date(new Date().getTime() + 3600 * 1000 * 24 * 365 * 10);
            OSSClientUtils utils = new OSSClientUtils();
            OSSClient ossClient = utils.init();
            URL url = ossClient.generatePresignedUrl(bucketName, path, expiration);
            ossClient.shutdown();
            String u ="";
            if(!ObjectUtils.isNullOrEmpty(url)){
                u = url.toString();
                if(u.contains("?")){
                    u = u.substring(0,u.indexOf("?"));
                }
            }
            logger.info("=======获取文件成功=======");
            return !ObjectUtils.isNullOrEmpty(u) ? u : null;
        }catch (Exception e){
            logger.info("=======获取文件失败=======");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除图片
     */
    public static String deleteObject(String path){
        try{
            logger.info("=======请求oss开始：删除文件"+path+"=======");
            String bucketName = propertiesUtil.getString("bucketName");
            OSSClientUtils utils = new OSSClientUtils();
            OSSClient ossClient = utils.init();
            ossClient.deleteObject(bucketName, path);
            ossClient.shutdown();
            logger.info("=======删除文件成功=======");
            return "1";
        }catch (Exception e){
            logger.info("=======删除文件失败=======");
            e.printStackTrace();
        }
        return "0";
    }






}
