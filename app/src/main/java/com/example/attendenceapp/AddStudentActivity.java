package com.example.attendenceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attendenceapp.bean.StudentBean;
import com.example.attendenceapp.db.DBAdapter;

public class AddStudentActivity extends AppCompatActivity {
    Button register;
    EditText name;
    EditText sirName;
    EditText phoneNo;
    EditText address;
    Spinner branchSpinner;
    Spinner yearSpinner;
    String[] branchString={"cse"};
    String[] yearString={"SE","TE","BE"};
    String year,branch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        register=findViewById(R.id.register);
        name=findViewById(R.id.name);
        sirName=findViewById(R.id.sirName);
        phoneNo=findViewById(R.id.phoneNo);
        address=findViewById(R.id.address);
        branchSpinner=findViewById(R.id.branchSpinner);
        yearSpinner=findViewById(R.id.yearSpinner);

//        spinnerAdapter 1
        branchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                 branch=(String)branchSpinner.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
        ArrayAdapter<String> branchAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,branchString);
        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branchSpinner.setAdapter(branchAdapter);

//        spinnerAdapter 2
        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                year=(String)yearSpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter<String> yearAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,yearString);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);
    }
//    registeration
    public void registration(View view){
         String firstName=name.getText().toString();
        String lasttName=sirName.getText().toString();
        String phone=phoneNo.getText().toString();
        String addressText=address.getText().toString();

        if(TextUtils.isEmpty(firstName)){
            name.setError("Please enter name");
        }else if (TextUtils.isEmpty(lasttName)){
            sirName.setError("Please enter last name");
        }else if (TextUtils.isEmpty(phone)){
            phoneNo.setError("Please enter phone number");
        }else if (TextUtils.isEmpty(addressText)){
            address.setError("Please enter address");
        }
        else
        {
            StudentBean studentBean=new StudentBean();
            studentBean.setStudent_firstname(firstName);
            studentBean.setStudent_lastname(lasttName);
            studentBean.setStudent_mobilenumber(phone);
            studentBean.setStudent_address(addressText);
            studentBean.setStudent_department(branch);
            studentBean.setStudent_class(year);

            DBAdapter dbAdapter=new DBAdapter(AddStudentActivity.this);
            dbAdapter.addStudent(studentBean);
            Log.i( "registration: ",studentBean.getStudent_firstname());
            Intent intent =new Intent(getApplicationContext(),MenuActivity.class);
            startActivity(intent);

            Toast.makeText(getApplicationContext(), "student added successfully", Toast.LENGTH_SHORT).show();

        }

    }


}