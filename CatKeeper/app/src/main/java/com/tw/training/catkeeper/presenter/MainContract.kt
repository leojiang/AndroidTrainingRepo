package com.tw.training.catkeeper.presenter

interface MainContract {

    interface View: BaseView<Presenter> {
        fun switchToNearbyCat()
        fun switchToMyCat()
    }

    interface Presenter: BasePresenter {
        fun switchToNearbyCat()
        fun switchToMyCat()
    }
}