package com.pltest.mvvmtest

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.pltest.mvvmtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    private val mViewModel by lazy {
        ViewModelProviders.of(this).get(MainActViewModel::class.java)
    }

    private val mAdapter by lazy {
        MainAdapter(mViewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initUi()
        listener()
    }

    private fun initUi() {
        mBinding.viewModel = mViewModel
        mBinding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.adapter = mAdapter
        }
    }

    private fun listener() {
        mBinding.apply {
            btGet.setOnClickListener {
                mViewModel.getData()
            }
        }

        mViewModel.apply {
            dataLive.observe(this@MainActivity, Observer {
                mAdapter.setData(it)
            })

            onItemChangeLive.observe(this@MainActivity, Observer {
                mAdapter.notifyItemChanged(it)
            })
        }
    }
}