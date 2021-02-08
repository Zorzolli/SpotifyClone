package com.zorzolli.spotifyclone.model

import com.google.gson.annotations.SerializedName
import com.zorzolli.spotifyclone.R

data class Category(

    @SerializedName("titulo") var title: String = "",
    @SerializedName("albuns") var albums: List<Album> = arrayListOf()

)

data class Album(

    @SerializedName("url_imagem") var album: String = "",
    @SerializedName("id") var id: Int = 0
)

data class Categories(@SerializedName("categoria")

    val categories: List<Category>

)

data class Section(

        var section: Int,
        var sectionName: String

)

class builderSection {

    var section: Int = 0
    var sectionName: String = ""

    fun build(): Section = Section(section, sectionName)

}

fun section(block: builderSection.() -> Unit): Section = builderSection().apply(block).build()

fun data(): MutableList<Section> = mutableListOf(

        section {
            section = R.drawable.secao1
            sectionName = "Podcasts"
        },

        section {
            section = R.drawable.secao2
            sectionName = "Lançamentos"
        },

        section {
            section = R.drawable.secao1
            sectionName = "Em casa"
        },

        section {
            section = R.drawable.secao3
            sectionName = "Paradas"
        },

        section {
            section = R.drawable.secao4
            sectionName = "Shows"
        },

        section {
            section = R.drawable.secao2
            sectionName = "Made for You"
        },

        section {
            section = R.drawable.secao1
            sectionName = "Brasil"
        },

        section {
            section = R.drawable.secao2
            sectionName = "Funk"
        }
)

