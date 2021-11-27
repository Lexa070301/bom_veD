package com.example.bom_ved

interface Message{
    fun getMessage(who: People, msg: String)
}


class People(name:String): Message{
    val name = name
    override fun getMessage(who: People, msg: String) {
        println("$name Получил сообщение от ${who.name} сообщение: $msg")
    }


}

class ChatManager(){
    private var subscribePeopleList = mutableListOf<People>()

    fun subscribe(people:People){
        subscribePeopleList.add(people)
    }

    fun subscribe(peopleList: List<People>){
        subscribePeopleList.addAll(peopleList)
    }

    fun notify(who:People, msg:String){
        for (i in 1 .. subscribePeopleList.size){
            if (subscribePeopleList[i-1] != who)
            subscribePeopleList[i-1].getMessage(who, msg)
        }
    }
}

class VoiceChat(){
    private val vasya = People("vasya")
    private val petya = People("petya")
    private val misha = People("misha")
    private val lobster = People("lobster")
    private val nokk3r = People("nokk3r")

    fun onCreate(){
        val chat = ChatManager()
        chat.subscribe(listOf(vasya,petya))
        chat.notify(vasya,"АЛЛО")
        chat.subscribe(listOf(misha,lobster,nokk3r))
        chat.notify(petya,"МЫ ЗАШЛИ ПРИЕМ АЛЛО АЛЛО ПРИЕМ")
        chat.notify(vasya,"ВАС СЛЫШНО ХЕЛЛО")
    }
}

fun main(){
    VoiceChat().onCreate()
}