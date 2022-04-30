package com.example.attendenceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.attendenceapp.bean.AttendanceBean;
import com.example.attendenceapp.db.DBAdapter;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    Button addStudent;
    Button addFaculty;
    Button viewStudent;
    Button viewFaculty;
    Button attendancePerStudent;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        addStudent=findViewById(R.id.addStudent);
        addFaculty=findViewById(R.id.addFaculty);
        viewStudent=findViewById(R.id.viewStudent);
        viewFaculty=findViewById(R.id.viewFaculty);
        attendancePerStudent=findViewById(R.id.attendancePerStudent);
        logout=findViewById(R.id.layout);

    }
    public void addStudent(View view){
        Intent intent=new Intent(MenuActivity.this,AddStudentActivity.class);
        startActivity(intent);

    }
    public void addFaculty(View view){
        Intent intent=new Intent (MenuActivity.this,AddFacultyActivity.class);
        startActivity(intent);
    }
    public void viewStudent(View view){
        Intent intent=new Intent (MenuActivity.this,ViewStudentActivity.class);
        startActivity(intent);
    }
    public void viewFaculty(View view){
        Intent intent=new Intent (MenuActivity.this,ViewFacultyActivity.class);
        startActivity(intent);
    }
    public void attendancePerStdent(View view){
        DBAdapter dbAdapter = new DBAdapter(MenuActivity.this);
        ArrayList<AttendanceBean> attendanceBeanList=dbAdapter.getAllAttendanceByStudent();
        ((ApplicationContext)MenuActivity.this.getApplicationContext()).setAttendanceBeanList(attendanceBeanList);
        Intent intent=new Intent (MenuActivity.this,AttendancePerStudentActivity.class);
        startActivity(intent);
    }
    public void logout(View view){
   Intent intent = new Intent(MenuActivity.this,MainActivity.class);
   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
   startActivity(intent);

    }
}