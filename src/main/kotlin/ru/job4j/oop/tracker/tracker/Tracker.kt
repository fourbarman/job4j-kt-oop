package ru.job4j.oop.tracker.tracker

import ru.job4j.oop.tracker.item.Item
import java.util.UUID

class Tracker {
    private val items = mutableListOf<Item>()

    fun add(item: Item): Item {
        items.add(item)
        return item
    }

    fun replace(id: UUID, newItem: Item): Item? {
        val index = items.indexOfFirst { it.uuid == id }
        if (index == -1) return null
        items[index] = newItem
        return newItem
    }

    fun deleteById(id: UUID) {
        val index = items.indexOfFirst { it.uuid == id }
        if (index == -1) return
        items.removeAt(index)
    }

    fun findAll(): List<Item> =
        items.toList()

    fun findById(id: UUID): Item? =
        items.find {it.uuid == id}

    fun findByName(name: String): List<Item> =
        items.filter {it.name.contains(name)}
}
