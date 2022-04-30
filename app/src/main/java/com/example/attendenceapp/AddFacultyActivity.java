package com.example.attendenceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.attendenceapp.bean.FacultyBean;
import com.example.attendenceapp.db.DBAdapter;

public class AddFacultyActivity extends AppCompatActivity {

    Button register;
    EditText firstName;
    EditText lastName;

    EditText contact;
    EditText address;
    EditText userName;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faculty);

        register= findViewById(R.id.register);
        firstName= findViewById(R.id.firstName);
        lastName= findViewById(R.id.lastName);

        address= findViewById(R.id.address);
        contact= findViewById(R.id.contact);
        userName= findViewById(R.id.userName);
        password= findViewById(R.id.password);



    }
//    click on register button
    public void register(View view){

        String first_name= firstName.getText().toString();
        String last_name= lastName.getText().toString();
        String address_text= address.getText().toString();
        String contact_text= contact.getText().toString();
        String user_name= userName.getText().toString();
        String password_text=password.getText().toString();

        if(TextUtils.isEmpty(first_name)){
          firstName.setError("Please enter name");
        }else if(TextUtils.isEmpty(last_name)){
            lastName.setError("Please enter last name");
        }else if(TextUtils.isEmpty(address_text)){
            address.setError("Please enter address");
        }else if(TextUtils.isEmpty(contact_text)){
            contact.setError("Please enter contact");
        }else if(TextUtils.isEmpty(user_name)){
            userName.setError("Please enter user name");
        }else if(TextUtils.isEmpty(password_text)){
            password.setError("Please enter password");
        } else {

            DBAdapter dbAdapter =new DBAdapter(AddFacultyActivity.this);
            FacultyBean facultyBean=new FacultyBean();
            facultyBean.setFaculty_firstname(first_name);
            facultyBean.setFaculty_lastname(last_name);
            facultyBean.setFaculty_mobilenumber(contact_text);
            facultyBean.setFaculty_address(address_text);
            facultyBean.setFaculty_username(user_name);
            facultyBean.setFaculty_password(password_text);

            dbAdapter.addFaculty(facultyBean);

            Intent intent =new Intent(AddFacultyActivity.this,MenuActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Faculty added successfully", Toast.LENGTH_SHORT).show();
        }
    }
}