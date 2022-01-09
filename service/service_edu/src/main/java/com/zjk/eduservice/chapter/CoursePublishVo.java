package com.zjk.eduservice.chapter;

import lombok.Data;

@Data
public class CoursePublishVo {
    public String id;
    public String title;
    public Integer lessonNum;
    public String subjectLevelOne;
    public String subjectLevelTwo;
    public String teacherName;
    public String price;
}
