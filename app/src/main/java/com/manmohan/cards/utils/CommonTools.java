package com.manmohan.cards.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CommonTools {

  /**
   * check internet connection available or not
   *
   * @param context context of app
   * @return boolean true or false
   */
  public static boolean isNetworkConnected(Context context) {
    ConnectivityManager cm =
        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
  }

  /**
   * simple alert dialog with one positive button
   *
   * @param context Context
   * @param message message to be shown in dialog
   */
  public static void showAlertDialog(Context context, String message) {
    showAlertDialog(context, "",
        message,
        new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {

          }
        }, "OK",
        null, "",
        false
    );
  }

  /**
   * simple alert dialog with one positive button
   *
   * @param context context of activity
   * @param message message to be shown in dialog
   * @param title Title of alert dialog
   */
  public static void showAlertDialog(Context context, String title, String message) {
    showAlertDialog(context, title,
        message,
        new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {

          }
        }, "OK",
        null, "",
        false
    );
  }

  /**
   * Alert msg
   *
   * @param context Context
   * @param title Title of alert dialog
   * @param message message to be shown in dialog
   * @param posListener Dialog positive button click
   * @param posText Text of positive button
   * @param negListener Dialog negative button click
   * @param negText Text of negative button
   * @param isCancelable if cancelable dialog
   */
  public static void showAlertDialog(
      Context context, String title, String message,
      DialogInterface.OnClickListener posListener, String posText,
      DialogInterface.OnClickListener negListener, String negText, boolean isCancelable) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);

    if (posListener != null) {
      /*Setting OK Button*/
      builder.setPositiveButton(posText, posListener);
    }

    if (negListener != null) {
      /*Setting OK Button*/
      builder.setNegativeButton(negText, negListener);
    }

    AlertDialog alertDialog = builder.create();
    /*Setting Dialog Title*/
    alertDialog.setTitle(title);
    /*Setting Dialog Message*/
    alertDialog.setMessage(message);

    if (!isCancelable) {
      alertDialog.setCancelable(isCancelable);
    }
    /*Showing Alert Message*/
    alertDialog.show();
  }
}
