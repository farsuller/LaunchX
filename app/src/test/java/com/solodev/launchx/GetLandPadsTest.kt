package com.solodev.launchx

import com.solodev.launchx.data.RequestState
import com.solodev.launchx.domain.model.Landpad
import com.solodev.launchx.domain.repository.LaunchXRepository
import com.solodev.launchx.domain.usecase.GetLandPads
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
class GetLandPadsTest {
    private val fakeRepository = mock<LaunchXRepository>()
    private val testUseCase = GetLandPads(fakeRepository)

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `returns success state with crews`() = runTest {
        val fakeData = listOf(Landpad(name = "Land Pad", id = "1"))
        whenever(fakeRepository.getLandPads()).thenReturn(
            flowOf(RequestState.Success(fakeData)),
        )

        val result = testUseCase().first()

        assertTrue(result is RequestState.Success)
        assertEquals("Land Pad", (result as RequestState.Success).result.first().name)
    }
}
