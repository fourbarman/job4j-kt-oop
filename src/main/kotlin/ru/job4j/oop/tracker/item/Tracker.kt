package ru.job4j.oop.tracker.item

class Tracker {
    private val items = mutableListOf<Item>()
    private var index = 1

    fun add(item: Item): Item {
        item.id = index++
        items.add(item)
        return item
    }

    fun replace(id: Int, item: Item): Item? {
        val index = items.indexOfFirst { it.id == id }
        if (index == -1) {
            return null
        }

        val updated = items[index]
        updated.name = item.name

        return updated
    }

    fun deleteById(id: Int): Item? {
        val index = items.indexOfFirst { it.id == id }
        if (index == -1) {
            return null
        }

        return items.removeAt(index)
    }

    fun findAll(): List<Item> {
        return items.toList()
    }

    fun findById(id: Int): Item? {
        return items.find {it.id == id}
    }

    fun findByName(name: String): List<Item> {
        return items.filter {it.name.contains(name)}
    }
}
