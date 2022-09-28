package com.technipixl.exo1.models


data class Heroes(
   val data: Data? = null
) {
   data class Data(
      val results: List<Hero>? = null,
      val total: Int? = null // 1
   ) {
      data class Hero(
         val comics: Comics? = null,
         val id: Int? = null, // 1011334
         val name: String? = null, // 3-D Man
         val thumbnail: Thumbnail? = null
      ) {
         data class Comics(
            val items: List<Comic>? = null
         ) {
            data class Comic(
               val name: String? = null, // Avengers: The Initiative (2007) #14
               val resourceURI: String? = null // http://gateway.marvel.com/v1/public/comics/21366
            )
         }

         data class Thumbnail(
            val extension: String? = null, // jpg
            val path: String? = null // http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784
         )
      }
   }
}