package com.example.basic_iplayer

data class iPlayerReqObj(val version: String, val schema: String, val categories: Array<Category>) {

    //overrides were created by IDE automatically
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as iPlayerReqObj

        if (version != other.version) return false
        if (schema != other.schema) return false
        if (!categories.contentEquals(other.categories)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = version.hashCode()
        result = 31 * result + schema.hashCode()
        result = 31 * result + categories.contentHashCode()
        return result
    }
}