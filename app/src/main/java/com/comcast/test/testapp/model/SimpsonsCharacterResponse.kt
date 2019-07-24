package com.comcast.test.testapp.model

import com.google.gson.annotations.SerializedName

import java.util.ArrayList

class SimpsonsCharacterResponse {

    @SerializedName("Redirect")
    private val redirect: String? = null

    @SerializedName("AnswerType")
    private val answerType: String? = null

    @SerializedName("Image")
    private val image: String? = null

    @SerializedName("meta")
    private val meta: MetaInfo? = null

    @SerializedName("Entity")
    private val entity: String? = null

    @SerializedName("Abstract")
    private val abstractName: String? = null

    @SerializedName("AbstractURL")
    private val abstractURL: String? = null

    @SerializedName("AbstractText")
    private val abstractText: String? = null

    @SerializedName("DefinitionSource")
    private val definitionSource: String? = null

    @SerializedName("Infobox")
    private val infobox: String? = null

    @SerializedName("ImageWidth")
    private val imageWidth: Long = 0

    @SerializedName("ImageHeight")
    private val imageHeight: Long = 0

    @SerializedName("Results")
    private val results: ArrayList<Any>? = null

    @SerializedName("AbstractSource")
    private val abstractSource: String? = null

    @SerializedName("Answer")
    private val answer: String? = null

    @SerializedName("DefinitionURL")
    private val definitionURL: String? = null

    @SerializedName("RelatedTopics")
    var relatedTopics: ArrayList<RelatedTopicsInfo>? = null

    @SerializedName("Type")
    private val type: String? = null

    @SerializedName("Heading")
    private val heading: String? = null

    @SerializedName("ImageIsLogo")
    private val imageIsLogo: Long = 0

    @SerializedName("Definition")
    private val definition: String? = null
}
