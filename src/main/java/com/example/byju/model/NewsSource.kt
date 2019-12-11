package com.example.byju.model

import android.os.Parcel
import android.os.Parcelable
import com.example.byju.EMPTY_STRING

data class NewsSource(
    var id: String? = EMPTY_STRING,
    var name: String? = EMPTY_STRING
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsSource> {
        override fun createFromParcel(parcel: Parcel): NewsSource {
            return NewsSource(parcel)
        }

        override fun newArray(size: Int): Array<NewsSource?> {
            return arrayOfNulls(size)
        }
    }
}

