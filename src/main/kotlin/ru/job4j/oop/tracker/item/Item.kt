package ru.job4j.oop.tracker.item

class Item {
    private var id = 0
    private var name = ""

    fun getId(): Int = id

    fun setId(id: Int) {
        this.id = id
    }

    fun getName(): String = name

    fun setName(name: String) {
        this.name = name
    }
}
