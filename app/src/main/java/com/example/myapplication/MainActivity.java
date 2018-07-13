package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity {
  private TextView textView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    textView = findViewById(R.id.textView0);

    Database database = new Database();
    database.execute((Object) null);
    try {
      textView.setText(database.get().toString());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

  }

}

