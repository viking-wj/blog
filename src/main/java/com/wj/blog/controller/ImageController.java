package com.wj.blog.controller;


import com.wj.blog.common.exception.cdn.FileUploadException;
import com.wj.blog.common.result.ResultEntity;
import com.wj.blog.model.vo.ImageVo;
import com.wj.blog.service.ImageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * 图片存储
 *
 * @author w
 * @since 2023-02-15
 */
@RestController
@RequestMapping("/blog")
public class ImageController {

    @Resource
    private ImageService imageService;

    /**
     * 上传文件
     *
     * @param file 文件
     * @return {@link ResultEntity}<{@link String}>
     */
    @PostMapping("/file")
    public ResultEntity<String> uploadFile(MultipartFile file) {
        try {
            String url = imageService.uploadFile(file);
            return ResultEntity.success(url);
        } catch (FileUploadException e) {
            return ResultEntity.fail(e.getMessage());
        }
    }

    /**
     * 删除文件
     *
     * @param file 文件
     * @return {@link ResultEntity}<{@link String}>
     */
    @DeleteMapping("/file")
    public ResultEntity<String> deleteFile(@RequestParam List<ImageVo> file) {
        imageService.deleteFile(file);
        return ResultEntity.success();
    }
}
