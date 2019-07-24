package com.comcast.test.testapp.utils

import com.comcast.test.testapp.model.RelatedTopicsInfo
import java.util.ArrayList

object Utility {

    val BUILD_SIMPSONS = "simpsons"
    val BUILD_WIRE = "wire"
    val SIMPSONS_URL = "http://api.duckduckgo.com/?q=simpsons+characters&format=json"
    val WIRE_URL = "http://api.duckduckgo.com/?q=the+wire+characters&format=json"

    /**
     * Method that performs search based on the input
     * input param1: search input string
     * input param2: List of the Characters
     *
     * return: filtered list
     */
    fun filterList(searchText: String, topicsList: ArrayList<RelatedTopicsInfo>): List<RelatedTopicsInfo> {
        if (searchText.isEmpty())
            return topicsList

        val filteredCityList = ArrayList<RelatedTopicsInfo>()
        var isResultFound = false

        for (i in topicsList.indices) {
            val topic = topicsList[i]
            if (topic.text.toString().toUpperCase().contains(searchText.toUpperCase())) {
                filteredCityList.add(topic)
                isResultFound = true
            } else if (isResultFound) {
                break
            }
        }
        return filteredCityList
    }
}
