package com.tw.training.catkeeper.presenter

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {

    @Mock
    private lateinit var mView: MainContract.View

    @InjectMocks
    private lateinit var presenter: MainPresenter

    @Before
    fun setUp() {
//        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun shouldCallswitchToNearbyCatOfView() {
        presenter.switchToNearbyCat()
        verify(mView).switchToNearbyCat()
    }

    @Test
    fun shouldCallSwitchToMyCatOfView() {
        presenter.switchToMyCat()
        verify(mView).switchToMyCat()
    }

}