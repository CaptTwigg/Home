/*
package com.example.andmysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.widget.TextView;

public class MainActivity extends Activity implements Runnable{

	private String hiduke="";
	private int price=0;
	private String errmsg="";
	
	public void run() {
		  System.out.println("Select Records Example by using the Prepared Statement!");
		  Connection con = null;
		  int count = 0;
		  try{
		  Class.forName("com.mysql.jdbc.Driver");
		  con = DriverManager.getConnection
		("jdbc:mysql://10.0.2.2:3306/stock","root","root");
		  try{
			  String sql;
		//	  sql
		//	  = "SELECT title,year_made FROM movies WHERE year_made >= ? AND year_made <= ?";
			  sql 
			  = "SELECT hiduke,jikan,code,price FROM table_stock";
		  PreparedStatement prest = con.prepareStatement(sql);
		  //prest.setInt(1,1980);
		  //prest.setInt(2,2004);
		  ResultSet rs = prest.executeQuery();
		  while (rs.next()){
		   hiduke = rs.getString(1);
		   price = rs.getInt(4);
		  count++;
		  System.out.println(hiduke + "\t" + "- " + price);
		  }
		  System.out.println("Number of records: " + count);
		  prest.close();
		  con.close();
		  }
		  catch (SQLException s){
		  System.out.println("SQL statement is not executed!");
		  errmsg=errmsg+s.getMessage();

		  }
		  }
		  catch (Exception e){
		  e.printStackTrace();
		  errmsg=errmsg+e.getMessage();
		  }

		  handler.sendEmptyMessage(0);
		
	}
	private Handler handler = new Handler() {
	      public void handleMessage(Message msg) {
	    	  
	          TextView textView = (TextView) findViewById(R.id.textView0);  
	          textView.setText("hiduke="+hiduke+" price="+price+" "+errmsg);  
	      }
	   };

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		Thread thread = new Thread(this);
	    thread.start();   
				  
		
		
		
	}


}*/
