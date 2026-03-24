package ru.job4j.oop.tracker

import ru.job4j.oop.tracker.item.Item
import ru.job4j.oop.tracker.tracker.Tracker
import java.util.UUID

class StartUI private constructor() {
    companion object {
        private val tracker = Tracker()

        private var run = true
        fun init() {
            while (run) {
                showMenu()
                when (readln().toIntOrNull()) {
                    1 -> addItem()
                    2 -> showAllItems()
                    3 -> exit()
                    else -> println("Wrong input")
                }
            }
        }

        private fun showMenu() {
            println()
            println("Menu")
            println("1. Add item")
            println("2. Show all items")
            println("3. Exit")
            print("Provide number: ")
        }

        private fun addItem() {
            print("Enter item name: ")
            val name = readln()
            val item = Item(name, UUID.randomUUID())
            tracker.add(item)
            println("New item added: $item")
        }

        private fun showAllItems() {
            val items = tracker.findAll()
            if (items.isEmpty()) {
                println("No item in storage")
            } else {
                println("Found items:")
                for (item in items) {
                    println("Name: ${item.name}, UUID: ${item.uuid}")
                }
            }
        }

        private fun exit() {
            println("Exiting tracker")
            run = false
        }
    }
}

fun main(args: Array<String>) {
    StartUI.init()
}
