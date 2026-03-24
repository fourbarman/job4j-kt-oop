package ru.job4j.oop.tracker.action

import ru.job4j.oop.tracker.tracker.Tracker

class ShowItemsAction() : Action {
    override fun name(): String = "Show all items"

    override fun execute(tracker: Tracker): Boolean {
        val items = tracker.findAll()
        if (items.isEmpty()) {
            println("No item in storage")
        } else {
            println("Found items:")
            for (item in items) {
                println("Name: ${item.name}, UUID: ${item.uuid}")
            }
        }
        return true
    }
}
