package ru.job4j.oop.tracker.item

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class TrackerTest {
    private lateinit var tracker: Tracker

    @BeforeEach
    fun initTracker() {
        tracker = Tracker()
    }

    @Test
    fun whenAddNewItemThenReturnItemWithIndexOne() {
        tracker.add("New item")
        val addedItem = tracker.findAll()[0]

        assertThat(addedItem).isNotNull()
        assertThat(addedItem.name).isEqualTo("New item")
    }

    @Test
    fun whenAddTwoNewItemsThenReturnEach() {
        tracker.add("New item 1")
        tracker.add("New item 2")

        val added1 = tracker.findAll()[0]
        val added2 = tracker.findAll()[1]

        assertThat(added1).isNotNull()
        assertThat(added2).isNotNull()
        assertThat(added1.name).isEqualTo("New item 1")
        assertThat(added2.name).isEqualTo("New item 2")
    }

    @Test
    fun whenFindByIdAndTrackerHasItemThenReturnFoundItem() {
        val addedItem = tracker.add("New item")

        val foundItem = tracker.findById(addedItem.uuid)

        assertThat(foundItem).isNotNull
        assertThat(foundItem?.uuid).isEqualTo(addedItem.uuid)
        assertThat(foundItem?.name).isEqualTo(addedItem.name)
    }

    @Test
    fun whenFindByIdAndTrackerDoesntHaveItemThenReturnNull() {
        assertThat(tracker.findById(Uuid.random())).isNull()
    }

    @Test
    fun whenDeleteByIdItemAndTrackerHaveItemItsNotInStorage() {
        val added = tracker.add("New item")

        tracker.deleteById(added.uuid)

        assertThat(tracker.findById(added.uuid)).isNull()
    }

    @Test
    fun whenDeleteByIdItemAndTrackerDoesntHaveItemThenReturnNull() {
        val added = tracker.add("New item")
        val before = tracker.findAll()

        tracker.deleteById(Uuid.random())

        val after = tracker.findAll()

        assertThat(after).containsExactlyElementsOf(before)
        assertThat(after).contains(added)
    }

    @Test
    fun whenFindAllThenReturnListOfItems() {
        val added1 = tracker.add("New item 1")
        val added2 = tracker.add("New item 2")

        val items = tracker.findAll()

        assertThat(items).hasSize(2)
        assertThat(items).contains(added1, added2)
    }

    @Test
    fun whenFindAllAndTrackerEmptyThenReturnEmptyList() {
        assertThat(tracker.findAll()).isEmpty()
    }

    @Test
    fun whenFindByNameAndTrackerHaveItemThenReturnListOfFoundItem() {
        val added = tracker.add("New item")

        val found = tracker.findByName("New item")

        assertThat(found.size).isEqualTo(1)
        assertThat(found[0].uuid).isEqualTo(added.uuid)
        assertThat(found[0].name).isEqualTo(added.name)
    }

    @Test
    fun whenFindByNameAndTrackerHaveTwoItemsThenReturnListOfFoundItems() {
        val added1 = tracker.add("New item 1")
        val added2 = tracker.add("New item 2")

        val foundList = tracker.findByName("New item")

        assertThat(foundList.size).isEqualTo(2)
        assertThat(foundList).contains(added1, added2)
    }

    @Test
    fun whenFindByNameAndTrackerDoesntHaveItemThenReturnEmptyList() {
        assertThat(tracker.findByName("New item")).isEmpty()
    }

    @Test
    fun whenReplaceItemThenReturnReplaced() {
        val added = tracker.add("New item")
        val replaced = tracker.replace(added.uuid, "Replaced item")

        assertThat(replaced?.uuid).isEqualTo(added.uuid)
        assertThat(replaced?.name).isEqualTo(added.name)
    }

    @Test
    fun whenReplaceItemAndTrackerDoesntHaveItemThenReturnNull() {
        assertThat(tracker.replace(Uuid.random(), "Replaced item")).isNull()
    }
}
