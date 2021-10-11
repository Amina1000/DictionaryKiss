package com.cocos.develop.favoritescreen.ui

import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cocos.develop.core.base.BaseActivity
import com.cocos.develop.dictionarykiss.ui.description.DescriptionActivity
import com.cocos.develop.favoritescreen.R
import com.cocos.develop.favoritescreen.utils.injectDependencies
import com.cocos.develop.model.data.AppState
import com.cocos.develop.model.data.DataModel
import com.cocos.develop.utils.ui.viewById
import kotlinx.android.synthetic.main.activity_favorite.*
import org.koin.android.scope.currentScope

class FavoriteActivity : BaseActivity<AppState, FavoriteInteractor>() {
    override lateinit var model: FavoriteViewModel
    private val adapter: FavoriteAdapter by lazy { FavoriteAdapter(onListItemClickListener) }
    private val favoriteRecyclerview by viewById<RecyclerView>(R.id.favorite_activity_recyclerview)

    private val onListItemClickListener: FavoriteAdapter.OnListItemClickListener =
        object : FavoriteAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                startActivity(
                    DescriptionActivity.getIntent(
                        this@FavoriteActivity,
                        data
                    )
                )
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        iniViewModel()
        initViews()
    }

    override fun setDataToScreen(data: List<DataModel>) {
        adapter.setData(data)
    }

    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }

    private fun iniViewModel() {
        check(favorite_activity_recyclerview.adapter == null) { getString(R.string.error_initialised) }
        injectDependencies()
        val viewModel: FavoriteViewModel by currentScope.inject()
        model = viewModel
        model.subscribe().observe(this@FavoriteActivity, { renderData(it) })
    }

    private fun initViews() {
        favoriteRecyclerview.adapter = adapter

        val itemDecoration =
            DividerItemDecoration(this@FavoriteActivity, GridLayoutManager.VERTICAL)
        itemDecoration.setDrawable(
            ResourcesCompat.getDrawable(resources, R.drawable.separator_vertical_rv, null)!!
        )
        favoriteRecyclerview.addItemDecoration(itemDecoration)
    }
}