package com.ch.fo.sqlDemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ch.co.R;

public class SqlMainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_main_activity);
        findViewById(R.id.insert).setOnClickListener(this);
        findViewById(R.id.query).setOnClickListener(this);
        findViewById(R.id.update).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.insert) {
            Intent intent = new Intent(this, InsertActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.query) {
            Intent intent = new Intent(this, QueryActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.update) {
            Intent intent = new Intent(this, UpdateActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.delete) {
            Intent intent = new Intent(this, DeleteActivity.class);
            startActivity(intent);
        }
    }
}