package com.acsl.jakartacovidwatch.data.datasource.model

import com.google.gson.annotations.SerializedName

class VulnerableDistrictResponse(
    @SerializedName("vulnerable")
    val vulnerableDistricts: List<VulnerableDistrictItem>? = null
)

class VulnerableDistrictItem(
    @SerializedName("kecamatan")
    val subDistrictName : String? = null,
    @SerializedName("kabupaten")
    val districtName : String? = null,
    @SerializedName("lat")
    val lat : Double? = null,
    @SerializedName("lon")
    val lng : Double? = null,
    @SerializedName("kategori")
    val category : String? = null
)