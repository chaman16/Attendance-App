package com.example.attendenceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.attendenceapp.bean.StudentBean;
import com.example.attendenceapp.db.DBAdapter;

import java.util.ArrayList;

public class ViewStudentByBranchYear extends AppCompatActivity {
    ArrayList<StudentBean> studentbeanlist;
    ArrayAdapter<String> adapter;
    private ListView list;
    String branch;
    String year;

    DBAdapter dbAdapter=new DBAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_by_branch_year);
        Toast.makeText(this, "List of Student", Toast.LENGTH_SHORT).show();
        final ArrayList<String> studentlist=new ArrayList<>();
        list=findViewById(R.id.list);

        branch=getIntent().getExtras().getString("branch");
        year =getIntent().getExtras().getString("year");
        Log.i("branch ",branch);
        Log.i("year ",year);
        studentbeanlist= dbAdapter.getAllStudentByBranchYear(branch,year);
        Log.i("onCreate: ",studentbeanlist.toString());
        for(StudentBean sb :studentbeanlist){
            String name=sb.getStudent_firstname()+" "+sb.getStudent_lastname();
            studentlist.add(name);
            Log.i("students ",name);
        }
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,studentlist);
        list.setAdapter(adapter);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                new AlertDialog.Builder(ViewStudentByBranchYear.this).setIcon(android.R.drawable.ic_menu_delete).
                        setTitle("DELETE").
                        setMessage("Are You Sure").
                        setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int id) {
                                studentlist.remove(i);
                                adapter.notifyDataSetChanged();
                                adapter.notifyDataSetInvalidated();

                                dbAdapter.deleteStudent(studentbeanlist.get(i).getStudent_id());
//                                dbAdapter.getAllStudentByBranchYear(branch,year);
//                                for(StudentBean studentBean : studentbeanlist)
//                                {
//                                    String users = " FirstName: " + studentBean.getStudent_firstname() +" "+studentBean.getStudent_lastname();
//                                    studentlist.add(users);
//                                    Log.i("users: ", users);
//
//
//                            }
                            }
                        }).
                        setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(ViewStudentByBranchYear.this, "Student is not removed", Toast.LENGTH_SHORT).show();
                            }
                        }).show();


                return false;
            }
        });



    }
}