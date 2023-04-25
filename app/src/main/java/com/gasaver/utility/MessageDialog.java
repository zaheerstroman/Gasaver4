package com.gasaver.utility;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.gasaver.R;

public class MessageDialog {

    public AlertDialog alertDialog;

    public MessageDialog(Context context, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.display_message_popup, null);

        TextView text_msg = (TextView) view.findViewById(R.id.text_msg);
        TextView btn_ok = (TextView) view.findViewById(R.id.btn_ok);
        text_msg.setText(message);
        builder.setView(view);

        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    public void displayMessageShow(){
        if(alertDialog!=null)
            alertDialog.show();
    }

    public void displayMessageHide(){
        if(alertDialog!=null)
            alertDialog.dismiss();
    }


}
