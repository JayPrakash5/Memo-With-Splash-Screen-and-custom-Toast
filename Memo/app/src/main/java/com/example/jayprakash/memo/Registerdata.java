package com.example.jayprakash.memo;


/**
 * Created by JAY PRAKASH on 21-09-2017.
 */

public class Registerdata {

        //private variables
        int _id;
        String user_name;
        String email_id;
        String password;


    public String getPassword() {
        return password;
        }
        public void setPassword(String password) {
        this.password = password;
        }


// Empty constructor
public Registerdata(){  }

// constructor
public Registerdata(int id, String  user_name, String email_id){
        this._id = id;
        this.user_name = user_name;
        this.email_id=email_id;
        }


// getting ID
        public int getID(){
        return this._id;
        }

// setting id
        public void setID(int id){
        this._id = id;
        }

public String getUserName() {
        // TODO Auto-generated method stub
        return user_name;
        }

        // setting  user name
        public void setUserName(String first_name){
        this.user_name = user_name;
        }


public String getEmailId() {
        // TODO Auto-generated method stub
        return email_id;
        }
        public void setEmailId(String email_id){
        this.email_id =email_id;
        }
        }
