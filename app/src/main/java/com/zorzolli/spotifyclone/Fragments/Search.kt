package com.zorzolli.spotifyclone.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zorzolli.spotifyclone.R
import com.zorzolli.spotifyclone.model.Section
import com.zorzolli.spotifyclone.model.data
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.section_item.view.*

class Search : Fragment() {

    private lateinit var adapterSection: AdapterSection

    companion object {
        fun newInstance(): Search {
            val fragmentSearch = Search()
            val arguments = Bundle()
            fragmentSearch.arguments = arguments
            return fragmentSearch
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapterSection = AdapterSection(data())
        recycler_view_section.adapter = adapterSection
        recycler_view_section.layoutManager = GridLayoutManager(context, 2)
    }

    private inner class AdapterSection(private val sections: MutableList<Section>): RecyclerView.Adapter<HolderSection>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderSection {
            return HolderSection(layoutInflater.inflate(R.layout.section_item, parent, false))
        }

        override fun getItemCount(): Int = sections.size

        override fun onBindViewHolder(holder: HolderSection, position: Int) {
            val section: Section = sections[position]
            holder.bind(section)
        }


    }

    private inner class HolderSection(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(section: Section) {
            itemView.image_section.setImageResource(section.section)
            itemView.sectionName.text = section.sectionName
        }
    }

}