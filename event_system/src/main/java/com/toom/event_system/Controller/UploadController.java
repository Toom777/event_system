package com.toom.event_system.Controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.toom.event_system.Common.Result;
import com.toom.event_system.Common.Utils.FileUploadUtils;
import com.toom.event_system.Common.Utils.QiniuUtils;
import com.toom.event_system.Common.Utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传
 * @author Toom
 */
@RestController
@RequestMapping( value = "/upload", consumes = "multipart/form-data")
public class UploadController {

    @Autowired
    private QiniuUtils qiniuUtils;

    @PostMapping("/uploadPicture")
    public Result uploadPicture(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return Result.error("上传失败，请重新上传！");
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();

        // 存储上传图片的文件夹
        File fileDir = FileUploadUtils.getImgDirFile();

        System.out.println(fileDir.getAbsolutePath());
        try {

            //构建真实文件路径
            File saveFile = new File(fileDir.getAbsolutePath() + File.separator + fileName);
            System.out.println(saveFile.getAbsolutePath());

            file.transferTo(saveFile);

            Result result = Result.success();
            result.put("fileName", fileName);
            result.put("url", saveFile);
            return result;

        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("上传失败，请重新上传！");
        }

        /*构造文件名*/
        String fileName = file.getOriginalFilename();
        String fileType = StrUtil.subAfter(fileName, ".", true);
        String newFileName = System.currentTimeMillis() + "." + fileType;

        /*临时文件保存路径以及文件名*/
        String tempPath = FileUploadUtils.getImgDirFile().getAbsolutePath() + newFileName;

        /*生成临时文件 */
        File tempFile = new File(tempPath);

        try {
            /*拷贝临时文件 */
            file.transferTo(tempFile);
            System.out.println("第一步完成");
            /*上传至七牛云 */
            String qiNiu = qiniuUtils.upload(tempFile, newFileName);
            System.out.println("第二步完成");
            /*删除临时文件*/
            FileUtil.del(tempFile);
            System.out.println("第三步完成");
            if (qiNiu != "") {
                Result result = Result.success();
                result.put("fileName", newFileName);
                result.put("url", qiNiu);
                return result;
            }
            return Result.error("上传失败！");
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }

    }
}
