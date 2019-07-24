package com.comcast.test.testapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class IconInfo private constructor(input: Parcel) : Parcelable {

    @SerializedName("Height")
    private var height: String? = null

    @SerializedName("URL")
    internal var url: String? = null

    @SerializedName("Width")
    private var width: String? = null

    init {
        height = input.readString()
        url = input.readString()
        width = input.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(height)
        dest.writeString(url)
        dest.writeString(width)
    }

    companion object CREATOR : Parcelable.Creator<IconInfo> {
        override fun createFromParcel(parcel: Parcel): IconInfo {
            return IconInfo(parcel)
        }

        override fun newArray(size: Int): Array<IconInfo?> {
            return arrayOfNulls(size)
        }
    }
}
