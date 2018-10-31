package com.isabelcmdcosta.listgists.view.activities

import android.os.Bundle
import android.view.MenuItem
import com.isabelcmdcosta.listgists.R
import com.isabelcmdcosta.listgists.data.models.Gist
import com.isabelcmdcosta.listgists.utils.setTextViewStartingWithBoldSpan
import com.isabelcmdcosta.listgists.view.activities.ListGistsActivity.Companion.GIST_EXTRA
import kotlinx.android.synthetic.main.activity_show_gist_detail.*

/**
 * This Activity shows a Gist in detail
 */
class ShowGistDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_gist_detail)

        supportActionBar?.title = getString(R.string.gist_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val gist = intent.getParcelableExtra(GIST_EXTRA) as Gist

        populateView(gist)
    }

    private fun populateView(gist: Gist) {
        setTextViewStartingWithBoldSpan(tvAuthor, getString(R.string.author), gist.owner.username)
        setTextViewStartingWithBoldSpan(tvDescription, getString(R.string.description), gist.description)
        setTextViewStartingWithBoldSpan(tvCreatedAt, getString(R.string.created_at), gist.createdAt)
        setTextViewStartingWithBoldSpan(tvUpdatedAt, getString(R.string.updated_at), gist.updatedAt)
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(menuItem)
    }
}
