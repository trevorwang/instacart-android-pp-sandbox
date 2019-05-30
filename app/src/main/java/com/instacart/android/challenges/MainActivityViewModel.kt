package com.instacart.android.challenges

import com.instacart.android.challenges.network.NetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.Nullable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel {

    interface UpdateListener {
        fun onUpdate(state: ItemListViewState)
    }

    private var itemListViewState: ItemListViewState = ItemListViewState("Delivery Items", true, listOf())
        set(value) {
            listener?.onUpdate(state = value)
            field = value
        }
    private var listener: UpdateListener? = null

    fun setStateUpdateListener(@Nullable listener: UpdateListener?) {
        this.listener = listener
        listener?.onUpdate(itemListViewState)
    }


    fun loadDeliveryItems(): Disposable {
        return NetworkService.api.fetchOrderByIdObservable(1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    println(it.items)
                    itemListViewState = ItemListViewState("Delivery Items", false, it.items)
                }, {
                    println(it)
                })
    }
}
