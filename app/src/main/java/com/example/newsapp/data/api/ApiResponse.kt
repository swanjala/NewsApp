package com.example.newsapp.data.api

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import android.util.ArrayMap
import android.util.Log
import retrofit2.Response
import java.io.IOException
import java.util.*
import java.util.regex.Pattern

class ApiResponse <R>{

    var code:Int? = null
    var body:R ? = null
    var errorMessage:String? = null
    var links:MutableMap<String, String>

    constructor(error: Throwable) {
        this.code = 500
        this.body = null
        this.errorMessage = error.message
        this.links = Collections.emptyMap()
    }
    @SuppressLint("NewApi")
    constructor(response: Response<R>){
        code = response.code()
        if (response.isSuccessful) {
            body= response.body()
            errorMessage = null
        } else {
            var message:String ? = null
            if(response.errorBody() != null ){
                try {
                    message = response.errorBody()!!.string()
                }catch (ignored:IOException){
                    Log.e("ignored","error while parsing reponse")
                }
            }
            if(message == null || message.trim { it <= ' ' }.length == 0){
                message = response.message()
            }
            errorMessage = message
            body = null
        }
        val linkHeader = response.headers().get("link")
        if(linkHeader == null){
            links = Collections.emptyMap()
        } else {
            links = ArrayMap()
            val matcher = LINK_PATTERN.matcher(linkHeader)

            while(matcher.find()){
                val count = matcher.groupCount()
                if(count == 2){
                    links[matcher.group(2)] = matcher.group(1)
                }
            }
        }
    }
    companion object{
        private val LINK_PATTERN =
                Pattern.compile("<([^>]*)>[\\s]*;[\\s]*rel=\"([a-zA-Z0-9]+)\"")
        private val PAGE_PATTERN = Pattern.compile("\\bpage=(\\d+)")
        private val NEXT_LINK = "next"
    }





}