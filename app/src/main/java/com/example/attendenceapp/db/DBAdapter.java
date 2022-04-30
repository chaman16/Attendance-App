package com.example.attendenceapp.db;



import android.content.Context;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;


import com.example.attendenceapp.bean.AttendaceSessionBean;
import com.example.attendenceapp.bean.AttendanceBean;
import com.example.attendenceapp.bean.FacultyBean;
import com.example.attendenceapp.bean.StudentBean;

import java.util.ArrayList;

public class DBAdapter extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    public static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "Attendance";

    // Contacts table name
    public static final String FACULTY_INFO_TABLE = "faculty_table";
    public static final String STUDENT_INFO_TABLE = "student_table";
    public static final String ATTENDANCE_SESSION_TABLE = "attendance_session_table";
    public static final String ATTENDANCE_TABLE = "attendance_table";

    // Contacts Table Columns names
    public static final String KEY_FACULTY_ID = "faculty_id";
    public static final String KEY_FACULTY_FIRSTNAME = "faculty_firstname";
    public static final String KEY_FACULTY_LASTNAME = "faculty_Lastname";
    public static final String KEY_FACULTY_MO_NO = "faculty_mobilenumber";
    public static final String KEY_FACULTY_ADDRESS = "faculty_address";
    public static final String KEY_FACULTY_USERNAME = "faculty_username";
    public static final String KEY_FACULTY_PASSWORD = "faculty_password";

    public static final String KEY_STUDENT_ID = "student_id";
    public static final String KEY_STUDENT_FIRSTNAME = "student_firstname";
    public static final String KEY_STUDENT_LASTNAME = "student_lastname";
    public static final String KEY_STUDENT_MO_NO = "student_mobilenumber";
    public static final String KEY_STUDENT_ADDRESS = "student_address";
    public static final String KEY_STUDENT_DEPARTMENT = "student_department";
    public static final String KEY_STUDENT_CLASS = "student_class";

    public static final String KEY_ATTENDANCE_SESSION_ID = "attendance_session_id";
    public static final String KEY_ATTENDANCE_SESSION_FACULTY_ID = "attendance_session_faculty_id";
    public static final String KEY_ATTENDANCE_SESSION_DEPARTMENT = "attendance_session_department";
    public static final String KEY_ATTENDANCE_SESSION_CLASS = "attendance_session_class";
    public static final String KEY_ATTENDANCE_SESSION_DATE = "attendance_session_date";
    public static final String KEY_ATTENDANCE_SESSION_SUBJECT = "attendance_session_subject";

    public static final String KEY_SESSION_ID = "attendance_session_id";
    public static final String KEY_ATTENDANCE_STUDENT_ID = "attendance_student_id";
    public static final String KEY_ATTENDANCE_STATUS = "attendance_status";



    public DBAdapter (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryFaculty="CREATE TABLE "+ FACULTY_INFO_TABLE +" (" +
                KEY_FACULTY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_FACULTY_FIRSTNAME + " TEXT, " +
                KEY_FACULTY_LASTNAME + " TEXT, " +
                KEY_FACULTY_MO_NO + " TEXT, " +
                KEY_FACULTY_ADDRESS + " TEXT," +
                KEY_FACULTY_USERNAME + " TEXT," +
                KEY_FACULTY_PASSWORD + " TEXT " + ")";
        Log.d("queryFaculty",queryFaculty);

        String queryStudent="CREATE TABLE "+ STUDENT_INFO_TABLE +" (" +
                KEY_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_STUDENT_FIRSTNAME + " TEXT, " +
                KEY_STUDENT_LASTNAME + " TEXT, " +
                KEY_STUDENT_MO_NO + " TEXT, " +
                KEY_STUDENT_ADDRESS + " TEXT," +
                KEY_STUDENT_DEPARTMENT + " TEXT," +
                KEY_STUDENT_CLASS + " TEXT " + ")";
        Log.d("queryStudent",queryStudent );

        String queryAttendanceSession="CREATE TABLE "+ ATTENDANCE_SESSION_TABLE +" (" +
                KEY_ATTENDANCE_SESSION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_ATTENDANCE_SESSION_FACULTY_ID + " INTEGER, " +
                KEY_ATTENDANCE_SESSION_DEPARTMENT + " TEXT, " +
                KEY_ATTENDANCE_SESSION_CLASS + " TEXT, " +
                KEY_ATTENDANCE_SESSION_DATE + " DATE," +
                KEY_ATTENDANCE_SESSION_SUBJECT + " TEXT" + ")";
        Log.d("queryAttendanceSession",queryAttendanceSession );


        String queryAttendance="CREATE TABLE "+ ATTENDANCE_TABLE +" (" +
                KEY_SESSION_ID + " INTEGER, " +
                KEY_ATTENDANCE_STUDENT_ID + " INTEGER, " +
                KEY_ATTENDANCE_STATUS + " TEXT " + ")";
        Log.d("queryAttendance",queryAttendance );

       try {
           sqLiteDatabase.execSQL(queryFaculty);
           sqLiteDatabase.execSQL(queryStudent);

           sqLiteDatabase.execSQL(queryAttendanceSession);
           sqLiteDatabase.execSQL(queryAttendance);

       }catch (Exception e){
           e.printStackTrace();

           Log.e("exception",e.getMessage());
       }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String queryFaculty="CREATE TABLE "+ FACULTY_INFO_TABLE +" (" +
                KEY_FACULTY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_FACULTY_FIRSTNAME + " TEXT, " +
                KEY_FACULTY_LASTNAME + " TEXT, " +
                KEY_FACULTY_MO_NO + " TEXT, " +
                KEY_FACULTY_ADDRESS + " TEXT," +
                KEY_FACULTY_USERNAME + " TEXT," +
                KEY_FACULTY_PASSWORD + " TEXT " + ")";
        Log.d("queryFaculty",queryFaculty);

        String queryStudent="CREATE TABLE "+ STUDENT_INFO_TABLE +" (" +
                KEY_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_STUDENT_FIRSTNAME + " TEXT, " +
                KEY_STUDENT_LASTNAME + " TEXT, " +
                KEY_STUDENT_MO_NO + " TEXT, " +
                KEY_STUDENT_ADDRESS + " TEXT," +
                KEY_STUDENT_DEPARTMENT + " TEXT," +
                KEY_STUDENT_CLASS + " TEXT " + ")";
        Log.d("queryStudent",queryStudent );


        String queryAttendanceSession="CREATE TABLE "+ ATTENDANCE_SESSION_TABLE +" (" +
                KEY_ATTENDANCE_SESSION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_ATTENDANCE_SESSION_FACULTY_ID + " INTEGER, " +
                KEY_ATTENDANCE_SESSION_DEPARTMENT + " TEXT, " +
                KEY_ATTENDANCE_SESSION_CLASS + " TEXT, " +
                KEY_ATTENDANCE_SESSION_DATE + " DATE," +
                KEY_ATTENDANCE_SESSION_SUBJECT + " TEXT" + ")";
        Log.d("queryAttendanceSession",queryAttendanceSession );


        String queryAttendance="CREATE TABLE "+ ATTENDANCE_TABLE +" (" +
                KEY_SESSION_ID + " INTEGER, " +
                KEY_ATTENDANCE_STUDENT_ID + " INTEGER, " +
                KEY_ATTENDANCE_STATUS + " TEXT " + ")";
        Log.d("queryAttendance",queryAttendance );
        try {
            sqLiteDatabase.execSQL(queryFaculty);
            sqLiteDatabase.execSQL(queryStudent);
            sqLiteDatabase.execSQL(queryAttendanceSession);
            sqLiteDatabase.execSQL(queryAttendance);
            Log.i( "onUpgrade: ",sqLiteDatabase.toString());
        }catch (Exception e){
            e.printStackTrace();
            Log.e("exception",e.getMessage());

        }

    }
