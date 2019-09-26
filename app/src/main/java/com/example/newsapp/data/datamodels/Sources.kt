package com.example.newsapp.data.datamodels


import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "sources", indices = [Index("id")])
class Sources {

    @SerializedName("id")
    @Expose
    @PrimaryKey
    lateinit var id: String

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("category")
    @Expose
    var category: String? = null


    @SerializedName("language")
    @Expose
    var language: String? = null


    @SerializedName("country")
    @Expose
    var country: String? = null

}


