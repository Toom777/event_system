package com.toom.event_system.Controller;

import com.toom.event_system.Common.Result;
import com.toom.event_system.Common.Utils.FileUploadUtils;
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
}
