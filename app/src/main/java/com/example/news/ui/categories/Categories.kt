package com.example.news.ui.categories

import com.example.news.R

data class Categories(val id : String,
val name : String,
val imageId : Int,
val bg_colorId : Int){
    companion object{
        fun getCategoriesList() : List<Categories>{
            return listOf(
                Categories(
                    id = "business",
                    name = "Business",
                    imageId = R.drawable.busines,
                    bg_colorId = R.color.orange
                ),
            Categories(
                id = "entertainment",
                name = "Entertainment",
                imageId = R.drawable.entertainment,
                bg_colorId = R.color.blue
            ),
            Categories(
                id = "health",
                name = "Health",
                imageId = R.drawable.health,
                bg_colorId = R.color.bink
            ),
            Categories(
                id = "science",
                name = "Science",
                imageId = R.drawable.science,
                bg_colorId = R.color.yellow
            ),
            Categories(
                id = "sports",
                name = "Sports",
                imageId = R.drawable.sports,
                bg_colorId = R.color.red
            ),
            Categories(
                id = "technology",
                name = "Technology",
                imageId = R.drawable.technology,
                bg_colorId = R.color.babyBlue
            )
            )
        }
    }
}
