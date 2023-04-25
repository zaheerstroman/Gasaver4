package com.gasaver.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;

import com.gasaver.R;
import com.gasaver.interfaces.DilogClickListener;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {

    static ProgressDialog progressDialog;

    public static void showLoading(Context mContext, String message, boolean cancelable) {
        try {
            if (progressDialog != null && progressDialog.isShowing())
                progressDialog.dismiss();
            progressDialog = new ProgressDialog(mContext, R.style.AppTheme_Loading_Dialog);
            progressDialog.setMessage(message);
            progressDialog.setCancelable(cancelable);
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    dialog.dismiss();
                }
            });
            progressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void hideLoading() {
        try {
            if (progressDialog != null && progressDialog.isShowing())
                progressDialog.dismiss();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void hideKeyboard(Context mContext) {
        try {
            InputMethodManager inputManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(((Activity) mContext).getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showAlert(Context context, String title, String message, DilogClickListener dilogClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (title != null) {
            builder.setTitle(title);
        }
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (dilogClickListener != null)
                    dilogClickListener.onDilogButtonClicked();
            }
        });
        builder.show();
    }

    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }


    }

    public static Date getDate(String date) {
        //String date_ = date;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date mDate = sdf.parse(date);
            return mDate;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public static long convertPriceUnitToRs(String priceUnit) {
        switch (priceUnit.toLowerCase()) {
            case "rs":
                return 1;
            case "hundred(s)":
                return 100;
            case "thousand(s)":
                return 1000;
            case "lac(s)":
                return 100000;
            case "crore(s)":
                return 10000000;
            default:
                return 1;

        }
    }

    public static String roundUpDecimal(double value, int round) {
        DecimalFormat myFormat;
        try {
            if (round == 2)
                myFormat = new DecimalFormat("0.00");
            else
                myFormat = new DecimalFormat("#.###");
            return myFormat.format(value);
        } catch (Exception e) {
            e.printStackTrace();
            return " ";
        }
    }
}
