package com.example.byju.model

import android.os.Parcel
import android.os.Parcelable
import com.example.byju.EMPTY_STRING


data class Articles(
    var author: String? = EMPTY_STRING,
    var title: String? = EMPTY_STRING,
    var description: String? = EMPTY_STRING,
    var url: String? = EMPTY_STRING,
    var urlToImage: String? = EMPTY_STRING,
    var publishedAt: String? = EMPTY_STRING,
    var content: String? = EMPTY_STRING,
    var source: NewsSource? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(NewsSource::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(url)
        parcel.writeString(urlToImage)
        parcel.writeString(publishedAt)
        parcel.writeString(content)
        parcel.writeParcelable(source, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Articles> {
        override fun createFromParcel(parcel: Parcel): Articles {
            return Articles(parcel)
        }

        override fun newArray(size: Int): Array<Articles?> {
            return arrayOfNulls(size)
        }
    }
}
