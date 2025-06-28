package com.solodev.launchx.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solodev.launchx.data.RequestState
import com.solodev.launchx.domain.usecase.GetRocketUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val rocketUseCase: GetRocketUseCase
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    fun requestApi() {
        getRockets()
    }

    private fun getRockets() = viewModelScope.launch {
        rocketUseCase
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