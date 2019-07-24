package com.comcast.test.app.utils

import com.comcast.test.app.model.RelatedTopicsInfo
import org.junit.Test

import org.junit.Assert.*
import java.util.ArrayList

class UtilityTest {

    fun dataInit(): ArrayList<RelatedTopicsInfo> {
        val list = ArrayList<RelatedTopicsInfo>()
        val topicsInfo = RelatedTopicsInfo()
        topicsInfo.text =
                "Apu Nahasapeemapetilon - Apu Nahasapeemapetilon is a fictional character in the animated TV series The Simpsons."
        list.add(topicsInfo)
        val topicsInfo1 = RelatedTopicsInfo()
        topicsInfo1.text =
                "Barney Gumble - Barnard Arnold \"Barney\" Gumble is a fictional character in the American animated sitcom The Simpsons."
        list.add(topicsInfo1)
        return list
    }

    @Test
    fun filterListTest() {
        val topicsInfo = ArrayList<RelatedTopicsInfo>()
        val list = Utility.filterList("", topicsInfo)
        assertTrue(list.isEmpty())
    }

    @Test
    fun filterListTest1() {
        val topicsInfo = dataInit()
        val list = Utility.filterList("", topicsInfo)
        assertEquals(list.size.toLong(), 2)
    }

    @Test
    fun filterListTest2() {
        val topicsInfo = dataInit()
        val list = Utility.filterList("Ap", topicsInfo)
        assertEquals(list.size.toLong(), 1)
    }

    @Test
    fun filterListTest3() {
        val topicsInfo = dataInit()
        val list = Utility.filterList("AB", topicsInfo)
        assertTrue(list.isEmpty())
    }

    @Test
    fun filterListTest4() {
        val topicsInfo = dataInit()
        val list = Utility.filterList("Barn", topicsInfo)
        assertEquals(list.size.toLong(), 1)
    }
}