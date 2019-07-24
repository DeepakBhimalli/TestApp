package com.comcast.test.testapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class RelatedTopicsInfo : Parcelable {

    @SerializedName("FirstURL")
    var firstURL: String? = null

    @SerializedName("Result")
    var result: String? = null

    @SerializedName("Text")
    var text: String? = null

    @SerializedName("Icon")
    var icon: IconInfo? = null

    internal var name: String? = null

    internal var description: String? = null

    constructor() {}

    private constructor(input: Parcel)  {
        firstURL = input.readString()
        result = input.readString()
        text = input.readString()
        icon = input.readParcelable(IconInfo::class.java.classLoader)
        name = input.readString()
        description = input.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(firstURL)
        dest.writeString(result)
        dest.writeString(text)
        dest.writeParcelable(icon, flags)
        dest.writeString(name)
        dest.writeString(description)
    }

    fun getName(): String? {
        if (text!!.contains("-")) {
            val splitStr = text!!.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            name = splitStr[0]
            description = splitStr[1]
        } else {
            name = text
        }
        return name
    }

    fun getDescription(): String {
        val splitStr = text!!.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        name = splitStr[0]
        description = splitStr[1]
        return description!!.substring(1)
    }

    companion object CREATOR : Parcelable.Creator<RelatedTopicsInfo> {
        override fun createFromParcel(parcel: Parcel): RelatedTopicsInfo {
            return RelatedTopicsInfo(parcel)
        }

        override fun newArray(size: Int): Array<RelatedTopicsInfo?> {
            return arrayOfNulls(size)
        }
    }
}
