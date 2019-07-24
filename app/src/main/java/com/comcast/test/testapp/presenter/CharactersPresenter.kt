package com.comcast.test.testapp.presenter

import android.util.Log
import com.comcast.test.testapp.interfaces.ICharactersPresenter
import com.comcast.test.testapp.model.RelatedTopicsInfo
import com.comcast.test.testapp.restapi.RestApiRequest
import java.util.*

class CharactersPresenter(private val view: ICharactersPresenter.IView) : ICharactersPresenter.IPresenter,
    RestApiRequest.GetDataListener {

    private val TAG = CharactersPresenter::class.java.simpleName

    /**
     * Method that places request for the characters
     */
    override fun getCharacterNames(url: String) {
        val apiRequest = RestApiRequest(this)
        apiRequest.requestCharactersData(url)
    }

    /**
     * Callback method for handling the response from the server
     */
    override fun getDataResponse(response: ArrayList<RelatedTopicsInfo>?) {
        if (response != null) {
            Log.d(TAG, "Received Data response, Updating UI")
            view.updateView(response)
        } else {
            view.showErrorMessage();
        }
    }
}
