package com.example.skywish.smspopup;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.alertdialogpro.AlertDialogPro;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.Inflater;

/**
 * Created by skywish on 2015/6/29.
 */
public class SMSBroadcastReceiver extends BroadcastReceiver {

    private String phone, content, time;
    private LinearLayout msg_layout;

    @Override
    public void onReceive(Context context, Intent intent) {
//        Object[] pduses= (Object[])intent.getExtras().get("pdus");
//        for(Object pdus: pduses){
//            byte[] pdusmessage = (byte[])pdus;
//            SmsMessage sms = SmsMessage.createFromPdu(pdusmessage);
//            phone = sms.getOriginatingAddress();//发送短信的手机号码
//            content = sms.getMessageBody(); //短信内容
//            Date date = new Date(sms.getTimestampMillis());
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            time = format.format(date);  //得到发送时间
//        }

//        AlertDialogPro.Builder builder = new AlertDialogPro.Builder(context,
//                R.style.Theme_AlertDialogPro_Material_Light);
//        builder.setTitle(phone)
//                .setMessage(content)
//                .setPositiveButton("已读", null)
//                .setNegativeButton("CANCEL", null);
//        AlertDialog dia = builder.create();
//        dia.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
//        dia.setCanceledOnTouchOutside(true);
//        dia.show();

        AlertDialogPro.Builder builder = new AlertDialogPro.Builder(context,
                R.style.Theme_AlertDialogPro_Material_Light);
        builder.setTitle("+8618883385247")
                .setMessage("bb")
                .setPositiveButton("已读", null);
        AlertDialog dia = builder.create();
        dia.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dia.setCanceledOnTouchOutside(true);
        dia.show();
    }

}
