package com.wj.blog.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 文章简介Vo对象
 *
 * @author wj
 * {@code @date} 2023/02/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleIntroductionVo {
    @ApiModelProperty("文章主键")
    private String id;

    @ApiModelProperty("文章标题")
    private String title;

    @ApiModelProperty("文章主体内容")
    private String context;

    @ApiModelProperty("文章头图")
    private String thumbnail;

    @ApiModelProperty("文章发布时间")
    private LocalDateTime releaseTime;

    @ApiModelProperty("文章发布作者")
    private UserVo author;

    @ApiModelProperty("文章所属分类")
    private CategoryVo category;


}
