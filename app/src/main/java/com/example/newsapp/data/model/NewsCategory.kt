package com.example.newsapp.data.model

enum class NewsCategory(val type: String) {
    BUSINESS("business"),
    ENTERTAINMENT("entertainment"),
    EVERYTHING("all"),
    GENERAL("general"),
    HEALTH("health"),
    SCIENCE("science"),
    SPORTS("sports"),
    TECHNOLOGY("technology"),
}

enum class Country(val countryCode: String) {
    AUSTRALIA("au"),
    CANADA("ca"),
    FRANCE("fr"),
    GERMANY("de"),
    NORWAY("no"),
    SWEDEN("se"),
    UK("gb"),
    USA("us"),
}
