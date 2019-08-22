package data.datamodels

import android.arch.persistence.room.*
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import data.database.utils.NewsAppTypeConverter

@Entity(tableName = "articles")

@TypeConverters(NewsAppTypeConverter::class)
data class Articles(@PrimaryKey(autoGenerate = true) var id: Int?) {

    @ColumnInfo(name = "author") var author:String
    @ColumnInfo(name ="title") var author:String

    @SerializedName("author")
    @Expose
    var author:String





}