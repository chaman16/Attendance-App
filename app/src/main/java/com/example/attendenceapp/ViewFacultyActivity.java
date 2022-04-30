package com.example.attendenceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.attendenceapp.bean.FacultyBean;
import com.example.attendenceapp.db.DBAdapter;

import java.util.ArrayList;

public class ViewFacultyActivity extends AppCompatActivity {

    ListView list;
    private ArrayList<FacultyBean> facultyBeanList;
    private ArrayAdapter<String> adapter;
    DBAdapter db=new DBAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_faculty);

        final ArrayList<String> facultyList=new ArrayList<>();
        list=findViewById(R.id.list);
        facultyBeanList=db.getAllFaculty();

        for (FacultyBean fb:facultyBeanList){
            String name=fb.getFaculty_firstname()+" "+fb.getFaculty_lastname();
            facultyList.add(name);
        }
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,facultyList);
        list.setAdapter(adapter);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                new AlertDialog.Builder(ViewFacultyActivity.this).setIcon(android.R.drawable.ic_menu_delete).
                        setTitle("DELETE").
                        setMessage("Are You Sure").
                        setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int id) {
                                facultyList.remove(i);
                                adapter.notifyDataSetChanged();
                                adapter.notifyDataSetInvalidated();

                                db.deleteFaculty(facultyBeanList.get(i).getFaculty_id());
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ViewFacultyActivity.this, "Faculty is not removed", Toast.LENGTH_SHORT).show();
                    }
                }).show();

                return false;
            }
        });
    }
}