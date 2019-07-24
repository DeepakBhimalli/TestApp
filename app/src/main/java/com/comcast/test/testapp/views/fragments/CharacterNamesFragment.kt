package com.comcast.test.testapp.views.fragments

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.comcast.test.testapp.BuildConfig
import com.comcast.test.testapp.R
import com.comcast.test.testapp.adapters.CharactersAdapter
import com.comcast.test.testapp.interfaces.ICharactersPresenter
import com.comcast.test.testapp.model.RelatedTopicsInfo
import com.comcast.test.testapp.presenter.CharactersPresenter
import com.comcast.test.testapp.utils.Utility
import com.comcast.test.testapp.views.activities.BaseActivity
import kotlinx.android.synthetic.main.character_names_layout.view.*
import java.util.*

class CharacterNamesFragment : Fragment(), ICharactersPresenter.IView, CharactersAdapter.OnCharacterNameClickListener,
    SearchView.OnQueryTextListener {

    private val TAG = CharacterNamesFragment::class.java.simpleName

    private lateinit var viewLayout: View
    private lateinit var adapter: CharactersAdapter
    private var twoPane: Boolean = false
    private lateinit var presenter: CharactersPresenter
    private var activity: Activity? = null
    private var isNetworkAvailable: Boolean = false

    /**
     * onCreate method of a fragment
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.activity = getActivity()

        retainInstance = true
        presenter = CharactersPresenter(this)
    }

    /**
     * onCreateView method of a fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewLayout = inflater.inflate(R.layout.character_names_layout, container, false)
        Log.d(TAG, " onCreateView")
        initView()

        activity?.setTitle(R.string.character_names)

        if (savedInstanceState == null) {
            isNetworkAvailable = (activity as BaseActivity).isNetworkConnectionAvailable
            if (isNetworkAvailable) {
                if (BuildConfig.FLAVOR == Utility.BUILD_SIMPSONS)
                    presenter.getCharacterNames(Utility.SIMPSONS_URL)
                else if (BuildConfig.FLAVOR == Utility.BUILD_WIRE)
                    presenter.getCharacterNames(Utility.WIRE_URL)
            }
        }

        if (viewLayout.findViewById<View>(R.id.item_detail_container) != null) {
            twoPane = true
        }
        return viewLayout
    }

    /**
     * onCreateView method of a fragment
     */
    override fun onStart() {
        super.onStart()
        if (!isNetworkAvailable) {
            Toast.makeText(context, context!!.resources.getString(R.string.no_network), Toast.LENGTH_LONG).show()
        }
    }

    /**
     * Method that initializes the view
     */
    private fun initView() {
        viewLayout.search_view.setOnQueryTextListener(this)
        viewLayout.search_view.setOnSearchClickListener {
            viewLayout.search_view.findViewById<ImageView>(android.support.v7.appcompat.R.id.search_mag_icon).visibility = View.INVISIBLE
        }

        val layoutManager = LinearLayoutManager(context)
        viewLayout.recycler_view.layoutManager = layoutManager
        initAdapter()
    }

    /**
     * Method that instantiates the Recycler view adapter
     */
    private fun initAdapter() {
        adapter = CharactersAdapter(context, null)
        adapter.setListener(this)
        viewLayout.recycler_view.adapter = adapter
    }

    /**
     * Method that updates the view after getting the data
     */
    override fun updateView(topics: ArrayList<RelatedTopicsInfo>) {
        Log.d(TAG, " updateView")
        adapter.updateList(topics)
        adapter.notifyDataSetChanged()
    }

    /**
     * Method that handles the click for each item
     */
    override fun onCharacterNameClickListener(characterData: RelatedTopicsInfo) {
        val bundle = Bundle()
        bundle.putParcelable("CharData", characterData)
        val fragmentManager = getActivity()!!.supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val fragment = CharacterDetailFragment()
        fragment.arguments = bundle
        if (twoPane) {
            transaction.replace(R.id.item_detail_container, fragment, BuildConfig.FLAVOR)
        } else {
            transaction.add(R.id.container, fragment, BuildConfig.FLAVOR)
            transaction.addToBackStack("CharactersFragment")
        }
        transaction.commitAllowingStateLoss()
    }

    /**
     * Override the text query submit method for the search view
     */
    override fun onQueryTextSubmit(s: String): Boolean {
        return false
    }

    /**
     * Method that handles the query for the search view
     */
    override fun onQueryTextChange(text: String): Boolean {
        adapter.filter.filter(text)
        return false
    }

    /**
     * Method that shows the error message
     */
    override fun showErrorMessage() {
        Toast.makeText(context, R.string.no_characters_available, Toast.LENGTH_LONG).show()
    }
}
