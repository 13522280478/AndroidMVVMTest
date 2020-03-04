package com.pltest.mvvmtest

import android.annotation.SuppressLint
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

/**
 * @author YueShuai
 * @date 2020/3/4
 * @Describe
 *
 * <p>
 * Email : yueshuai@pupupula.com
 */

class MainActViewModel : ViewModel() {

    private var mDisposable = CompositeDisposable()

    private val _countLive = ObservableField<String>()
    val countLive get() = _countLive
    private fun refreshCount() {
        _countLive.set("当前条数 : " + _dataLive.value?.size)
    }

    private val _dataLive = MutableLiveData<MutableList<Bean>>()
    val dataLive get() = _dataLive
    private fun setData(list: MutableList<Bean>) {
        _dataLive.value = list
        refreshCount()
    }

    private fun addData(list: List<Bean>) {
        val data = _dataLive.value ?: mutableListOf<Bean>()
        data.addAll(list)
        _dataLive.value = data
        refreshCount()
    }

    /**
     * on click
     */

    private val _onItemChangeLive = MutableLiveData<Int>()
    val onItemChangeLive get() = _onItemChangeLive
    fun onItemClick(position: Int) {
        if (position > _dataLive.value?.size ?: 0) return
        val data = _dataLive.value ?: return
        data[position].isSelect = !data[position].isSelect
        _onItemChangeLive.value = position
    }


    /**
     * 获取数据
     */

    @SuppressLint("CheckResult")
    fun getData() {
        // 模拟网络请求
        val single = Single.timer(200, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    setData(BeenFactory.reqData())
                }, {

                })
        mDisposable.add(single)
    }

    @SuppressLint("CheckResult")
    fun addData() {
        // 同样模拟网络请求
        Single.timer(200, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    addData(BeenFactory.reqData())
                }, {

                }).addTo(mDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        mDisposable.clear()
    }
}