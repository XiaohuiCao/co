package com.ch.co.sqlDemo;

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

public class UpdateActivity extends Activity {
    private DBAdapter dbAdapter;
    private EditText booknameText;
    private EditText authorText;
    private EditText bookpriceText;
    private EditText idUpdate;
    private TextView labelView;
    private TextView displayView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        booknameText = (EditText) findViewById(R.id.et_bookname);
        authorText = (EditText) findViewById(R.id.et_author);
        bookpriceText = (EditText) findViewById(R.id.et_bookprice);
        idUpdate = (EditText) findViewById(R.id.id_update);
        labelView = (TextView) findViewById(R.id.label);
        displayView = (TextView) findViewById(R.id.display);
        Button updateButton = (Button) findViewById(R.id.btn_update);
        updateButton.setOnClickListener(updateButtonListener);
        dbAdapter = new DBAdapter(this);
        dbAdapter.open();
    }

    View.OnClickListener updateButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String bookName = booknameText.getText().toString();
            String author = authorText.getText().toString();
            String priceStr = bookpriceText.getText().toString();
            String idStr = idUpdate.getText().toString();
            if (TextUtils.isEmpty(bookName)) {
                showToast("请先填写图书名称");
                return;
            } else if (TextUtils.isEmpty(author)) {
                showToast("请先填写图书作者");
                return;
            } else if (TextUtils.isEmpty(priceStr)) {
                showToast("请先填写图书价格");
                return;
            } else if (TextUtils.isEmpty(idStr)) {
                showToast(" ID 必须要输入！");
                return;
            }
            Book book = new Book();
            Float bookPrice = Float.parseFloat(idStr);
            book.setBookName(bookName);
            book.setAuthor(author);
            book.setBookPrice(bookPrice);
            long id = Integer.parseInt(idUpdate.getText().toString());
            long count = dbAdapter.updateOne(id, book);
            if (count == -1) {
                labelView.setText("更新错误！");
            } else if (count == 0) {
                labelView.setText("没有此 ID ，请重新输入");
            }
        }
    };

    private void showToast(String desc) {
        Toast.makeText(this, desc, Toast.LENGTH_SHORT).show();
    }
}