// working on faculty

//    add faculty
 public void addFaculty(FacultyBean facultyBean){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="INSERT INTO faculty_table(faculty_firstname,faculty_Lastname,faculty_mobilenumber,faculty_address,faculty_username,faculty_password) values ('"+
                facultyBean.getFaculty_firstname()+ "', '"+
                facultyBean.getFaculty_lastname()+"','"+
                facultyBean.getFaculty_mobilenumber()+"','"+
                facultyBean.getFaculty_address()+"','"+
                facultyBean.getFaculty_username()+"','"+
                facultyBean.getFaculty_password()+"')";
        Log.d("query",query);
        db.execSQL(query);
        db.close();
 }
// valid faculty
    public FacultyBean validateFaculty(String userName,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="SELECT * FROM faculty_table where faculty_username ='"+ userName+"' and faculty_password ='"+password+"'";
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            FacultyBean facultyBean=new FacultyBean();
            facultyBean.setFaculty_id(Integer.parseInt(cursor.getString(0)));
            facultyBean.setFaculty_firstname(cursor.getString(1));
            facultyBean.setFaculty_lastname(cursor.getString(2));
            facultyBean.setFaculty_mobilenumber(cursor.getString(3));
            facultyBean.setFaculty_address(cursor.getString(4));
            facultyBean.setFaculty_username(cursor.getString(5));
            facultyBean.setFaculty_password(cursor.getString(6));


            return  facultyBean;
        }
        return null;
    }

