package com.comcast.test.testapp.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.comcast.test.testapp.R
import com.comcast.test.testapp.model.RelatedTopicsInfo

import java.util.ArrayList

class CharactersAdapter(private val context: Context?, private var characterNames: ArrayList<RelatedTopicsInfo>?) :
    RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>(), Filterable {
    private var listener: OnCharacterNameClickListener ?= null
    private var charNamesFilter: Filter ?= null
    private var charNamesList: ArrayList<RelatedTopicsInfo> = ArrayList()

    interface OnCharacterNameClickListener {
        fun onCharacterNameClickListener(characterData: RelatedTopicsInfo)
    }

    /**
     * CreateView of Recycler view adapter
     */
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CharactersViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.character_row, viewGroup, false)
        return CharactersViewHolder(view)
    }

    /**
     * BindViewHolder of recycler view adapter
     */
    override fun onBindViewHolder(charactersViewHolder: CharactersAdapter.CharactersViewHolder, pos: Int) {
        if (characterNames != null) {
            Log.d("CharactersAdapter", "Position = $pos")
            val topicsInfo = characterNames!![pos]
            val name = topicsInfo.getName()
            charactersViewHolder.characterName?.text = name
            charactersViewHolder.parentLayout?.tag = pos
        } else {
            Toast.makeText(context, R.string.no_characters_available, Toast.LENGTH_LONG).show()
        }
    }

    /**
     * Method that returns the size of the list
     */
    override fun getItemCount(): Int {
        if (characterNames != null) {
            return characterNames!!.size
        }
        return 0
    }

    /**
     * Method that updates the List
     */
    fun updateList(characterNames: ArrayList<RelatedTopicsInfo>) {
        this.characterNames = characterNames
    }

    /**
     * Method that sets the callback listener
     */
    fun setListener(listener: OnCharacterNameClickListener) {
        this.listener = listener
    }

    inner class CharactersViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var characterName: TextView ? = null
        internal var parentLayout: LinearLayout ? = null

        init {
            characterName = itemView.findViewById(R.id.character_name)
            parentLayout = itemView.findViewById(R.id.character_name_parent)

            itemView.setOnClickListener { v ->
                val pos = adapterPosition
                val topic = characterNames!![pos]
                if (listener != null) {
                    listener!!.onCharacterNameClickListener(topic)
                }
            }
        }
    }

    /**
     * Method that initiates the search
     */
    override fun getFilter(): Filter {
        if (charNamesFilter == null) {
            charNamesFilter = characterNames?.let { CharacterNamesFilter(this, it) }
            if (characterNames == null) {
                charNamesFilter = CharacterNamesFilter(this, charNamesList)
            }
        }
        return charNamesFilter as CharacterNamesFilter
    }
}
