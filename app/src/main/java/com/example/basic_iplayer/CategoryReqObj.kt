package com.example.basic_iplayer

import java.time.Duration

data class CategoryReqObj (val category_highlights: CatHighLights = CatHighLights())

data class CatHighLights (val elements: Array<Element> = arrayOf(Element()))

data class Element (
    val title: String = "",
    val images: ImageDetails = ImageDetails(),
    val count: Int? = 1,
    val subtitle: String = "",
    val synopses: Synopses = Synopses(),
    val release_date: String = "",
    val versions: Array<Version> = arrayOf(Version()),
    val master_brand: MasterBrand = MasterBrand(),
    val type: String = "" )

data class ImageDetails (val standard: String = "")

data class MasterBrand (val titles: MasterbrandTitle = MasterbrandTitle())

data class MasterbrandTitle(val small: String = "")

data class Synopses(val medium: String = "")

data class Version(val duration: DurationObj = DurationObj(), val availability: Availability = Availability())

data class DurationObj(val text:String = "")

data class Availability(val remaining: Remaining = Remaining())

data class Remaining(val text: String = "")