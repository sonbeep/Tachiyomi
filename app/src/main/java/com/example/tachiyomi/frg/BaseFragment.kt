package com.example.tachiyomi.frg

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.R
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B: ViewBinding, M: ViewModel> : Fragment(){
    lateinit var appContext: Context
    lateinit var binding: B
    lateinit var viewModel: M

    override fun getContext(): Context? {
        return appContext
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.appContext = context
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = initViewBinding(inflater, container)
        viewModel = ViewModelProvider(this).get(initViewModel())
        return binding.root
        initView()
    }

    abstract fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): B

    abstract fun initView()

    abstract fun initViewModel(): Class<M>

}