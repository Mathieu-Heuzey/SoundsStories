package com.soundsstories

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class SoundTemplate(@SerializedName("page") val pages: List<SoundPage>)

@Keep
class SoundPage(
    @SerializedName("color") val color: String,
    @SerializedName("title") val title: String,
    @SerializedName("sounds") val sound: List<Sound>
)

@Keep
class Sound(@SerializedName("icon") val icon: String, @SerializedName("mp3") val mp3: String)