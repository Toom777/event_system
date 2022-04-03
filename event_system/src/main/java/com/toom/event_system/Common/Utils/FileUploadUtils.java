package com.toom.event_system.Common.Utils;


import com.alibaba.fastjson.JSONObject;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 文件上传工具类
 * @author Toom
 */
public class FileUploadUtils {

    /** 项目根路径下的目录 */
    public final static String IMAGES_PATH_PREFIX = "static/uploadFile/images";

    public static File getImgDirFile() {
        String fileDirPath = new String("src/main/resources/" + IMAGES_PATH_PREFIX);
        
        File fileDir = new File(fileDirPath);
        if (!fileDir.exists()){
            fileDir.mkdirs();
        }
        return fileDir;
    }
}
