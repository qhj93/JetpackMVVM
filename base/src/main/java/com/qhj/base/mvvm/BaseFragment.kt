package com.qhj.base.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

/**
 * Created by 7in on 2021-09-28 9:25
 */
abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(
        private val inflate: (LayoutInflater) -> VB,
        private val clazz: Class<VM>) : Fragment(){

    var binding: VB? = null
    lateinit var viewModel: VM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = inflate(inflater)
        viewModel = ViewModelProvider(this).get(clazz)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    abstract fun init()

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}