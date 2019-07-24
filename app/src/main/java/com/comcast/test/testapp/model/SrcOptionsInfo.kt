package com.comcast.test.testapp.model

import com.google.gson.annotations.SerializedName

class SrcOptionsInfo {
    @SerializedName("skip_image_name")
    private val skipImageName: Long = 0

    @SerializedName("skip_end")
    private val skipEnd: String? = null

    @SerializedName("language")
    private val language: String? = null

    @SerializedName("source_skip")
    private val sourceSkip: String? = null

    @SerializedName("skip_abstract")
    private val skipAbstract: Long = 0

    @SerializedName("src_info")
    private val srcInfo: String? = null

    @SerializedName("skip_icon")
    private val skipIcon: Long = 0

    @SerializedName("is_wikipedia")
    private val isWikipedia: Long = 0

    @SerializedName("is_fanon")
    private val isFanon: Long = 0

    @SerializedName("is_mediawiki")
    private val isMediawiki: Long = 0

    @SerializedName("skip_abstract_paren")
    private val skipAbstractParen: Long = 0

    @SerializedName("directory")
    private val directory: String? = null

    @SerializedName("min_abstract_length")
    private val minAbstractLength: String? = null

    @SerializedName("skip_qr")
    private val skipQr: String? = null

}
