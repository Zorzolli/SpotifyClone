package com.zorzolli.spotifyclone.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.zorzolli.spotifyclone.Details
import com.zorzolli.spotifyclone.R
import com.zorzolli.spotifyclone.model.*
import kotlinx.android.synthetic.main.album_item.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_category.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home : Fragment() {

    private lateinit var adapterCategory: AdapterCategory

    companion object {
        fun newInstance(): Home {
            val fragmentHome = Home()
            val arguments = Bundle()
            fragmentHome.arguments = arguments
            return fragmentHome
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val categories = arrayListOf<Category>()
        adapterCategory = AdapterCategory(categories)
        recycler_view_categories.adapter = adapterCategory
        recycler_view_categories.layoutManager = LinearLayoutManager(context)
        retrofit().create(SpotifyAPI::class.java)
            .ListCategories()
            .enqueue(object : Callback<Categories>{
                override fun onFailure(call: Call<Categories>, t: Throwable) {
                    Toast.makeText(context, R.string.server_error.toString(), Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Categories>, response: Response<Categories>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            adapterCategory.categories.clear()
                            adapterCategory.categories.addAll(it.categories)
                            adapterCategory.notifyDataSetChanged()
                        }
                    }
                }
            })
    }
    private inner class AdapterCategory(internal val categories: MutableList<Category>): RecyclerView.Adapter<HolderCategory>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderCategory {
            return HolderCategory(layoutInflater.inflate(R.layout.item_category, parent, false))
        }

        override fun getItemCount(): Int = categories.size

        override fun onBindViewHolder(holder: HolderCategory, position: Int) {
            val categories = categories[position]
            holder.bind(categories)
        }
    }

    private inner class HolderCategory(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(category: Category) {
            itemView.text_title.text = category.title
            itemView.recycler_albums.adapter = AdapterAlbums(category.albums) {album ->
                val intent = Intent(context, Details::class.java)
                intent.putExtra("album", album.album)
                intent.putExtra("titles", titles[album.id])
                startActivity(intent)
            }
            itemView.recycler_albums.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }

    private inner class AdapterAlbums(private val albums: List<Album>, private val listener: ((Album) -> Unit)?): RecyclerView.Adapter<HolderAlbums>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderAlbums = HolderAlbums(
            layoutInflater.inflate(R.layout.album_item, parent, false), listener)

        override fun getItemCount(): Int = albums.size

        override fun onBindViewHolder(holder: HolderAlbums, position: Int) {
            val album = albums[position]
            holder.bind(album)
        }
    }

    private inner class HolderAlbums(itemView: View, val onClick: ((Album) -> Unit)?): RecyclerView.ViewHolder(itemView) {
        fun bind(album: Album) {
            Picasso.get().load(album.album).placeholder(R.drawable.placeholder).fit().into(itemView.image_album)
            itemView.image_album.setOnClickListener {
                onClick?.invoke(album)
            }
        }
    }
}