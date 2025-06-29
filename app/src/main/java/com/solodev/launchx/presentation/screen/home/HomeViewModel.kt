package com.solodev.launchx.presentation.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solodev.launchx.connectivity.ConnectivityObserver
import com.solodev.launchx.connectivity.NetworkConnectivityObserver
import com.solodev.launchx.data.RequestState
import com.solodev.launchx.domain.usecase.LaunchXUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val launchXUseCase: LaunchXUseCase,
    private val connectivity: ConnectivityObserver,
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    private val _networkStatus = MutableStateFlow(ConnectivityObserver.Status.Available)
    val networkStatus = _networkStatus.asStateFlow()

    fun requestApi() {
        getRockets()
        getCrews()
        getLandPads()
    }

    fun observeConnectivity() = viewModelScope.launch {
        connectivity.observe().collect { status ->
            _networkStatus.value = status
        }
    }


    private fun getLandPads() = viewModelScope.launch {
        launchXUseCase
            .getLandPads
            .invoke()
            .collectLatest { response ->
                when (response) {
                    is RequestState.Success -> {
                        _homeState.update { it.copy(landpads = response.result) }
                    }
                    is RequestState.Error -> {}
                }
            }
    }

    private fun getCrews() = viewModelScope.launch {
        launchXUseCase
            .getCrews
            .invoke()
            .collectLatest { response ->
                when (response) {
                    is RequestState.Success -> {
                        _homeState.update { it.copy(crews = response.result) }
                    }
                    is RequestState.Error -> {}
                }

            }
    }

    private fun getRockets() = viewModelScope.launch {
        launchXUseCase
            .getRockets
            .invoke()
            .onStart { _homeState.update { it.copy(isLoading = true) } }
            .catch { e ->
                _homeState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message
                    )
                }
            }
            .collectLatest { response ->
                when (response) {
                    is RequestState.Success -> {
                        _homeState.update { it.copy(isLoading = false, rockets = response.result) }
                    }

                    is RequestState.Error -> {
                        _homeState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = response.message
                            )
                        }
                    }
                }
            }
    }
}