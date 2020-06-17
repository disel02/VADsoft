package com.studio1hub.app.vadsoft.ui.article.list.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.studio1hub.app.vadsoft.R
import com.studio1hub.app.vadsoft.model.Article
import com.studio1hub.app.vadsoft.ui.article.detail.ArticleDetailActivity
import com.studio1hub.app.vadsoft.utils.NumFormat
import kotlinx.android.synthetic.main.article_item.view.*

class ArticleRecyclerViewAdapter(private val article: ArrayList<Article>) : RecyclerView.Adapter<ArticleRecyclerViewAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(article: Article) {
            itemView.apply {
                tvusername.text = article.user.name
                tvuserdesignation.text = article.user.designation
                ivuserprofile.load(article.user.avatar) {
                    placeholder(R.drawable.ic_photo)
                    transformations(CircleCropTransformation())
                }
                ivarticleimg.load(article.media.image) {
                    placeholder(R.drawable.ic_photo)
                }
                tvarticlecontent.text = article.content
                tvarticletitle.text = article.media.title
                tvarticleurl.text = article.media.url
                tvarticlelikes.text = "${NumFormat.numConvert(article.likes.toDouble(), 0)} Likes"
                tvarticlecomments.text = "${numFormat(article.comments.toDouble(), 0)} Comments"

                tvarticleurl.setOnClickListener {
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(article.media.url)))
                }
            }
        }

        private val c = charArrayOf('k', 'm', 'b', 't')

        private fun numFormat(n: Double, iteration: Int): String? {
            val d = n.toLong() / 100 / 10.0
            val isRound =
                d * 10 % 10 == 0.0
            return if (d < 1000)
                (if (d > 99.9 || isRound || !isRound && d > 9.99)
                    d.toInt() * 10 / 10 else d.toString() + ""
                        ).toString() + "" + c[iteration] else numFormat(d, iteration + 1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view: View
        val inflater = LayoutInflater.from(parent.context)
        view = inflater.inflate(R.layout.article_item, parent, false)
        val viewHolder = DataViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val intent = Intent(parent.context, ArticleDetailActivity::class.java)
            intent.putExtra("article_id", article[viewHolder.adapterPosition].id)
            parent.context.startActivity(intent)
        }
        return viewHolder
    }

    override fun getItemCount(): Int = article.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(article[position])
    }

    fun addArticle(article: List<Article>) {
        this.article.apply {
            clear()
            addAll(article)
        }

    }
}