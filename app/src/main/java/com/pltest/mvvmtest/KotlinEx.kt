package com.pltest.mvvmtest

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author YueShuai
 * @date 2020/3/4
 * @Describe
 *
 * <p>
 * Email : yueshuai@pupupula.com
 */

fun Disposable.addTo(disposables: CompositeDisposable) {
    disposables.add(this)
}