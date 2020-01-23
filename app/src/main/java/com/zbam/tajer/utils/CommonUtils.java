package com.zbam.tajer.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zbam.tajer.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by z.bazoubandi on 7/22/2018.
 */

public final class CommonUtils {

    private static Dialog dialog;

    public static void showToast(Activity context, String message) {
        //Creating the LayoutInflater instance
        LayoutInflater li = context.getLayoutInflater();
        //Getting the View object as defined in the layout_custom_toast.xml file
        View layout = li.inflate(R.layout.layout_custom_toast,
                (ViewGroup) context.findViewById(R.id.custom_toast_layout));
        ((TextView) layout.findViewById(R.id.toast_msg)).setText(message);
        //Creating the Toast object
        Toast toast = new Toast(context.getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);//setting the view of custom toast layout
        toast.show();
    }

    public static void dismissDialog() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pattern;
        Matcher matcher;
        final String phonePatern = "^(0{1}9{1})(\\d{9})$|^(9{1})(\\d{9})$";
        pattern = Pattern.compile(phonePatern);
        matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static void showAlert(Context context, String title, String msg, final IL listner) {
        try {
            TextView tvAlertTitle, tvAlertMessage;
            Button btnAlertOK;
            View dialogAlert = LayoutInflater.from(context).inflate(R.layout.dialog_alert, null);
            tvAlertTitle = (TextView) dialogAlert.findViewById(R.id.tv_alert_title);
            tvAlertMessage = (TextView) dialogAlert.findViewById(R.id.tv_alert_message);
            tvAlertMessage.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            btnAlertOK = (Button) dialogAlert.findViewById(R.id.btn_alert_ok);
            if (TextUtils.isEmpty(title))
                tvAlertTitle.setVisibility(View.GONE);
            else {
                tvAlertTitle.setVisibility(View.VISIBLE);
                tvAlertTitle.setText(title);
            }

            if (!TextUtils.isEmpty(msg))
                tvAlertMessage.setText(msg);

            btnAlertOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismissDialog();
                    if (listner != null)
                        listner.onSuccess();
                }
            });
            showDialogWithView(context, dialogAlert, listner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showDialogWithView(Context activity, final View view, final IL listner) {
        try {
            dismissDialog();
            dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setContentView(view);
            dialog.setCanceledOnTouchOutside(false);
            WindowManager.LayoutParams windowLayout = dialog.getWindow().getAttributes();
            windowLayout.gravity = Gravity.CENTER;
            dialog.show();
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        dialog.dismiss();
                        if (listner != null)
                            listner.onCancel();
                    }
                    return false;
                }
            });
            dialog.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public interface IL {
        void onSuccess();

        void onCancel();
    }





}
