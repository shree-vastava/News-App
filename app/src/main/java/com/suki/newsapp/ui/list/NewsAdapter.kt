package com.suki.newsapp.ui.list

import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.suki.newsapp.R
import com.suki.newsapp.entities.Doc
import com.suki.newsapp.ui.GridViewHolder
import com.suki.newsapp.ui.ListViewHolder
import com.suki.newsapp.util.NewsClickListener
import com.suki.newsapp.util.ViewType
import kotlinx.android.synthetic.main.layout_grid_item.view.*
import kotlinx.android.synthetic.main.layout_list_item.view.*
import java.text.SimpleDateFormat

class NewsAdapter(
    private val layoutManager: GridLayoutManager? = null,
    private val newsClickListener: NewsClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var newsItemList = ArrayList<Doc>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.LIST.ordinal -> ListViewHolder(parent)
            else -> GridViewHolder(parent)
        }
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        var itemView = p0.itemView
        var newsItem = newsItemList[p1]
        newsItem.display_date = getTimestamp(newsItem.pub_date)

        /**
         * When List view is selected
         */
        if (getItemViewType(p1) == ViewType.LIST.ordinal){

            /**
             * setting title text and
             * transition name to achieve
             * shared element transition
             */
            itemView.tv_title.apply {
               text = newsItem.headline.main
               transitionName = itemView.context.getString(R.string.transition_name_title)
           }

            /**
             * setting snippet text
             */
            itemView.tv_snippet.apply {
                text = newsItem.snippet
                transitionName = itemView.context.getString(R.string.transition_name_snippet)
            }

            /**
             * Setting date text
             */
            itemView.tv_timestamp.text = newsItem.display_date

            /**
             * Setting image if the multimedia
             * is not empty else a default image.
             * Setting transition name to achieve
             * Shared Element transition
             */
            if(newsItem.multimedia.isNotEmpty()) {
                Glide.with(itemView.context)
                    .load("https://static01.nyt.com/${newsItem.multimedia[0].url}")
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(itemView.image_view)
            }
            else{
                itemView.image_view.setImageResource(R.drawable.not_found)
            }
            itemView.image_view.transitionName = itemView.context.getString(R.string.transition_name_image)
        }

        /**
         * When Grid view is selected
         */
        else{
            itemView.tv_title_grid.apply {
                text = newsItem.headline.main
                transitionName = itemView.context.getString(R.string.transition_name_title)
            }

            /**
             * Setting date text
             */
            itemView.tv_timestamp_grid.text = newsItem.display_date

            if(newsItem.multimedia.isNotEmpty()) {
                Glide.with(itemView.context)
                    .load("https://static01.nyt.com/${newsItem.multimedia[0].url}")
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(itemView.image_view_grid)
            }
            else{
                itemView.image_view_grid.setImageResource(R.drawable.not_found)
            }
            itemView.image_view_grid.transitionName = itemView.context.getString(R.string.transition_name_image)
        }

        itemView.setOnClickListener {
            if(getItemViewType(p1) == ViewType.LIST.ordinal) {
                newsClickListener.onNewsClicked(
                    newsItem,
                    itemView.tv_title,
                    itemView.image_view
                )
            }
            else{
                newsClickListener.onNewsClicked(
                    newsItem,
                    itemView.tv_title_grid,
                    itemView.image_view_grid
                )
            }
        }
    }

    override fun getItemCount() = newsItemList.size

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager?.spanCount == 1) ViewType.LIST.ordinal
        else ViewType.GRID.ordinal
    }

    /**
     * Update list when new data is fetched.
     */
    fun updateList(newsList: ArrayList<Doc>){
        newsItemList.addAll(newsList)
        notifyDataSetChanged()
    }

    fun getTimestamp(date: String): String{
        if(date!=null){
            var inputSdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            val newDate = inputSdf.parse(date)
            var outputSdf = SimpleDateFormat("dd MMM yyyy, HH:mm")
            var outputDate = outputSdf.format(newDate)
            return "Published on $outputDate"
        }

        return "Published Date not found!"
    }
}