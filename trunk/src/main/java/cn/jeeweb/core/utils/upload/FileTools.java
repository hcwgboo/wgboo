package cn.jeeweb.core.utils.upload;

import javax.imageio.ImageIO;

import cn.jeeweb.core.exception.ExceptionResultInfo;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.util.ArrayList;

public class FileTools {

    public static final int IMG = 1;

    /**
     * 获取楼盘根目录
     *
     * @return 根目录
     */
    public static String getWebRootPath() {
        return System.getProperty("web.root");
    }

    /**
     * 获取头像目录，若不存在则直接创建一个
     *
     * @param userID 用户ID
     * @return
     * @throws ExceptionResultInfo 
     */
    public static String getPortraitPath(int userID) throws ExceptionResultInfo {
        String realPath = getWebRootPath() + "img/portrait/" + userID + "/";
        File file = new File(realPath);
        //判断文件夹是否存在，不存在则创建一个
        if (!file.exists() || !file.isDirectory()) {
            if (!file.mkdirs()) {
                throw new ExceptionResultInfo("创建头像文件夹失败！");
            }
        }
        return realPath;
    }

    /**
     *
     * @param fileName 文件名
     * @return
     */
    public static String getPortraitFileName(String fileName) {
        // 获取文件后缀
        String suffix = getSuffix(fileName);
        return System.currentTimeMillis() + suffix;
    }

    /**
     * 判断文件后缀是否符合要求
     *
     * @param fileName    文件名
     * @param allowSuffix 允许的后缀集合
     * @return
     * @throws Exception
     */
    public static boolean checkSuffix(String fileName, String[] allowSuffix) throws Exception {
        String fileExtension = getSuffix(fileName).toLowerCase();
        boolean flag = false;
        for (String extension : allowSuffix) {
            if (fileExtension.equals(extension)) {
                flag = true;
            }
        }
        return flag;
    }


    public static String getSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
    }

    /**
     * 将文件地址转成链接地址
     *
     * @param filePath 文件路径
     * @param fileType 文件类型
     * @return
     */
    public static String filePathToSRC(String filePath, int fileType) {
        String href = "";
        if (null != filePath && !filePath.equals("")) {
            switch (fileType) {
                case IMG:
                    if (filePath.contains("/img/")) {
                        int index = filePath.indexOf("/img/");
                        href = filePath.substring(index);
                    } else {
                        href = "";
                    }
                    return href;
            }
        }
        return href;
    }

    /**
     * 获取指定文件或文件路径下的所有文件清单
     *
     * @param fileOrPath 文件或文件路径
     * @return
     */
    public static ArrayList<File> getListFiles(Object fileOrPath) {
        File directory;
        if (fileOrPath instanceof File) {
            directory = (File) fileOrPath;
        } else {
            directory = new File(fileOrPath.toString());
        }

        ArrayList<File> files = new ArrayList<File>();

        if (directory.isFile()) {
            files.add(directory);
            return files;
        } else if (directory.isDirectory()) {
            File[] fileArr = directory.listFiles();
            if (null != fileArr && fileArr.length != 0) {
                for (File fileOne : fileArr) {
                    files.addAll(getListFiles(fileOne));
                }
            }
        }

        return files;
    }


    /**
     * 截图工具，根据截取的比例进行缩放裁剪
     *
     * @param path        图片路径
     * @param zoomX       X坐标
     * @param zoomY       Y坐标
     * @param zoomW       截取宽度
     * @param zoomH       截取高度
     * @param scaleWidth  图片的宽度
     * @param scaleHeight 图片的高度
     * @return 是否成功
     * @throws Exception 任何异常均抛出
     */
    public static boolean imgCut(File file, Double zoomX, Double zoomY, Double zoomW,
    		Double zoomH, Double scaleWidth, Double scaleHeight) throws Exception {
        Image img;
        ImageFilter cropFilter;
        BufferedImage bi = ImageIO.read(file);
        int fileWidth = bi.getWidth();
        int fileHeight = bi.getHeight();
        double scale = (double) fileWidth / (double) scaleWidth;

        double realX = zoomX * scale;
        double realY = zoomY * scale;
        double realW = zoomW * scale;
        double realH = zoomH * scale;
        if (fileWidth <= realW) {
        	realW = fileWidth;
		}
        if (fileHeight <= realH) {
			realH = fileHeight;
		}

        Image image = bi.getScaledInstance(fileWidth, fileHeight, Image.SCALE_DEFAULT);
        cropFilter = new CropImageFilter((int) realX, (int) realY, (int) realW, (int) realH);
        img = Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(image.getSource(), cropFilter));
        BufferedImage bufferedImage = new BufferedImage((int) realW, (int) realH, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        //输出文件
        return ImageIO.write(bufferedImage, "JPEG", file);
    }
}