package com.example.makeke;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class FamilyMembers extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new FamilyFragment()).commit();

    }

}
