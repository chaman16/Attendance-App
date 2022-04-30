package com.example.attendenceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
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

import com.example.attendenceapp.bean.FacultyBean;

import com.example.attendenceapp.db.DBAdapter;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    EditText email;
    EditText password;
    Spinner spinner;
   public String[] loginas = new String[]{"admin","teacher"};
   String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.button);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        spinner = findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                user = (String) spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, loginas);
        spinner.setAdapter(arrayAdapter);
    }
// click on login button


        public void onclick (View view){
            if (user.equals("admin")) {

                String user_name = email.getText().toString();
                String pass_word = password.getText().toString();

                if (TextUtils.isEmpty(user_name)) {
                    email.setError("Invalid User Name");
                } else if (TextUtils.isEmpty(pass_word)) {
                    password.setError("enter password");
                } else {
                    if (user_name.equals("admin") & pass_word.equals("chaman123")) {
                        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                String user_name = email.getText().toString();
                String pass_word = password.getText().toString();

                if (TextUtils.isEmpty(user_name)) {
                    email.setError("Invalid User Name");
                } else if (TextUtils.isEmpty(pass_word)) {
                    password.setError("enter password");
                }
                DBAdapter dbAdapter = new DBAdapter(LoginActivity.this);
                FacultyBean facultyBean = dbAdapter.validateFaculty(user_name, pass_word);


                if (facultyBean != null) {
                    ((ApplicationContext)LoginActivity.this.getApplicationContext()).setFacultyBean(facultyBean);
                        Intent intent = new Intent(LoginActivity.this,AddAttandanceSessionActivity.class);
                        startActivity(intent);



                        Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "Welcome "+facultyBean.getFaculty_firstname() +" Faculty", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }



