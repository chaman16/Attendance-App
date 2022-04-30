package com.example.attendenceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attendenceapp.bean.AttendaceSessionBean;
import com.example.attendenceapp.bean.AttendanceBean;
import com.example.attendenceapp.bean.FacultyBean;
import com.example.attendenceapp.bean.StudentBean;
import com.example.attendenceapp.db.DBAdapter;

import java.util.ArrayList;
import java.util.Calendar;

public class AddAttandanceSessionActivity extends AppCompatActivity {

    private ImageButton date;
    private Calendar cal;
    private int day;
    private int month;
    private int dyear;
    private EditText dateEditText;
    Button submit;
    Button viewAttendance;
    Button viewTotalAttendance;
    Spinner spinnerbranch, spinneryear, spinnerSubject;
    String branch = "cse";
    String year = "SE";
    String subject = "M1";

    private String[] branchString = new String[]{"cse"};
    private String[] yearString = new String[]{"SE", "TE", "BE"};
    private String[] subjectSEString = new String[]{"M1", "BEE"};
    private String[] subjectTEString = new String[]{"DM", "OS"};
    private String[] subjectBEString = new String[]{"PRO", "DAA"};

    private String[] subjectFinal = new String[]{"M1", "BEE", "DM", "OS", "PRO", "DAA"};
    AttendaceSessionBean attendanceSessionBean;
    ApplicationContext applicationContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attandance_session);

        spinnerbranch=(Spinner) findViewById(R.id.branchspinner);
        spinneryear = (Spinner)findViewById(R.id.yearSpinner);
        spinnerSubject = (Spinner)findViewById(R.id.subjectSpinner);

        //        spinner branch


        spinnerbranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                branch=(String) spinnerbranch.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter<String> branchadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, branchString);
        branchadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerbranch.setAdapter(branchadapter);

//        spinner year

        spinneryear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                year = (String) spinneryear.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter<String> yearadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, yearString);
        yearadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneryear.setAdapter(yearadapter);



//        subject Spinner

        spinnerSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                subject = (String) spinnerSubject.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter<String> subjectadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subjectFinal);
        subjectadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubject.setAdapter(subjectadapter);


        date = findViewById(R.id.imageButton);
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        dyear = cal.get(Calendar.YEAR);
        dateEditText = findViewById(R.id.editText);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(0);
            }
        });
    }

    public void submit(View view) {
        AttendaceSessionBean attendanceSessionBean = new AttendaceSessionBean();
        FacultyBean bean=((ApplicationContext)AddAttandanceSessionActivity.this.getApplicationContext()).getFacultyBean();
        attendanceSessionBean.setAttendance_session_faculty_id(bean.getFaculty_id());
        attendanceSessionBean.setAttendance_session_department(branch);
        attendanceSessionBean.setAttendance_session_class(year);
        attendanceSessionBean.setAttendance_session_date(dateEditText.getText().toString());
        attendanceSessionBean.setAttendance_session_subject(subject);


        DBAdapter dbAdapter = new DBAdapter(AddAttandanceSessionActivity.this);
        int sessionId=	dbAdapter.addAttendanceSession(attendanceSessionBean);
        Log.i("sesssion id ",String.valueOf(sessionId));

        ArrayList<StudentBean> studentBeanList=dbAdapter.getAllStudentByBranchYear(branch, year);
        ((ApplicationContext) AddAttandanceSessionActivity.this.getApplicationContext()).setStudentBeanList(studentBeanList);

        Intent intent =new Intent(AddAttandanceSessionActivity.this,AddAttendanceActivity.class);
        intent.putExtra("sessionId",sessionId);
        startActivity(intent);


    }

    public void viewAttendance(View view) {
     AttendaceSessionBean attendaceSessionBean=new AttendaceSessionBean();
     FacultyBean facultyBean=((ApplicationContext)AddAttandanceSessionActivity.this.getApplicationContext()).getFacultyBean();
     attendaceSessionBean.setAttendance_session_faculty_id(facultyBean.getFaculty_id());
     attendaceSessionBean.setAttendance_session_department(branch);
     attendaceSessionBean.setAttendance_session_class(year);
     attendaceSessionBean.setAttendance_session_date(dateEditText.getText().toString());
     attendaceSessionBean.setAttendance_session_subject(subject);

     DBAdapter dbAdapter=new DBAdapter(AddAttandanceSessionActivity.this);

        ArrayList<AttendanceBean> attendanceBeanList = dbAdapter.getAttendanceBySessionID(attendaceSessionBean);
        ((ApplicationContext)AddAttandanceSessionActivity.this.getApplicationContext()).setAttendanceBeanList(attendanceBeanList);

        Intent intent = new Intent(AddAttandanceSessionActivity.this,ViewAttendanceByFacultyActivit.class);
        startActivity(intent);

    }
    public void viewTotalAttendance(View view ){

        AttendaceSessionBean attendaceSessionBean=new AttendaceSessionBean();
        FacultyBean facultyBean=((ApplicationContext)AddAttandanceSessionActivity.this.getApplicationContext()).getFacultyBean();
        attendaceSessionBean.setAttendance_session_faculty_id(facultyBean.getFaculty_id());
        attendaceSessionBean.setAttendance_session_department(branch);
        attendaceSessionBean.setAttendance_session_class(year);
        attendaceSessionBean.setAttendance_session_date(dateEditText.getText().toString());
        attendaceSessionBean.setAttendance_session_subject(subject);

        DBAdapter dbAdapter=new DBAdapter(AddAttandanceSessionActivity.this);
        ArrayList<AttendanceBean> attendanceBeanList = dbAdapter.getTotalAttendanceBySessionID(attendaceSessionBean);
        ((ApplicationContext)AddAttandanceSessionActivity.this.getApplicationContext()).setAttendanceBeanList(attendanceBeanList);

        Intent intent = new Intent(AddAttandanceSessionActivity.this,ViewAttendanceByFacultyActivit.class);
        startActivity(intent);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, datePickerListener, dyear, month, day);
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            dateEditText.setText(i + " /" + i1 + " /" + i2);
        }
    };

}