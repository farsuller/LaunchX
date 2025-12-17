package com.solodev.launchx.presentation.screen.home

import com.solodev.launchx.domain.model.Crew
import com.solodev.launchx.domain.model.Landpad
import com.solodev.launchx.domain.model.Rocket

data class HomeState(
    val isLoading: Boolean = true,
    val rockets: List<Rocket>? = null,
    val crews: List<Crew>? = null,
    val landpads: List<Landpad>? = null,
    val errorMessage: String? = null,
)
