package com.qhj.base.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.qhj.base.R

/**
 * Created by 7in on 2021-09-28 9:10
 */
class LoadingDialog constructor(context: Context) : Dialog(context, R.style.TransparentDialog){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }
}