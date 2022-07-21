package com.vinay.catapp.domain.model

/**
 * Created by vinaymishra on 19,July,2022
 */

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weight (

    @SerializedName("imperial" ) var imperial : String? = null,
    @SerializedName("metric"   ) var metric   : String? = null

): Parcelable
