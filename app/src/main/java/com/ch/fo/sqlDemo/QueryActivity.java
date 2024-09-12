package com.ch.fo.sqlDemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ch.co.R;

public class QueryActivity extends Activity {
    private DBAdapter dbAdapter;
    private EditText idQuery;
    private TextView labelView;
    private TextView displayView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        idQuery = (EditText) findViewById(R.id.id_query);
        labelView = (TextView) findViewById(R.id.label);
        displayView = (TextView) findViewById(R.id.display);
        Button queryButton = (Button) findViewById(R.id.query);
        queryButton.setOnClickListener(queryButtonListener);
        dbAdapter = new DBAdapter(this);
        dbAdapter.open();
//        displayAll();
    }

    private void displayAll() {
        Book[] books = dbAdapter.queryAll();
        if (books == null) {
            labelView.setText("数据库中没有数据");
            return;
        }
        labelView.setText("数据库：");
        String msg = "";
        for (int i = 0; i < books.length; i++) {
            msg += books[i].toString() + "\n ";
        }
        displayView.setText(msg);
    }

    View.OnClickListener queryButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String ii = idQuery.getText().toString();
            int id = 0;
            if (ii.isEmpty()) {
                labelView.setText("数据库中没有 ID 为" + String.valueOf(id) + "的数据");
                return;
            }
            id = Integer.parseInt(idQuery.getText().toString());
            Book[] books = dbAdapter.queryOne(id);
            if (books == null) {
                labelView.setText("数据库中没有 ID 为" + String.valueOf(id) + "的数据");
                return;
            }

            labelView.setText("数据库：");
            displayView.setText(books[0].toString());
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        dbAdapter.close();
    }
}
