package com.example.android_lab4.ui.model

import kotlinx.serialization.Serializable

@Serializable
data class FieldContent(
    val fieldType: FieldType,
    val content: String
)