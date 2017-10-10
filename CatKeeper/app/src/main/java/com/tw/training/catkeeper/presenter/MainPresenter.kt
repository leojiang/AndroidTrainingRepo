package com.tw.training.catkeeper.presenter

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