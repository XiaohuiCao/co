package com.ch.co.sqlDemo;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ch.co.R;

public class DeleteActivity extends Activity {
    private DBAdapter dbAdapter;
    private EditText idDelete;
    private TextView labelView;
    private TextView displayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        idDelete = (EditText) findViewById(R.id.id_delete);
        labelView = (TextView) findViewById(R.id.label);
        displayView = (TextView) findViewById(R.id.display);
        Button deleteButton = (Button) findViewById(R.id.btn_delete);
        deleteButton.setOnClickListener(deleteButtonListener);
        dbAdapter = new DBAdapter(this);
        dbAdapter.open();
    }

    View.OnClickListener deleteButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String idStr = idDelete.getText().toString();
            if (TextUtils.isEmpty(idStr)) {
                showToast(" ID 必须要输入！");
                return;
            }
            long id = Integer.parseInt(idStr);
            long count = dbAdapter.deleteOne(id);
            if (count == 0) {
                labelView.setText("没有此 ID ，请重新输入！删除数据" + String.valueOf(count) + "条！");
            } else {
                labelView.setText("删除成功，删除数据" + String.valueOf(count) + "条！");
                String msg = "删除 ID 为" + idDelete.getText().toString() + "的数据" +
                        (count > 0 ? "成功" : "失败");
                labelView.setText(msg);
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
