package com.tw.training.catkeeper.presenter

interface DetailContract {
    interface View: BaseView<Presenter>
    interface Presenter: BasePresenter
}