package com.comcast.test.testapp.views.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.comcast.test.testapp.R
import com.comcast.test.testapp.model.RelatedTopicsInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_detail_layout.view.*

class CharacterDetailFragment : Fragment() {
    private val TAG = CharacterDetailFragment::class.java.simpleName

    private lateinit var viewLayout: View

    /**
     * onCreateView callback method of a fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewLayout = inflater.inflate(R.layout.character_detail_layout, container, false)
        Log.d(TAG, "onCreateView");

        val bundle = arguments
        if (bundle != null) {
            val topicData = bundle.getParcelable<RelatedTopicsInfo>("CharData")
            if (topicData != null) {
                viewLayout.character_name.text = topicData.name
                viewLayout.character_desc.text = topicData.getDescription()

                val url = topicData.icon?.url
                if (!url.isNullOrEmpty()) {
                    Picasso.get().load(url).into(viewLayout.character_image)
                }
            }
        }
        return viewLayout
    }
}
