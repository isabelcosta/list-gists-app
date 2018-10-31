package com.isabelcmdcosta.listgists.view.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import com.isabelcmdcosta.listgists.R
import com.isabelcmdcosta.listgists.data.models.Gist
import com.isabelcmdcosta.listgists.view.adapters.GistsAdapter
import com.isabelcmdcosta.listgists.viewmodels.ListGistsViewModel
import kotlinx.android.synthetic.main.activity_list_gists.*

/**
 * This Activity is responsible for listing public gists
 */
class ListGistsActivity : BaseActivity() {

    companion object {
        const val GIST_EXTRA = "GIST_EXTRA"
    }

    private lateinit var listGistsViewModel: ListGistsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_gists)
        supportActionBar?.title = getString(R.string.list_of_gists)

        listGistsViewModel = ViewModelProviders.of(this).get(ListGistsViewModel::class.java)
        observeEvents()

        showProgressDialog(getString(R.string.get_gists_list))
        listGistsViewModel.getGists()
    }

    private fun observeEvents() {
        listGistsViewModel.successful.observe(this, Observer {
            successful ->
            hideProgressDialog()
            if (successful != null) {
                if (successful) {
                    populateListOfGists(listGistsViewModel.gistList)
                } else {
                    Snackbar.make(getRootView(), listGistsViewModel.message, Snackbar.LENGTH_LONG)
                            .show()
                }
            }
        })
    }

    private fun populateListOfGists(gistList: List<Gist>) {
        rvGists.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = GistsAdapter(listGistsViewModel.gistList, openGistDetail)
        }
    }

    private val openGistDetail: (Gist) -> Unit =
        { gist ->
            val intent = Intent(this, ShowGistDetailActivity::class.java)
            intent.putExtra(GIST_EXTRA, gist)
            startActivity(intent)
        }

    override fun onDestroy() {
        super.onDestroy()
        listGistsViewModel.successful.removeObservers(this)
        listGistsViewModel.successful.value = null
    }
}