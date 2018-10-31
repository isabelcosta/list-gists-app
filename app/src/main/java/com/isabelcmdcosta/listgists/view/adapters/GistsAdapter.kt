package com.isabelcmdcosta.listgists.view.adapters

import android.support.annotation.NonNull
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.isabelcmdcosta.listgists.ListGistsApplication
import com.isabelcmdcosta.listgists.R
import com.isabelcmdcosta.listgists.data.models.Gist
import com.isabelcmdcosta.listgists.utils.setTextViewStartingWithBoldSpan
import kotlinx.android.synthetic.main.item_gist.view.*

/**
 * This class represents the adapter that fills in each view of the Gists recyclerView
 * @param gistList list of gists to show
 * @param openDetailFunction function to be called when an item from Gists list is clicked
 */
class GistsAdapter (
        private val gistList: List<Gist>,
        private val openDetailFunction: (gist: Gist) -> Unit
) : RecyclerView.Adapter<GistsAdapter.GistsViewHolder>() {

    private val context = ListGistsApplication.getContext()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistsViewHolder =
            GistsViewHolder(
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.item_gist, parent, false)
            )

    override fun onBindViewHolder(@NonNull holder: GistsViewHolder, position: Int) {
        val item = gistList[position]
        val itemView = holder.itemView

        setTextViewStartingWithBoldSpan(itemView.tvAuthor, context.getString(R.string.author), item.owner.username)
        setTextViewStartingWithBoldSpan(itemView.tvDescription, context.getString(R.string.description), item.description)

        itemView.setOnClickListener { openDetailFunction(item) }
    }

    override fun getItemCount(): Int = gistList.size

    /**
     * This class holds a view for each item of the Gists list
     * @param itemView represents each view of Gists list
     */
    class GistsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
