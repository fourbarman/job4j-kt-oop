package ru.job4j.oop.tracker.action

import ru.job4j.oop.tracker.tracker.Tracker

class ExitAction() : Action {
    override fun name(): String = "Exit"

    override fun execute(tracker: Tracker): Boolean {
        println("Exiting tracker")
        return false
    }
}
