/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 7/2/19 4:36 PM
 */

package id.ac.polinema.seameo.ecanteen.view.activity

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import java.util.ArrayList

import id.ac.polinema.seameo.ecanteen.R
import id.ac.polinema.seameo.ecanteen.contract.TransactionContract
import id.ac.polinema.seameo.ecanteen.model.TransactionModel
import id.ac.polinema.seameo.ecanteen.presenter.transaction.TransactionPresenter
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), TransactionContract.View {
    private var mPresenter: TransactionPresenter? = null
    private val mListHistory: ArrayList<TransactionModel>? = null

    override fun initPresenter() {
        mPresenter = TransactionPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initPresenter()
        scann_barcode.setOnClickListener {
            startActivity(Intent(this, ScanActivity::class.java)) }
    }
}
