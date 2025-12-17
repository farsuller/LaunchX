package com.solodev.launchx

import com.solodev.launchx.connectivity.ConnectivityObserver
import com.solodev.launchx.data.RequestState
import com.solodev.launchx.domain.model.Rocket
import com.solodev.launchx.domain.usecase.GetCrews
import com.solodev.launchx.domain.usecase.GetLandPads
import com.solodev.launchx.domain.usecase.GetRockets
import com.solodev.launchx.domain.usecase.LaunchXUseCase
import com.solodev.launchx.presentation.screen.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class HomeViewModelTest {
    private val getRockets = mock<GetRockets>()
    private val getCrews = mock<GetCrews>()
    private val getLandPads = mock<GetLandPads>()
    private val connectivityObserver = mock<ConnectivityObserver>()

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() = runBlocking {
        Dispatchers.setMain(UnconfinedTestDispatcher())

        whenever(getRockets()).thenReturn(
            flowOf(RequestState.Success(listOf(Rocket(name = "Falcon Heavy", id = "2")))),
        )

        whenever(getCrews()).thenReturn(
            flowOf(RequestState.Success(emptyList())),
        )

        whenever(getLandPads()).thenReturn(
            flowOf(RequestState.Success(emptyList())),
        )

        viewModel =
            HomeViewModel(
                launchXUseCase = LaunchXUseCase(getRockets, getCrews, getLandPads),
                connectivity = connectivityObserver,
            )
    }

    @Test
    fun `updates rockets state from API`() = runTest {
        viewModel.requestApi()
        val state = viewModel.homeState.value
        assertFalse(state.isLoading)
        assertEquals("Falcon Heavy", state.rockets?.first()?.name)
    }
}
