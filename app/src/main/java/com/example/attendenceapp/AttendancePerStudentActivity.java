package com.example.attendenceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.attendenceapp.bean.AttendanceBean;
import com.example.attendenceapp.bean.StudentBean;
import com.example.attendenceapp.db.DBAdapter;

import java.util.ArrayList;

public class AttendancePerStudentActivity extends AppCompatActivity {
    ArrayList<AttendanceBean> attendanceBeanList;
    private ListView listView ;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_per_student);

        listView=(ListView)findViewById(R.id.listview1);
        final ArrayList<String> attendanceList = new ArrayList<String>();
        attendanceList.add("Present Count Per Student");
        attendanceBeanList=((ApplicationContext)AttendancePerStudentActivity.this.getApplicationContext()).getAttendanceBeanList();

        for(AttendanceBean attendanceBean : attendanceBeanList)
        {
            String users = "";

            DBAdapter dbAdapter = new DBAdapter(AttendancePerStudentActivity.this);
            StudentBean studentBean =dbAdapter.getStudentById(attendanceBean.getAttendance_student_id());
            users = attendanceBean.getAttendance_student_id()+"  "+studentBean.getStudent_firstname()+" "+studentBean.getStudent_lastname()+" | "+attendanceBean.getAttendance_session_id();
            attendanceList.add(users);
        }

        listAdapter = new ArrayAdapter<String>(this, R.layout.view_attendance_list_per_student, R.id.labelAttendancePerStudent, attendanceList);
        listView.setAdapter( listAdapter );
    }
}