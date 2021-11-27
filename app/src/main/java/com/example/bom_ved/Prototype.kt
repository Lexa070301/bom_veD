package com.example.bom_ved

interface Builder{
    fun setStats(stats:String)
    fun setMove(move:String)
    fun setTypeAttack(attack:String)
}

class CreateCharacter:Builder{
    private var character: String = ""

    override fun setStats(stats:String) {
        character += stats
    }

    override fun setMove(move:String) {
        character += move
    }

    override fun setTypeAttack(attack:String) {
        character += attack
    }

    fun getResult():String{
        return character
    }
}

class CreateCharacterInfo:Builder{
    private var characterInfo: MutableList<String> = mutableListOf()

    override fun setStats(stats:String) {
        characterInfo.add("info about: $stats")
    }

    override fun setMove(move:String) {
        characterInfo.add("info about: $move")
    }

    override fun setTypeAttack(attack:String) {
        characterInfo.add("info about: $attack")
    }

    fun getResult():MutableList<String>{
        return characterInfo
    }
}

class Director(){
    fun constructorCharacter(builder: Builder){
        builder.setStats("550hp 200mp")
        builder.setMove("200km")
        builder.setTypeAttack("20hp")
    }
}

class Application(){
    fun createCharacter(){
        val director = Director()
        val builderCh = CreateCharacter()
        val builderChIn = CreateCharacterInfo()
        director.constructorCharacter(builderCh)

        val character:String = builderCh.getResult()

        director.constructorCharacter(builderChIn)

        val characterInfo:MutableList<String> = builderChIn.getResult()
        println(character)
        print(characterInfo)
    }
}

fun main(){
    Application().createCharacter()
}