package com.tuts.prakash.simpleocr;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartUp extends AppCompatActivity {
FloatingActionButton cameraButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);
        cameraButton=findViewById(R.id.floatingActionButton4);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(newIntent);

            }
        });
    }
}
