package com.artbook.viewModel

import com.artbook.repository.FakeArtRepository
import org.junit.Before

class ArtViewModelTest {

    private lateinit var viewModel: ArtViewModel

    @Before
    fun setUp(){
        //TestDoubles(Test Kopyaları)

        viewModel = ArtViewModel(FakeArtRepository())

    }

}