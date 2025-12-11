package ru.job4j.oop.tracker.item

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class Item (var name: String, val uuid: Uuid = Uuid.random())
