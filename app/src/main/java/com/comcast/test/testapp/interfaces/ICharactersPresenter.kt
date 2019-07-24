package com.comcast.test.testapp.interfaces

import com.comcast.test.testapp.model.RelatedTopicsInfo

import java.util.ArrayList

interface ICharactersPresenter {

    interface IPresenter {
        fun getCharacterNames(url: String)
    }

    interface IView {
        fun updateView(topics: ArrayList<RelatedTopicsInfo>)
        fun showErrorMessage();
    }
}
