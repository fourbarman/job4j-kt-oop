package ru.job4j.oop.tracker.item

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TrackerTest {
    private lateinit var tracker: Tracker

    @BeforeEach
    fun initTracker() {
        tracker = Tracker()
    }

    @Test
    fun whenAddNewItemThenReturnItemWithIndexOne() {
        val item = Item()
        item.setName("New item")

        val addedItem = tracker.add(item)

        assertThat(addedItem.getId()).isNotNull()
        assertThat(addedItem.getName()).isEqualTo("New item")
    }

    @Test
    fun whenAddTwoNewItemsThenReturnEach() {
        val item1 = Item()
        item1.setName("New item 1")
        val item2 = Item()
        item2.setName("New item 2")

        val added1 = tracker.add(item1)
        val added2 = tracker.add(item2)

        assertThat(added1.getId()).isNotNull()
        assertThat(added2.getId()).isNotNull()
        assertThat(added1.getName()).isEqualTo("New item 1")
        assertThat(added2.getName()).isEqualTo("New item 2")
    }

    @Test
    fun whenFindByIdAndTrackerHasItemThenReturnFoundItem() {
        val item = Item()
        item.setName("New item")
        val addedItem = tracker.add(item)

        val foundItem = tracker.findById(addedItem.getId())

        assertThat(foundItem).isNotNull
        assertThat(foundItem?.getId()).isEqualTo(addedItem.getId())
        assertThat(addedItem.getName()).isEqualTo("New item")
    }

    @Test
    fun whenFindByIdAndTrackerDoesntHaveItemThenReturnNull() {
        assertThat(tracker.findById(1)).isNull()
    }

    @Test
    fun whenDeleteByIdItemAndTrackerHaveItemThenReturnDeletedAndItsNotInStorage() {
        val item = Item()
        item.setName("New item")
        val added = tracker.add(item)

        val deleted = tracker.deleteById(added.getId())

        assertThat(deleted?.getId()).isEqualTo(added.getId())
        assertThat(tracker.findById(added.getId())).isNull()
    }

    @Test
    fun whenDeleteByIdItemAndTrackerDoesntHaveItemThenReturnNull() {
        assertThat(tracker.deleteById(1)).isNull()
    }

    @Test
    fun whenFindAllThenReturnListOfItems() {
        val item1 = Item()
        item1.setName("New item 2")
        val added1 = tracker.add(item1)
        val item2 = Item()
        item2.setName("New item 2")
        val added2 = tracker.add(item2)

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
        val item = Item()
        item.setName("New item")
        val added = tracker.add(item)

        val found = tracker.findByName("New item")

        assertThat(found.size).isEqualTo(1)
        assertThat(found[0].getId()).isEqualTo(added.getId())
        assertThat(found[0].getName()).isEqualTo(item.getName())
    }

    @Test
    fun whenFindByNameAndTrackerHaveTwoItemsThenReturnListOfFoundItems() {
        val item1 = Item()
        item1.setName("New item 1")
        val added1 = tracker.add(item1)
        val item2 = Item()
        item2.setName("New item 2")
        val added2 = tracker.add(item2)

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
        val item = Item()
        item.setName("New item")
        val added = tracker.add(item)

        val new = Item()
        item.setName("Replaced item")

        val replaced = tracker.replace(added.getId(), new)

        assertThat(replaced?.getId()).isEqualTo(added.getId())
        assertThat(replaced?.getName()).isEqualTo(new.getName())
    }

    @Test
    fun whenReplaceItemAndTrackerDoesntHaveItemThenReturnNull() {

        assertThat(tracker.replace(1, Item())).isNull()
    }
}
