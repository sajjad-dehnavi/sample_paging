package dehnavi.sajjad.paging.model


import com.google.gson.annotations.SerializedName

data class ResponseMovie(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("metadata")
    val metadata: Metadata?
)