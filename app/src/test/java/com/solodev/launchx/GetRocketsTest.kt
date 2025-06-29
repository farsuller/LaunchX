package com.solodev.launchx

import com.solodev.launchx.data.RequestState
import com.solodev.launchx.domain.model.Rocket
import com.solodev.launchx.domain.repository.LaunchXRepository
import com.solodev.launchx.domain.usecase.GetRockets
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class GetRocketsTest {

    private val fakeRepository = mock<LaunchXRepository>()
    private val testUseCase = GetRockets(fakeRepository)

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `returns success state with rockets`() = runTest {
        val fakeData = listOf(Rocket(name = "Falcon 9", id = "1"))
        whenever(fakeRepository.getRockets()).thenReturn(
            flowOf(RequestState.Success(fakeData))
        )

        val result = testUseCase().first()

        assertTrue(result is RequestState.Success)
        assertEquals("Falcon 9", (result as RequestState.Success).result.first().name)
    }
}