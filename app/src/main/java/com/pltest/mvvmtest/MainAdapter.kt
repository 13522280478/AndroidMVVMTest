package com.pltest.mvvmtest

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pltest.mvvmtest.databinding.ItemMainAdapterBinding

/**
 * @author YueShuai
 * @date 2020/3/4
 * @Describe
 *
 * <p>
 * Email : yueshuai@pupupula.com
 */
class MainAdapter(val viewModel: MainActViewModel) : RecyclerView.Adapter<MainAdapter.Holder>() {

    var mData = mutableListOf<Bean>()

    fun setData(list: List<Bean>) {
        mData.clear()
        mData.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflate = ItemMainAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(inflate, viewModel)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindData(mData[position],position)
    }

    class Holder(val bind: ItemMainAdapterBinding, val viewModel: MainActViewModel) : RecyclerView.ViewHolder(bind.root) {

        fun bindData(bean1: Bean,position: Int) {
            bind.apply {
                rootView.setBackgroundColor(if (bean1.isSelect) Color.RED else Color.WHITE)
                rootView.setOnClickListener {
                    viewModel.onItemClick(position)
                }
                bind.bean = bean1
                executePendingBindings()
            }
        }
    }

}