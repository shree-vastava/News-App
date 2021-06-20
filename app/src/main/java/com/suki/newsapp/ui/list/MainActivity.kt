package com.suki.newsapp.ui.list

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.suki.newsapp.R
import com.suki.newsapp.entities.Doc
import com.suki.newsapp.ui.details.NewsDetailsActivity
import com.suki.newsapp.util.NewsClickListener
import com.suki.newsapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NewsClickListener {

    private val mainViewModel: MainViewModel by viewModels()
    private val gridLayoutManager = GridLayoutManager(this, 1)
    private val newsAdapter =
        NewsAdapter(gridLayoutManager, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Applying layoutManager
         * and adapter to recyclerview
         */
        rv_news.apply {
            layoutManager = gridLayoutManager
            adapter = newsAdapter
        }

        setupScrollListener()

        /**
         * Observing newsdata to update
         * the data on UI
         * Showing loader in center for
         * the first time and then at the
         * end of the list.
         */
        mainViewModel.newsData.observe(this, Observer {
            when(it.status){
                Resource.Status.LOADING ->{
                    progress_bar.visibility = View.VISIBLE
                    if(mainViewModel.pageNo==1){
                        rv_news.visibility = View.GONE
                    }
                }

                Resource.Status.SUCCESS ->{
                    if(mainViewModel.pageNo == 1){
                        rv_news.visibility = View.VISIBLE
                    }
                    progress_bar.visibility = View.GONE
                    newsAdapter.updateList(ArrayList(it.data))
                }

                Resource.Status.ERROR ->{
                    progress_bar.visibility = View.GONE
                    showErrorMessage(it.message)
                }
            }

        })

        mainViewModel.getNews()

        /**
         * click on layout change
         */
        ic_layout.setOnClickListener {
            switchLayout()
        }

    }

    private fun showErrorMessage(message: String?) {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show()
    }


    override fun onNewsClicked(doc: Doc,tv_title: TextView, image: ImageView) {
        /**
         * Using scene transition animation
         * for the shared element transition
         */
        var intent = Intent(this, NewsDetailsActivity::class.java)
        intent.putExtra("newsItem",doc)
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this,
            android.util.Pair(tv_title,tv_title.transitionName),
            android.util.Pair(image,image.transitionName)).toBundle())
    }

    fun switchLayout(){
        if (gridLayoutManager?.spanCount == 1) {
            gridLayoutManager?.spanCount = 2
            ic_layout.setImageResource(R.drawable.ic_list)
        } else {
            gridLayoutManager?.spanCount = 1
            ic_layout.setImageResource(R.drawable.ic_grid)
        }
        newsAdapter?.notifyItemRangeChanged(0, newsAdapter?.itemCount ?: 0)
    }

    /**
     * To fetch more data when
     * the list is scrolled from
     * the last item
     */
    private fun setupScrollListener() {
        rv_news.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)){
                    mainViewModel.getNews()
                }
            }
        })
    }
}