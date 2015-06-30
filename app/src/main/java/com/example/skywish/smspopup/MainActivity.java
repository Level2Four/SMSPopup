package com.example.skywish.smspopup;

import java.text.SimpleDateFormat;

import android.app.Activity;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by skywish on 2015/6/28.
 */
public class MainActivity extends Activity {

    private SmsObserver smsObserver;
    private Uri SMS_INBOX = Uri.parse("content://sms/");
    private TextView tv_phone, tv_date, tv_content;
    private Button btn_open;
    private final String actionName = "com.example.skywish.action.SMSSEND";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        smsObserver = new SmsObserver(this, smsHandler);
        getContentResolver().registerContentObserver(SMS_INBOX, true, smsObserver);

        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_date = (TextView) findViewById(R.id.tv_date);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        btn_open = (Button) findViewById(R.id.btn_open);
        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(actionName);
                sendBroadcast(intent);
            }
        });
    }

    public Handler smsHandler = new Handler() {
        // 这里可以进行回调的操作
        // TODO
        public void handleMessage(android.os.Message msg) {
            System.out.println("smsHandler 执行了.....");
        }
    };

    public void getSmsFromPhone() {
        ContentResolver cr = getContentResolver();
        String[] projection = new String[] { "body","address","date"};
        // "_id", "address", "person",, "date", "type
        String where = " date >  "
                + (System.currentTimeMillis() - 10 * 60 * 1000);
        Cursor cur = cr.query(SMS_INBOX, projection, where, null, "date desc");
        if (null == cur)
            return;
        if (cur.moveToNext()) {
            String number = cur.getString(cur.getColumnIndex("address"));// 手机号
            String date = cur.getString(cur.getColumnIndex("date"));// 时间
            String body = cur.getString(cur.getColumnIndex("body"));

            //String 转 long
            long time = Long.valueOf(date).longValue();
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            //Long 转 string
            String dateTime = sdf.format(time);

            tv_date.setText(dateTime);
            tv_phone.setText(number);
            tv_content.setText(body);

//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle(number)
//                    .setMessage(body)
//                    .setPositiveButton("OK", null)
//                    .setNegativeButton("CANCEL", null);
//            AlertDialog dia = builder.create();
//            dia.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
//            dia.setCanceledOnTouchOutside(true);
//            dia.show();
        }
    }

    protected void showToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
    }

    class SmsObserver extends ContentObserver {

        public SmsObserver(Context context, Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            // 每当有新短信到来时，使用我们获取短消息的方法
            getSmsFromPhone();
        }
    }
}
