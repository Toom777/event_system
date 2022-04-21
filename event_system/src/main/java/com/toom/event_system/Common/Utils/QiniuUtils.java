package com.toom.event_system.Common.Utils;

import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 七牛云工具类
 * @author Toom
 */
@Data
@Component
public class QiniuUtils {
    /**
     * accessKey
     */
    @Value("${qiniu.accessKey}")
    private String accessKey ;

    /**
     * secretKey
     */
    @Value("${qiniu.secretKey}")
    private String secretKey ;

    /**
     * 空间名
     */
    @Value("${qiniu.bucket}")
    private String bucket ;

    /**
     * 域名
     */
    @Value("${qiniu.domain}")
    private String domain;

    public String upload(File file, String fileName) throws QiniuException {

        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huanan());

        UploadManager uploadManager = new UploadManager(cfg);

        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);

            Response response = uploadManager.put(file, fileName, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);

            String returnPath = domain + "/" + putRet.key;
            return returnPath;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

}
