package com.example.attendenceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.attendenceapp.bean.AttendanceBean;
import com.example.attendenceapp.bean.StudentBean;
import com.example.attendenceapp.db.DBAdapter;

import java.util.ArrayList;

public class ViewAttendanceByFacultyActivit extends AppCompatActivity {
    ArrayList<AttendanceBean> attendanceBeanList;
    private ListView listView ;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance_by_faculty);

        listView=(ListView)findViewById(R.id.listview);
        final ArrayList<String> attendanceList = new ArrayList<String>();
        attendanceList.add("Id | StudentName |  Status");

        attendanceBeanList=((ApplicationContext)ViewAttendanceByFacultyActivit.this.getApplicationContext()).getAttendanceBeanList();

        for(AttendanceBean attendanceBean : attendanceBeanList)
        {
            String users = "";
            if(attendanceBean.getAttendance_session_id() != 0)
            {
                DBAdapter dbAdapter = new DBAdapter(ViewAttendanceByFacultyActivit.this);
                StudentBean studentBean =dbAdapter.getStudentById(attendanceBean.getAttendance_student_id());
                users = attendanceBean.getAttendance_student_id()+"   "+studentBean.getStudent_firstname()+" "+studentBean.getStudent_lastname()+"   "+attendanceBean.getAttendance_status();
            }
            else
            {
                users = attendanceBean.getAttendance_status();
            }

            attendanceList.add(users);
            Log.d("users: ", users);

        }

        listAdapter = new ArrayAdapter<String>(this, R.layout.view_attendance_list, R.id.labelAttendance, attendanceList);
        listView.setAdapter( listAdapter );
    }
}