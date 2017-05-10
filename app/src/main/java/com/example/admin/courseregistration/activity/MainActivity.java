package com.example.admin.courseregistration.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.courseregistration.R;
import com.example.admin.courseregistration.service.DBHelper;
import com.example.admin.courseregistration.service.PreferencesManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.major_button)
    Button major_button;

    @BindView(R.id.registration_button)
    Button registration_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        init();
    }

    public void init(){
        PreferencesManager.getInstance(getApplicationContext()).setID("1771321");

        title.setText("메인");

        major_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MajorActivity.class);
                startActivity(intent);
            }
        });

        registration_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}
