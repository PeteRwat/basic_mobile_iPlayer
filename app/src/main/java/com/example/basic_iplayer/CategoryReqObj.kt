package com.example.basic_iplayer

data class CategoryReqObj (val category_highlights: CatHighLights = CatHighLights())

data class CatHighLights (val elements: Array<Element> = arrayOf(Element()))

data class Element (val title: String = "", val images: ImageDetails = ImageDetails(), val count: Int? = 1, val subtitle: String? = "", val master_brand: MasterBrand = MasterBrand(), val type: String = "" )

data class ImageDetails (val standard: String = "")

data class MasterBrand (val titles: MasterbrandTitle = MasterbrandTitle())

data class MasterbrandTitle(val small: String = "")