//    add Student
    public void addStudent(StudentBean studentBean){
        SQLiteDatabase db= this.getWritableDatabase();
        String query="INSERT INTO student_table ( student_firstname,student_lastname,student_mobilenumber,student_address,student_department,student_class ) values ('"+
                studentBean.getStudent_firstname() +"','"+
                studentBean.getStudent_lastname()+"','"+
                studentBean.getStudent_mobilenumber()+"','"+
                studentBean.getStudent_address()+"','"+
                studentBean.getStudent_department()+"','"+
                studentBean.getStudent_class()+"')";
            db.execSQL(query);
            db.close();
    }
//    All student by branch year

    public ArrayList<StudentBean> getAllStudentByBranchYear(String branch, String year) {
        ArrayList<StudentBean> list=new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        String query = "SELECT * FROM student_table where student_department='"+branch+"' and student_class='"+year+"'";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do{
                StudentBean studentBean = new StudentBean();
                studentBean.setStudent_id(Integer.parseInt(cursor.getString(0)));
                studentBean.setStudent_firstname(cursor.getString(1));
                studentBean.setStudent_lastname(cursor.getString(2));
                studentBean.setStudent_mobilenumber(cursor.getString(3));
                studentBean.setStudent_address(cursor.getString(4));
                studentBean.setStudent_department(cursor.getString(5));
                studentBean.setStudent_class(cursor.getString(6));
                Log.i( "getAllStudentByBr ",studentBean.getStudent_firstname());
                list.add(studentBean);
            }while(cursor.moveToNext());
        }
        return list;
    }

    public void deleteStudent(int studentId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM student_table WHERE student_id="+studentId ;

        Log.d("query", query);
        db.execSQL(query);
        db.close();

    }
//    get All faculty

    public ArrayList<FacultyBean> getAllFaculty() {
        ArrayList<FacultyBean> list=new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        String query="SELECT * FROM faculty_table";
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst())
         {
            do {
                FacultyBean facultyBean=new FacultyBean();
                facultyBean.setFaculty_id(Integer.parseInt(cursor.getString(0)));
                facultyBean.setFaculty_firstname(cursor.getString(1));
                facultyBean.setFaculty_lastname(cursor.getString(2));
                facultyBean.setFaculty_mobilenumber(cursor.getString(3));
                facultyBean.setFaculty_address(cursor.getString(4));
                facultyBean.setFaculty_username(cursor.getString(5));
                facultyBean.setFaculty_password(cursor.getString(6));
                list.add(facultyBean);
            }while (cursor.moveToNext());
        }
        return  list;
    }
//    delete faculty
    public void deleteFaculty(int facultyId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM faculty_table WHERE faculty_id="+facultyId ;

        Log.d("query", query);
        db.execSQL(query);
        db.close();
    }

    public int addAttendanceSession(AttendaceSessionBean attendanceSessionBean) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO attendance_session_table (attendance_session_faculty_id,attendance_session_department,attendance_session_class,attendance_session_date,attendance_session_subject) values ('"+
                attendanceSessionBean.getAttendance_session_faculty_id()+"', '"+
                attendanceSessionBean.getAttendance_session_department()+"','"+
                attendanceSessionBean.getAttendance_session_class()+"', '"+
                attendanceSessionBean.getAttendance_session_date()+"', '"+
                attendanceSessionBean.getAttendance_session_subject()+"')";
        Log.d("query", query);
        db.execSQL(query);

        String query1= "select max(attendance_session_id) from attendance_session_table";
        Cursor cursor = db.rawQuery(query1, null);

        if(cursor.moveToFirst())
        {
            int sessionId = Integer.parseInt(cursor.getString(0));

            return sessionId;
        }


        db.close();
        return 0;

    }
//    add new attendance

    public void addNewAttendance(AttendanceBean attendanceBean) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO attendance_table values ("+
                attendanceBean.getAttendance_session_id()+", "+
                attendanceBean.getAttendance_student_id()+", '"+
                attendanceBean.getAttendance_status()+"')";
        Log.d("query", query);
        db.execSQL(query);
        db.close();
    }
