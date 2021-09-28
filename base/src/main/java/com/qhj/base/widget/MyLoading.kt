package com.qhj.base.widget

import com.qhj.base.manager.TopActivityManager

/**
 * Created by 7in on 2021-09-28 9:10
 */
class MyLoading private constructor(){

    private var dialog: LoadingDialog? = null

    companion object{
        val instance: MyLoading by lazy(LazyThreadSafetyMode.SYNCHRONIZED){ MyLoading() }
    }

    fun showLoading(){
        if (dialog!=null && dialog!!.isShowing){
            if (dialog!!.context == TopActivityManager.instance.getTopActivity()){
                return
            } else {
                dialog!!.dismiss()
            }
        }
        dialog = LoadingDialog(TopActivityManager.instance.getTopActivity()!!)
        dialog!!.show()
    }

    fun dismiss() {
        if (dialog!=null && dialog!!.isShowing){
            dialog!!.dismiss()
        }
    }

}