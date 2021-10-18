package com.cocos.develop.dictionarykiss.ui.history

import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cocos.develop.core.base.BaseActivity
import com.cocos.develop.dictionarykiss.R
import com.cocos.develop.dictionarykiss.di.injectDependencies
import com.cocos.develop.dictionarykiss.ui.description.DescriptionActivity
import com.cocos.develop.dictionarykiss.ui.main.*
import com.cocos.develop.model.data.AppState
import com.cocos.develop.model.data.DataModel
import com.cocos.develop.utils.ui.viewById
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_history.empty_linear_layout
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.scope.currentScope

class HistoryActivity : BaseActivity<AppState, MainInteractor>() {

    override lateinit var model: HistoryViewModel

    //Объявляем переменные на уровне класса
    private val historyRecyclerview by viewById<RecyclerView>(R.id.history_activity_recyclerview)
    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }

    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                startActivity(
                    DescriptionActivity.getIntent(
                        this@HistoryActivity,
                        data
                    )
                )
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        iniViewModel()
        initViews()
    }

    private fun iniViewModel() {
        check(history_activity_recyclerview.adapter == null) { getString(R.string.error_initialised) }
        injectDependencies()
        val viewModel: HistoryViewModel by currentScope.inject()
        model = viewModel
        model.getData("", false)
        model.subscribe().observe(this@HistoryActivity, {
            renderData(it)
            empty_linear_layout.isVisible = emptyLayout})

    }

    override fun setDataToScreen(data: List<DataModel>) {
        adapter.setData(data)
    }

    private fun initViews() {
        historyRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
        historyRecyclerview.adapter = adapter

        val itemDecoration = DividerItemDecoration(this@HistoryActivity, LinearLayoutManager.VERTICAL)
        itemDecoration.setDrawable(
            ResourcesCompat.getDrawable(resources, R.drawable.separator_vertical, null)!!
        )
        historyRecyclerview.addItemDecoration(itemDecoration)
    }
}