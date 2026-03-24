package ru.job4j.oop.tracker

import ru.job4j.oop.tracker.action.AddItemAction
import ru.job4j.oop.tracker.action.ExitAction
import ru.job4j.oop.tracker.action.ShowItemsAction
import ru.job4j.oop.tracker.tracker.Tracker

class StartUI private constructor() {
    companion object {
        private val tracker = Tracker()
        private val actions = listOf(
            AddItemAction(),
            ShowItemsAction(),
            ExitAction()
        )

        fun init() {
            var run = true
            while (run) {
                showMenu()
                val select = readln().toIntOrNull()
                if (select == null || select !in 1..actions.size) {
                    println("Provide number from 1 to ${actions.size}.")
                    continue
                }
                val action = actions[select - 1]
                run = action.execute(tracker)
            }
        }

        private fun showMenu() {
            println()
            println("Menu")
            actions.forEachIndexed() { i, action -> println("${i + 1}. ${action.name()}") }
            print("Provide number: ")
        }
    }
}

fun main(args: Array<String>) {
    StartUI.init()
}
