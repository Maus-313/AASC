package com.example.aasc.net

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnnotateImageRequest(val requests: List<RequestItem>)

@JsonClass(generateAdapter = true)
data class RequestItem(
    val image: ImagePayload,
    val features: List<Feature>,
    val imageContext: ImageContext? = null
)

@JsonClass(generateAdapter = true)
data class ImagePayload(val content: String) // base64 JPEG

@JsonClass(generateAdapter = true)
data class Feature(val type: String, val maxResults: Int = 1) // DOCUMENT_TEXT_DETECTION

@JsonClass(generateAdapter = true)
data class ImageContext(val languageHints: List<String>? = null)

@JsonClass(generateAdapter = true)
data class AnnotateImageResponse(@Json(name = "responses") val responses: List<SingleResponse>)

@JsonClass(generateAdapter = true)
data class SingleResponse(@Json(name = "fullTextAnnotation") val fullTextAnnotation: FullTextAnnotation?)

@JsonClass(generateAdapter = true)
data class FullTextAnnotation(val text: String?)
