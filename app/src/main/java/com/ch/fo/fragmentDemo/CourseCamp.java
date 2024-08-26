package com.ch.fo.fragmentDemo;

import android.content.Context;

import java.util.ArrayList;

public class CourseCamp {
    private static CourseCamp courseCamp;
    private ArrayList<Course> courseList;

    public static CourseCamp get(Context context) {
        if (courseCamp == null) {
            courseCamp = new CourseCamp(context);
        }
        return courseCamp;
    }

    public CourseCamp(Context context) {
        courseList = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            Course course = new Course();
            course.setCourseId("2024-ID: " + i);
            course.setCourseTitle("立项课程标题：" + i);
            course.setCourseDate("2024-08-31");
            course.setComplete(i%2 == 0);
            courseList.add(course);
        }
    }
    public CourseCamp(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    public static CourseCamp getCourseCamp() {
        return courseCamp;
    }

    public static void setCourseCamp(CourseCamp courseCamp) {
        CourseCamp.courseCamp = courseCamp;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    public Course getCourse(String newId) {
        for (Course course : courseList) {
            if (course.getCourseId().equals(newId)) {
                return course;
            }
        }
        return null;
    }
}
