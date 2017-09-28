package com.tw.training.catkeeper.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.tw.training.catkeeper.R
import com.tw.training.catkeeper.presenter.MainContract
import com.tw.training.catkeeper.presenter.MainPresenter

class MainActivity : AppCompatActivity(), MainContract.View {

    @BindView(R.id.left_tab) lateinit var leftTab: TextView
    //    lateinit var leftTab: TextView
    @BindView(R.id.right_tab) lateinit var rightTab: TextView
//    lateinit var rightTab: TextView

    var presenter: MainContract.Presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        ButterKnife.bind(this@MainActivity)

        presenter.onStart()

//        leftTab = findViewById(R.id.left_tab)
//        rightTab = findViewById(R.id.right_tab)

//        leftTab.setOnClickListener { onLeftTabClicked() }
//        rightTab.setOnClickListener { OnRightTabClicked() }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onStop()
    }

    @OnClick(R.id.left_tab)
    fun onLeftTabClicked() = presenter.switchToNearbyCat()


    @OnClick(R.id.right_tab)
    fun OnRightTabClicked() = presenter.switchToMyCat()

    override fun switchToNearbyCat() {
        rightTab.isEnabled = true
        leftTab.isEnabled = false
    }

    override fun switchToMyCat() {
        leftTab.isEnabled = true
        rightTab.isEnabled = false
    }
}
