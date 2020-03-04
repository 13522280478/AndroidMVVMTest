package com.pltest.mvvmtest

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

/**
 * @author YueShuai
 * @date 2020/3/4
 * @Describe
 *
 * <p>
 * Email : yueshuai@pupupula.com
 */

@Keep
@Parcelize
data class Bean(val name:String, var isSelect:Boolean) : Parcelable