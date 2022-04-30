package com.example.attendenceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewStudentActivity extends AppCompatActivity {
    Button submit;
    Spinner branchSpinner;
    Spinner yearSpinner;
    String branch;
    String year;
    private String[] branchString = new String[] { "cse"};
    private String[] yearString = new String[] {"SE","TE","BE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        submit=findViewById(R.id.submit);
        branchSpinner=findViewById(R.id.branchSpinner);
        yearSpinner=findViewById(R.id.yearSpinner);

        branchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                branch= (String) branchSpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,branchString);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branchSpinner.setAdapter(adapter);

//        Spinner 2 yearSpinner
        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                year=(String)yearSpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

         ArrayAdapter<String> yAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,yearString);
         yAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         yearSpinner.setAdapter(yAdapter);
    }
    public  void submit(View view){
        Intent intent=new Intent(ViewStudentActivity.this,ViewStudentByBranchYear.class);
        intent.putExtra("branch", branch);
        intent.putExtra("year", year);
        startActivity(intent);
    }
}