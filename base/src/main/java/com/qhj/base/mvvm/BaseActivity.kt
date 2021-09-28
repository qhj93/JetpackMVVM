package com.qhj.base.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

/**
 * Created by 7in on 2021-09-28 9:25
 */
abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel>(
    private val inflate: (LayoutInflater) -> VB,
    private val clazz: Class<VM>
) : AppCompatActivity() {

    lateinit var binding: VB
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(clazz)
        init()
    }

    abstract fun init()

}