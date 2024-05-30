package com.task.utils

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.task.R
import com.task.ui.components.callback.OnItemDialogTapCallBack

class DialogHelper(val activity: Activity) {
    private var alertDialog: AlertDialog? = null

    fun showDialogHelperAlert(
        lister: OnItemDialogTapCallBack
    ) {
        val dialogBuilder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_helper, null)
        val view = dialogBuilder.setView(dialogView)
        alertDialog = dialogBuilder.create()
        alertDialog?.setCancelable(false)
        alertDialog?.setCanceledOnTouchOutside(false)
        alertDialog?.show()
    }

}