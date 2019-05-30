package com.instacart.android.challenges

import com.instacart.android.challenges.network.DeliveryItem


data class ItemListViewState(
        val toolbarTitle: String,
        val loading: Boolean,
        val items: List<DeliveryItem>
)


data class ItemRow(val name: String)