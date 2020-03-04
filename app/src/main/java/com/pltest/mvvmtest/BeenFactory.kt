package com.pltest.mvvmtest

/**
 * @author YueShuai
 * @date 2020/3/4
 * @Describe
 *
 * <p>
 * Email : yueshuai@pupupula.com
 */

object BeenFactory {
    private var num = 0

    fun reqData(): MutableList<Bean> {
        val list = mutableListOf<Bean>()
        repeat(5) {
            num++
            list.add(Bean("我是数据$num", false))
        }
        return list
    }
}