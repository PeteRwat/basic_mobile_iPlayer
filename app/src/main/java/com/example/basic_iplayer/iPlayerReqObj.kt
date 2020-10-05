package com.example.basic_iplayer

data class iPlayerReqObj(val version: String ="", val schema: String = "", val categories: Array<Category> = arrayOf(Category()))

data class Category(val id: String = "", val title: String = "", val type: String = "", val kind: String = "")