package com.solodev.launchx.route

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {

    @Serializable
    data object Home: Route

    @Serializable
    data class RocketDetail(val id : String):Route

    @Serializable
    data class LandPadsDetail(val id : String):Route

}