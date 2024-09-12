package com.ch.fo.sqlDemo;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ch.co.R;

public class InsertActivity extends Activity {
    private DBAdapter dbAdapter;
    private EditText etBookName;
    private EditText etAuthor;
    private EditText etBookPrice;
    private TextView labelView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        etBookName = (EditText) findViewById(R.id.et_bookname);
        etAuthor = (EditText) findViewById(R.id.et_author);
        etBookPrice = (EditText) findViewById(R.id.et_bookprice);
        labelView = (TextView) findViewById(R.id.label);
        Button addButton = (Button) findViewById(R.id.btn_save);
        addButton.setOnClickListener(addButtonListener);
        dbAdapter = new DBAdapter(this);
        dbAdapter.open();
    }

    View.OnClickListener addButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String bookName = etBookName.getText().toString();
            String author = etAuthor.getText().toString();
            String priceStr = etBookPrice.getText().toString();
            if (TextUtils.isEmpty(bookName)) {
                showToast("请先填写图书名称");
                return;
            } else if (TextUtils.isEmpty(author)) {
                showToast("请先填写图书作者");
                return;
            } else if (TextUtils.isEmpty(priceStr)) {
                showToast("请先填写图书价格");
                return;
            }
            Book book = new Book();
            float bookPrice = Float.parseFloat(etBookPrice.getText().toString());
            book.setBookName(bookName);
            book.setAuthor(author);
            book.setBookPrice(bookPrice);
            long num = dbAdapter.insert(book);
            if (num == -1) {
                labelView.setText("添加数据失败！");
            } else {
                labelView.setText("成功添加数据， ID :" + String.valueOf(num));
            }
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        dbAdapter.close();
    }

    private void showToast(String desc) {
        Toast.makeText(this, desc, Toast.LENGTH_SHORT).show();
    }
}