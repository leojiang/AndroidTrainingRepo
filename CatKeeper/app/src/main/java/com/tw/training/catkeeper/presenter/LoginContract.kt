package com.tw.training.catkeeper.presenter

interface LoginContract {
    interface View : BaseView<Presenter>
    interface Presenter : BasePresenter
}