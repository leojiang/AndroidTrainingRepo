package com.tw.training.catkeeper.presenter

/**
 * Created by yhjiang on 28/09/2017.
 */
class MainPresenter(val view: MainContract.View) : MainContract.Presenter {
    override fun onStart() {
    }

    override fun onStop() {
    }

    override fun switchToNearbyCat() {
        view.switchToNearbyCat()
    }

    override fun switchToMyCat() = view.switchToMyCat()
}