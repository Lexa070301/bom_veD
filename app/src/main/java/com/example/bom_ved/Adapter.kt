package com.example.bom_ved

// Старый объект со своим конструктором и методами
class OldCharacterInfo(hpPoint: String, mpPoint: String, moveStat: String, typeAttack: String){
    private val hp = hpPoint
    private val mp = mpPoint
    private val move = moveStat
    private val attack = typeAttack

    fun getHpPoint():String{
        return hp
    }
    fun getMpPoint():String{
        return mp
    }
    fun getMove():String{
        return move
    }
    fun getAttack():String{
        return attack
    }
}

// Новый объект со своим конструктором и методами
class NewCharacterInfo(Stats: MutableList<String>){
    private val stat = Stats

    fun getStats():MutableList<String>{
        return stat
    }

    fun hit(){
        print("AXE ATTACKS " + stat[3])
    }
}

// Создаем адаптер для правильной работы старых объектов с новыми объектами
class AdapterOldNewCharacter(oldCharacterInfo: OldCharacterInfo) {
    private val character = oldCharacterInfo

    fun getStats():MutableList<String>{
        val stat = mutableListOf<String>()
        stat.add(character.getHpPoint())
        stat.add(character.getMpPoint())
        stat.add(character.getMove())
        stat.add(character.getAttack())
        return stat
    }
}

fun main(){
    val oldGraves = OldCharacterInfo("200hp","100mp","100KM","20hp")
    // Не можем созать нового героя просто обратившись к старому герою, нам нужно преобразовать данные
    // через адаптер
    // val newGraves = NewCharacterInfo(oldGraves.getStats())

    // Иницилизируем адаптер и преобразовываем данные в MutableList<String>
    val adapter = AdapterOldNewCharacter(oldGraves)

    // Теперь можно собрать нового героя обратившись к данным старого героя и использовать новые методы
    val newGraves = NewCharacterInfo(adapter.getStats())
    println(newGraves.getStats())

    // Не можем реализовать новый метод в старом объекте
    // oldGraves.hit()

    // В новом же объекте можно реализовать метод
    newGraves.hit()
}