package com.example.bom_ved

// Объект персонаж
class Character{
    lateinit var stats: String
    lateinit var move: String
    lateinit var typeAttack: String
}

// В интерфейсе обьявляем все шаги которые будут при создании будущего объекта
interface Builder{
    fun setStats(stats:String)
    fun setMove(move:String)
    fun setTypeAttack(attack:String)
}
// Создаем разную реализацию интерфейса и возращаем разные объекты
class CreateCharacter:Builder{
    private var character: Character = Character()

    override fun setStats(stats:String) {
        character.stats = stats
    }

    override fun setMove(move:String) {
        character.move = move
    }

    override fun setTypeAttack(attack:String) {
        character.typeAttack = attack
    }

    fun getResult():Character{
        return character
    }
}

// Создаем разную реализацию интерфейса и возращаем разные объекты
class CreateCharacterInfo:Builder{
    private var characterInfo: MutableList<String> = mutableListOf()

    override fun setStats(stats:String) {
        characterInfo.add("info stats: $stats")
    }

    override fun setMove(move:String) {
        characterInfo.add("info move: $move")
    }

    override fun setTypeAttack(attack:String) {
        if (attack.toInt() >= 20)
            characterInfo.add("IMBA_CHARACTER_DETECTED")
        else{
            characterInfo.add(attack)
        }
    }

    fun getResult():MutableList<String>{
        return characterInfo
    }
}

// Создаем информацию о персонаже, также последовательность создание объекта
class CharacterInformation(){
    fun constructorCharacter(builder: Builder){
        builder.setStats("550hp 200mp")
        builder.setMove("200km")
        builder.setTypeAttack("20")
    }
}

// Создаем билдер
class Application(){
    fun createCharacter(){
        val director = CharacterInformation()
        val builderCh = CreateCharacter()
        val builderChIn = CreateCharacterInfo()

        director.constructorCharacter(builderCh)

        // Создаем объект персонаж, хотя CharacterInformation не знает о методе constructorCharacter

        val character:Character = builderCh.getResult()

        director.constructorCharacter(builderChIn)

        // Создаем объект информацию о персонаже из тех же данных что и персонаж

        val characterInfo:MutableList<String> = builderChIn.getResult()

        // Имея одинаковый набор данных мы получили 2 абсолютно разных объекта, при этом
        // CharacterInformation не знает о методах constructorCharacter и CharacterInformation
        // при необходимости можно создать еще 1 реализацию набора данных и собрать другой объект
        println(character.stats + " " + character.move + " " + character.typeAttack)
        print(characterInfo)
    }
}

fun main(){
    Application().createCharacter()
}