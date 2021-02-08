package com.zorzolli.spotifyclone.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import com.zorzolli.spotifyclone.FragmentsTab.Albums
import com.zorzolli.spotifyclone.FragmentsTab.Artists
import com.zorzolli.spotifyclone.FragmentsTab.Playlists
import com.zorzolli.spotifyclone.R
import kotlinx.android.synthetic.main.fragment_library.*

class Library : Fragment() {

    companion object {
        fun newInstance(): Library {
            val fragmentLibrary = Library()
            val arguments = Bundle()
            fragmentLibrary.arguments = arguments
            return fragmentLibrary
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var adapter = FragmentPagerItemAdapter(fragmentManager, FragmentPagerItems.with(context)
            .add("Playlists", Playlists::class.java)
            .add("Artistas", Artists::class.java)
            .add("Álbuns", Albums::class.java)
            .create())

        viewpager.adapter = adapter
        viewpagertab.setViewPager(viewpager)
    }
}