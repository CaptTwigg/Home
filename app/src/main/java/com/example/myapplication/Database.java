package com.example.myapplication;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public class Database extends AsyncTask {

  @Override
  protected String doInBackground(Object[] objects) {

    String link = "http://mike.familien-jahn.dk/test/";

    try {
      URL url = new URL(link);
      HttpClient client = new DefaultHttpClient();
      HttpGet request = new HttpGet();
      request.setURI(new URI(link));
      HttpResponse response = client.execute(request);
      BufferedReader in = new BufferedReader(new
        InputStreamReader(response.getEntity().getContent()));
      StringBuffer sb = new StringBuffer("");
      String line = "";

      while ((line = in.readLine()) != null) {
        sb.append(line);
        //break;
      }

      in.close();
      System.out.println(json("[{\"temperature\":\"28.12\",\"date\":\"2018-07-12 20:51:07\"}]"));
      //System.out.println("page result: " + json(sb.toString()));
      return sb.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "null";
  }
  private String json(String json){
    JSONArray obj = null;
    try {
      obj = new JSONArray(json);
      JSONObject jsonObject  = obj.getJSONObject(0);
      return  jsonObject.get("date").toString();

    } catch (JSONException e) {
      e.printStackTrace();
    }
    return null;
  }
}

