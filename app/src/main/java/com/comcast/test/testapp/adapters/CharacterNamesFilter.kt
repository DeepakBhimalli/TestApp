package com.comcast.test.testapp.adapters

import android.widget.Filter
import com.comcast.test.testapp.model.RelatedTopicsInfo
import com.comcast.test.testapp.utils.Utility

import java.util.ArrayList

class CharacterNamesFilter(private val adapter: CharactersAdapter, private val topicsList: ArrayList<RelatedTopicsInfo>) : Filter() {

    /**
     * Method that initiates characters Search
     * input param: search input string
     */
    override fun performFiltering(searchText: CharSequence?): Filter.FilterResults {
        val filterResults = Filter.FilterResults()

        if (searchText?.length != 0) {
            val filteredCityList = Utility.filterList(searchText.toString().toUpperCase(), topicsList)
            filterResults.count = filteredCityList.size
            filterResults.values = filteredCityList
        } else {
            filterResults.count = topicsList.size
            filterResults.values = topicsList
        }
        return filterResults
    }

    /**
     * Method that initiated the list updates based on the search results
     */
    override fun publishResults(constraint: CharSequence, results: Filter.FilterResults) {
        adapter.updateList(results.values as ArrayList<RelatedTopicsInfo>)
        adapter.notifyDataSetChanged()
    }
}
