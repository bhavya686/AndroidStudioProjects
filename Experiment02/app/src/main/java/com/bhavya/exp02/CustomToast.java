package com.bhavya.exp02;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CustomToast {

    public static void show(Context context, String message) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View layout = inflater.inflate(
                R.layout.toast_layout,
                null
        );

        TextView txt = layout.findViewById(R.id.txtMessage);

        txt.setText(message);

        Toast toast = new Toast(context);

        toast.setDuration(Toast.LENGTH_SHORT);

        toast.setGravity(Gravity.CENTER,0,0);

        toast.setView(layout);

        toast.show();
    }
}