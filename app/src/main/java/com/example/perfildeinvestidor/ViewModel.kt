package com.example.perfildeinvestidor

import android.provider.ContactsContract
import androidx.lifecycle.*
import androidx.lifecycle.ViewModel


class ViewModel : ViewModel() {


    private val _name = MutableLiveData<String>()




    private val _listOfQuestionsScore = mutableListOf<Int>()
    val listOfAnswersScore = _listOfQuestionsScore


    init {
        resetInputs()
    }


    fun setName(name: String) {
        _name.value = name
    }

    fun hasNoQuestionChecked(questionIndex: Int, questionScoreValue: Int,){
        _listOfQuestionsScore.add(questionIndex, questionScoreValue)
    }

    fun setQuestionScore(questionIndex: Int, questionScoreValue: Int) {
        _listOfQuestionsScore[questionIndex] = questionScoreValue
    }

    fun returnName(): String {
        return _name.value.toString()
    }

    fun returnScore(): MutableList<Int> {
        return listOfAnswersScore
    }

    fun resetInputs() {
        _listOfQuestionsScore.clear()
        _name.value = ""
    }
    fun resetName() {
        _name.value = ""
    }
    fun removeLastScore(){
        _listOfQuestionsScore.removeLast()
    }



}