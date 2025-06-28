package com.solodev.launchx.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rocket(
    val height: Height? = null,
    val diameter: Diameter? = null,
    val mass: Mass? = null,

    @SerialName("first_stage")
    val firstStage: FirstStage? = null,

    @SerialName("second_stage")
    val secondStage: SecondStage? = null,

    val engines: Engines? = null,

    @SerialName("flickr_images")
    val flickrImages: List<String>? = null,

    val name: String? = null,
    val type: String? = null,

    val active: Boolean? = null,

    val stages: Long? = null,
    val boosters: Long? = null,

    @SerialName("cost_per_launch")
    val costPerLaunch: Long? = null,

    @SerialName("success_rate_pct")
    val successRatePct: Long? = null,

    @SerialName("first_flight")
    val firstFlight: String? = null,

    @SerialName("country")
    val country: String? = null,

    val company: String? = null,

    @SerialName("description")
    val description: String? = null,

    val id: String
)

@Serializable
data class Height(
    val meters : Double? = null,
    val feet : Double? = null
)

@Serializable
data class Diameter(
    val meters : Double? = null,
    val feet : Double? = null
)

@Serializable
data class Mass(
    val kg : Long? = null,
    val lb : Long? = null
)

@Serializable
data class FirstStage(

    @SerialName("thrust_sea_level")
    val thrustSeaLevel : Thrust? = null,

    @SerialName("thrust_vacuum")
    val thrustVacuum : Thrust? = null,

    val reusable : Boolean? = null,

    val engines : Long? = null,

    @SerialName("fuel_amount_tons")
    val fuelAmountTons : Double? = null,

    @SerialName("burn_time_sec")
    val burnTimeSec : Long? = null
)

@Serializable
data class SecondStage(
    val thrust: Thrust? = null,
    val payloads : Payloads? = null,
    val reusable : Boolean? = null,
    val engines : Long?= null,

    @SerialName("fuel_amount_tons")
    val fuelAmountTons : Double? = null,

    @SerialName("burn_time_sec")
    val burnTimeSec : Long? = null
)

@Serializable
data class Thrust(
    val kN : Long? = null,
    val lbf : Long? = null
)

@Serializable
data class Payloads(
    @SerialName("composite_fairing")
    val compositeFairing : CompositeFairing? = null,

    @SerialName("option_1")
    val option1 : String? = null
)

@Serializable
data class CompositeFairing(
    val height : Height,
    val diameter: Diameter
)

@Serializable
data class Engines (
    val isp: Isp? = null,

    @SerialName("thrust_sea_level")
    val thrustSeaLevel: Thrust? = null,

    @SerialName("thrust_vacuum")
    val thrustVacuum: Thrust? = null,

    val number : Long? = null,
    val type : String? = null,
    val version : String? = null,
    val layout : String? = null,

    @SerialName("engine_loss_max")
    val engineLossMax : Long? = null,

    @SerialName("propellant_1")
    val propellant1 : String? = null,

    @SerialName("propellant_2")
    val propellant2 : String? = null,

    @SerialName("thrust_to_weight")
    val thrustToWeight : Double? = null
)

@Serializable
data class Isp(
    @SerialName("sea_level")
    val seaLevel : Long? = null,
    val vacuum : Long? = null
)