//get Attendance by session id
    public ArrayList<AttendanceBean> getAttendanceBySessionID(AttendaceSessionBean attendanceSessionBean) {
        int attendanceSessionId=0;
        ArrayList<AttendanceBean> list = new ArrayList<AttendanceBean>();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM attendance_session_table where attendance_session_faculty_id="+attendanceSessionBean.getAttendance_session_faculty_id()+""
                +" AND attendance_session_department='"+attendanceSessionBean.getAttendance_session_department()+"' AND attendance_session_class='"+attendanceSessionBean.getAttendance_session_class()+"'" +
                " AND attendance_session_date='"+attendanceSessionBean.getAttendance_session_date()+"' AND attendance_session_subject='"+attendanceSessionBean.getAttendance_session_subject()+"'";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do{
                attendanceSessionId=(Integer.parseInt(cursor.getString(0)));
            }while(cursor.moveToNext());
        }

        String query1="SELECT * FROM attendance_table where attendance_session_id=" + attendanceSessionId+" order by attendance_student_id";
        Cursor cursor1 = db.rawQuery(query1, null);
        if(cursor1.moveToFirst())
        {
            do{
                AttendanceBean attendanceBean = new AttendanceBean();
                attendanceBean.setAttendance_session_id(Integer.parseInt(cursor1.getString(0)));
                attendanceBean.setAttendance_student_id(Integer.parseInt(cursor1.getString(1)));
                attendanceBean.setAttendance_status(cursor1.getString(2));
                list.add(attendanceBean);

            }while(cursor1.moveToNext());
        }
        return list;
    }

    public StudentBean getStudentById(int attendance_student_id) {
        StudentBean studentBean = new StudentBean();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM student_table where student_id="+attendance_student_id;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do{

                studentBean.setStudent_id(Integer.parseInt(cursor.getString(0)));
                studentBean.setStudent_firstname(cursor.getString(1));
                studentBean.setStudent_lastname(cursor.getString(2));
                studentBean.setStudent_mobilenumber(cursor.getString(3));
                studentBean.setStudent_address(cursor.getString(4));
                studentBean.setStudent_department(cursor.getString(5));
                studentBean.setStudent_class(cursor.getString(6));

            }while(cursor.moveToNext());
        }
        return studentBean;
    }

    public ArrayList<AttendanceBean> getTotalAttendanceBySessionID(AttendaceSessionBean attendanceSessionBean) {
        int attendanceSessionId=0;
        ArrayList<AttendanceBean> list = new ArrayList<AttendanceBean>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM attendance_session_table where attendance_session_faculty_id='"+attendanceSessionBean.getAttendance_session_faculty_id()+"'"
                +" AND attendance_session_department='"+attendanceSessionBean.getAttendance_session_department()+"' AND attendance_session_class='"+attendanceSessionBean.getAttendance_session_class()+"'" +
                " AND attendance_session_subject='"+attendanceSessionBean.getAttendance_session_subject()+"'";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do{
                attendanceSessionId=(Integer.parseInt(cursor.getString(0)));

                String query1="SELECT * FROM attendance_table where attendance_session_id=" + attendanceSessionId+" order by attendance_student_id";
                Cursor cursor1 = db.rawQuery(query1, null);
                if(cursor1.moveToFirst())
                {
                    do{
                        AttendanceBean attendanceBean = new AttendanceBean();
                        attendanceBean.setAttendance_session_id(Integer.parseInt(cursor1.getString(0)));
                        attendanceBean.setAttendance_student_id(Integer.parseInt(cursor1.getString(1)));
                        attendanceBean.setAttendance_status(cursor1.getString(2));
                        list.add(attendanceBean);

                    }while(cursor1.moveToNext());
                }

                AttendanceBean attendanceBean = new AttendanceBean();
                attendanceBean.setAttendance_session_id(0);
                attendanceBean.setAttendance_status("Date : " + cursor.getString(4));
                list.add(attendanceBean);

            }while(cursor.moveToNext());
        }


        return list;
    }
//get All attendence by Student
    public ArrayList<AttendanceBean> getAllAttendanceByStudent() {
        ArrayList<AttendanceBean> list = new ArrayList<AttendanceBean>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT attendance_student_id,count(*) FROM attendance_table where attendance_status='P' group by attendance_student_id";

        Log.d("query", query);

        Cursor cursor = db.rawQuery(query, null);



        if(cursor.moveToFirst())
        {
            do{
                Log.d("studentId","studentId:"+cursor.getString(0)+", Count:"+cursor.getString(1));
                AttendanceBean attendanceBean = new AttendanceBean();
                attendanceBean.setAttendance_student_id(Integer.parseInt(cursor.getString(0)));
                attendanceBean.setAttendance_session_id(Integer.parseInt(cursor.getString(1)));
                list.add(attendanceBean);

            }while(cursor.moveToNext());
        }
        return list;
    }
}