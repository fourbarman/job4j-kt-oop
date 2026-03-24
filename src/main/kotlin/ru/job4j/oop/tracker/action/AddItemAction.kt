package ru.job4j.oop.tracker.action

import ru.job4j.oop.tracker.item.Item
import ru.job4j.oop.tracker.tracker.Tracker
import java.util.*

class AddItemAction() : Action {
    override fun name(): String = "Add new item"

    override fun execute(tracker: Tracker): Boolean {
        print("Enter item name: ")
        val name = readln()
        val item = Item(name, UUID.randomUUID())
        tracker.add(item)
        println("New item added: $item")
        return true
    }
}
