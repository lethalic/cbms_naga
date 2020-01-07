package com.example.cbms_naga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cbms_naga.Questions.A_cbms_question;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.startsurvey) {
            Intent i = new Intent(MainActivity.this, A_cbms_question.class);
            startActivity(i);
        }

        if (v.getId() == R.id.listofsurvey) {
//            Intent i = new Intent(MainActivity.this, GraphMenu.class);
//            startActivity(i);
        }

        if (v.getId() == R.id.sendsurvey) {
//            Intent i = new Intent(MainActivity.this, RecyclerSearchView.class);
//            startActivity(i);
        }


    }
}
