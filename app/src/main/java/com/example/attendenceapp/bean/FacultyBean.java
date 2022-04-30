package com.example.attendenceapp.bean;

public class FacultyBean {

    private int faculty_id;
    private String faculty_firstname;
    private String faculty_lastname;
    private String faculty_mobilenumber;
    private String faculty_address;
    private String faculty_username;
    private String faculty_password;

//    faculty id
    public int getFaculty_id(){
        return  faculty_id;
    }
    public void setFaculty_id(int faculty_id){
        this.faculty_id=faculty_id;
    }

//    faculty first name
    public  String getFaculty_firstname(){
        return faculty_firstname;
    }
    public  void setFaculty_firstname(String faculty_firstname){
        this.faculty_firstname=faculty_firstname;
    }

//    faculty lastname
    public  String getFaculty_lastname(){
        return faculty_lastname;
    }
    public  void setFaculty_lastname(String faculty_lastname){
        this.faculty_lastname=faculty_lastname;
    }

//    faculty mobile number

    public  String getFaculty_mobilenumber(){
        return faculty_mobilenumber;
    }
    public  void setFaculty_mobilenumber(String faculty_mobilenumber){
        this.faculty_mobilenumber=faculty_mobilenumber;
    }

// faculty address
     public  String getFaculty_address(){
    return faculty_address;
}
      public  void setFaculty_address(String faculty_address){
        this.faculty_address=faculty_address;
    }

//    faculty user name
public  String getFaculty_username(){
    return faculty_username;
}
    public  void setFaculty_username(String faculty_username){
        this.faculty_username=faculty_username;
    }

//    password
public  String getFaculty_password(){
    return faculty_password;
}
    public  void setFaculty_password(String faculty_password){
        this.faculty_password=faculty_password;
    }

}
