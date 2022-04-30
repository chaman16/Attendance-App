package com.example.attendenceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.attendenceapp.bean.AttendanceBean;
import com.example.attendenceapp.bean.StudentBean;
import com.example.attendenceapp.db.DBAdapter;

import java.util.ArrayList;

public class AddAttendanceActivity extends AppCompatActivity {
    ArrayList<StudentBean> studentBean;
    private ListView listView;
    private ArrayAdapter<String> adapter;

    int sessionId=0;
    String status="P";
    Button attendanceSubmit;
    DBAdapter dbAdapter = new DBAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attendance);

        final int sessionId = getIntent().getExtras().getInt("sessionId");
        listView = findViewById(R.id.listView);
        ArrayList<String> studentName = new ArrayList<>();
        studentBean = new ArrayList<>();
        studentBean = ((ApplicationContext) AddAttendanceActivity.this.getApplicationContext()).getStudentBeanList();
        for (StudentBean s : studentBean) {
            String name = s.getStudent_firstname() + " " + s.getStudent_lastname();
            studentName.add(name);
            Log.i("attendence student", name);
        }
        adapter = new ArrayAdapter<>(this, R.layout.add_student_attendance, R.id.labelA,studentName);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapterView.getChildAt(i).setBackgroundColor(Color.YELLOW);
                //arg0.setBackgroundColor(234567);
                view.setBackgroundColor(Color.YELLOW);
                final StudentBean studentBean1 = studentBean.get(i);
                final Dialog dialog = new Dialog(AddAttendanceActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//...........fv
                dialog.setContentView(R.layout.test_layout);
                // set title and cancelable
                RadioGroup radioGroup;
                RadioButton present;
                RadioButton absent;
                radioGroup = (RadioGroup) dialog.findViewById(R.id.radioGroup);
                present=(RadioButton)dialog.findViewById(R.id.PresentradioButton);
                absent=(RadioButton)dialog.findViewById(R.id.AbsentradioButton);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if(checkedId == R.id.PresentradioButton) {

                            status = "P";
                        } else if(checkedId == R.id.AbsentradioButton) {

                            status = "A";
                        } else {
                        }
                    }
                });

                attendanceSubmit = (Button)dialog.findViewById(R.id.attendanceSubmitButton);
                attendanceSubmit.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        AttendanceBean attendanceBean = new AttendanceBean();

                        attendanceBean.setAttendance_session_id(sessionId);
                        attendanceBean.setAttendance_student_id(studentBean1.getStudent_id());
                        attendanceBean.setAttendance_status(status);

                        DBAdapter dbAdapter = new DBAdapter(AddAttendanceActivity.this);
                        dbAdapter.addNewAttendance(attendanceBean);

                        dialog.dismiss();

                    }
                });

                dialog.setCancelable(true);
                dialog.show();


            }
        });





    }
    }
