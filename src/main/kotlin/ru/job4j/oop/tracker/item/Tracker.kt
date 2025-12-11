package ru.job4j.oop.tracker.item

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class Tracker {
    private val items = mutableListOf<Item>()

    fun add(name: String): Item {
        val item = Item(name = name)
        items.add(item)
        return item
    }

    fun replace(id: Uuid, newName: String): Item? {
        val existing = items.find { it.uuid == id } ?: return null
        existing.name = newName
        return existing
    }

    fun deleteById(id: Uuid) {
        val index = items.indexOfFirst { it.uuid == id }
        if (index == -1) return
        items.removeAt(index)
    }

    fun findAll(): List<Item> =
        items.toList()

    fun findById(id: Uuid): Item? =
        items.find {it.uuid == id}

    fun findByName(name: String): List<Item> =
        items.filter {it.name.contains(name)}
}
