package ru.job4j.oop.tracker.action

import ru.job4j.oop.tracker.tracker.Tracker

interface Action {
    fun name(): String
    fun execute(tracker: Tracker) : Boolean
}
