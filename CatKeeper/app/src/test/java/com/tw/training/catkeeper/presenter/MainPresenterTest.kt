package com.tw.training.catkeeper.presenter

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {

    @Mock
    private lateinit var mView: MainContract.View

    @InjectMocks
    private lateinit var presenter: MainPresenter

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