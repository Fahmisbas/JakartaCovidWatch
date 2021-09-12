package com.acsl.jakartacovidwatch.data.model

data class SymptomModel(
    val imageUrl : String,
    val title : String,
    val content : String,
    val viewType: ViewType = ViewType.SYMPTOM
)