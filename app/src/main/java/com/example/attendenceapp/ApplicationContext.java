package com.example.attendenceapp;

import android.app.Activity;
import android.app.Application;

import com.example.attendenceapp.bean.AttendaceSessionBean;
import com.example.attendenceapp.bean.AttendanceBean;
import com.example.attendenceapp.bean.FacultyBean;
import com.example.attendenceapp.bean.StudentBean;

import java.util.ArrayList;

public class ApplicationContext extends Application {
    public FacultyBean facultyBean;
    private AttendaceSessionBean attendanceSessionBean;
    private ArrayList<StudentBean> studentBeanList;
    private ArrayList<AttendanceBean> attendanceBeanList;

    public FacultyBean getFacultyBean() {
        return facultyBean;
    }
    public void setFacultyBean(FacultyBean facultyBean) {
        this.facultyBean = facultyBean;
    }
    public AttendaceSessionBean getAttendanceSessionBean() {
        return attendanceSessionBean;
    }
    public void setAttendanceSessionBean(AttendaceSessionBean attendanceSessionBean) {
        this.attendanceSessionBean = attendanceSessionBean;
    }
    public ArrayList<StudentBean> getStudentBeanList() {
        return studentBeanList;
    }
    public void setStudentBeanList(ArrayList<StudentBean> studentBeanList) {
        this.studentBeanList = studentBeanList;
    }
    public ArrayList<AttendanceBean> getAttendanceBeanList() {
        return attendanceBeanList;
    }
    public void setAttendanceBeanList(ArrayList<AttendanceBean> attendanceBeanList) {
        this.attendanceBeanList = attendanceBeanList;
    }
}
