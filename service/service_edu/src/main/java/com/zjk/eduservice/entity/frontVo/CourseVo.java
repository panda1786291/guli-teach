package com.zjk.eduservice.entity.frontVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:CourseVo
 * @Description:
 * @Author:Zhou Jingkai
 * @Data:2022-01-04 01:18
 * @Version:1.0
 **/
@Data
public class CourseVo {
    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "讲师id")
    private String TeacherId;

    @ApiModelProperty(value = "一级类别id")
    private String subjectParentId;

    @ApiModelProperty(value = "二级类被id")
    private String subjectId;

    @ApiModelProperty(value = "销量排序")
    private String buyCountSort;

    @ApiModelProperty(value = "最新时间排序")
    private String gmtCreatSort;

    @ApiModelProperty(value = "价格排序")
    private String priceSort;
}
