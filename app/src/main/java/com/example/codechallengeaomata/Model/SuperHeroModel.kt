
package com.example.codechallengeaomata.Model

import com.google.gson.annotations.SerializedName

data class SuperHeroModel (

	@SerializedName("name") val name : String,
	@SerializedName("realname") val realname : String,
	@SerializedName("team") val team : String,
	@SerializedName("firstappearance") val firstappearance : Int,
	@SerializedName("createdby") val createdby : String,
	@SerializedName("publisher") val publisher : String,
	@SerializedName("imageurl") val imageurl : String,
	@SerializedName("bio") val bio : String
)