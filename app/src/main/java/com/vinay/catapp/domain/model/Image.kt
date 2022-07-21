package com.vinay.catapp.domain.model

/**
 * Created by vinaymishra on 19,July,2022
 */
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image (

    @SerializedName("id"     ) var id     : String? = null,
    @SerializedName("width"  ) var width  : Int?    = null,
    @SerializedName("height" ) var height : Int?    = null,
    @SerializedName("url"    ) var url    : String? = null

): Parcelable
