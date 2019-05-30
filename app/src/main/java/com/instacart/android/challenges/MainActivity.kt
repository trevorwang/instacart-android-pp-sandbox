package com.instacart.android.challenges

import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter

    private val viewModel = MainActivityViewModel()
    private val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemScreenContainerView = findViewById<View>(R.id.item_screen_container)
        bindViews(itemScreenContainerView)

        viewModel.setStateUpdateListener(object : MainActivityViewModel.UpdateListener {
            override fun onUpdate(state: ItemListViewState) {
                renderItemList(state)
            }
        })
    }


    override fun onStart() {
        super.onStart()
        viewModel.loadDeliveryItems().addTo(compositeDisposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    private fun renderItemList(state: ItemListViewState) {
        adapter.update(state.items)
        toolbar.title = state.toolbarTitle
        empty_view.visibility = if (state.loading) View.VISIBLE else View.INVISIBLE
    }

    private fun bindViews(parent: View) {
        toolbar = parent.findViewById(R.id.toolbar)

        recyclerView = parent.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(parent.context, RecyclerView.VERTICAL, false)
        recyclerView.addItemDecoration(DividerDecoration(Color.BLACK, 1, Rect(100, 0, 0, 0)))


        adapter = ItemAdapter()
        recyclerView.adapter = adapter
    }
}
