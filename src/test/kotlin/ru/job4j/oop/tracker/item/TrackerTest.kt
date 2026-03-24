package ru.job4j.oop.tracker.item

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.job4j.oop.tracker.tracker.Tracker
import java.util.*

class TrackerTest {
    private lateinit var tracker: Tracker

    @BeforeEach
    fun init() {
        tracker = Tracker()
    }

    @Test
    fun whenAddNewItemThenReturnItemWithIndexOne() {
        val fixedUuid = UUID.fromString("00000000-0000-0000-0000-000000000001")
        val item = Item("New item", fixedUuid)

        tracker.add(item)

        val addedItem = tracker.findAll()[0]

        //assertThat(addedItem).isNotNull()
        assertThat(addedItem).isEqualTo(item)
    }

    @Test
    fun whenAddTwoNewItemsThenReturnEach() {
        val fixedUuid1 = UUID.fromString("00000000-0000-0000-0000-000000000001")
        val fixedUuid2 = UUID.fromString("00000000-0000-0000-0000-000000000002")
        val item1 = Item("New item", fixedUuid1)
        val item2 = Item("New item 2", fixedUuid2)
        tracker.add(item1)
        tracker.add(item2)

        assertThat(tracker.findAll()).containsExactlyInAnyOrder(item1, item2)
    }

    @Test
    fun whenFindByIdAndTrackerHasItemThenReturnFoundItem() {
        val fixedUuid = UUID.fromString("00000000-0000-0000-0000-000000000001")
        val item = Item("New item", fixedUuid)
        tracker.add(item)

        val foundItem = tracker.findById(fixedUuid)

        assertThat(foundItem).isEqualTo(item)
    }

    @Test
    fun whenFindByIdAndTrackerDoesntHaveItemThenReturnNull() {
        val fixedUuid = UUID.fromString("00000000-0000-0000-0000-000000000001")

        assertThat(tracker.findById(fixedUuid)).isNull()
    }

    @Test
    fun whenDeleteByIdItemAndTrackerHaveItemItsNotInStorage() {
        val fixedUuid = UUID.fromString("00000000-0000-0000-0000-000000000001")
        val item = Item("New item", fixedUuid)
        tracker.add(item)

        tracker.deleteById(fixedUuid)

        assertThat(tracker.findById(fixedUuid)).isNull()
    }

    @Test
    fun whenDeleteByIdItemAndTrackerDoesntHaveItemThenReturnNull() {
        val fixedUuid = UUID.fromString("00000000-0000-0000-0000-000000000001")
        val deleteUuid = UUID.fromString("00000000-0000-0000-0000-000000000002")
        val item = Item("New item", fixedUuid)
        tracker.add(item)

        val before = tracker.findAll()

        tracker.deleteById(deleteUuid)

        val after = tracker.findAll()

        assertThat(after).containsExactlyElementsOf(before)
    }

    @Test
    fun whenFindAllThenReturnListOfItems() {
        val fixedUuid1 = UUID.fromString("00000000-0000-0000-0000-000000000001")
        val fixedUuid2 = UUID.fromString("00000000-0000-0000-0000-000000000002")
        val item1 = Item("New item 1", fixedUuid1)
        val item2 = Item("New item 2", fixedUuid2)
        tracker.add(item1)
        tracker.add(item2)

        val items = tracker.findAll()

        assertThat(items).containsExactlyInAnyOrder(item1, item2)
    }

    @Test
    fun whenFindAllAndTrackerEmptyThenReturnEmptyList() {
        assertThat(tracker.findAll()).isEmpty()
    }

    @Test
    fun whenFindByNameAndTrackerHaveItemThenReturnListOfFoundItem() {
        val fixedUuid = UUID.fromString("00000000-0000-0000-0000-000000000001")
        val item = Item("New item", fixedUuid)
        tracker.add(item)

        val found = tracker.findByName("New item")

        assertThat(found).containsExactlyInAnyOrder(item)
    }

    @Test
    fun whenFindByNameAndTrackerHaveTwoItemsThenReturnListOfFoundItems() {
        val fixedUuid1 = UUID.fromString("00000000-0000-0000-0000-000000000001")
        val fixedUuid2 = UUID.fromString("00000000-0000-0000-0000-000000000002")
        val item1 = Item("New item 1", fixedUuid1)
        val item2 = Item("New item 2", fixedUuid2)

        tracker.add(item1)
        tracker.add(item2)

        val foundList = tracker.findByName("New item")

        assertThat(foundList).containsExactlyInAnyOrder(item1, item2)
    }

    @Test
    fun whenFindByNameAndTrackerHaveTwoItemsThenReturnListOfFoundItem() {
        val fixedUuid1 = UUID.fromString("00000000-0000-0000-0000-000000000001")
        val fixedUuid2 = UUID.fromString("00000000-0000-0000-0000-000000000002")
        val item1 = Item("New item 1", fixedUuid1)
        val item2 = Item("New item 2", fixedUuid2)

        tracker.add(item1)
        tracker.add(item2)

        val foundList = tracker.findByName("New item 1")

        assertThat(foundList).containsExactlyInAnyOrder(item1)
    }

    @Test
    fun whenFindByNameAndTrackerDoesntHaveItemThenReturnEmptyList() {
        assertThat(tracker.findByName("New item")).isEmpty()
    }

    @Test
    fun whenReplaceItemThenReturnReplaced() {
        val fixedUuid = UUID.fromString("00000000-0000-0000-0000-000000000001")
        tracker.add(Item("New item", fixedUuid))

        val updateItem = Item("Replaced item", fixedUuid)
        val replaced = tracker.replace(fixedUuid, updateItem)

        assertThat(replaced).isEqualTo(updateItem)
    }

    @Test
    fun whenReplaceItemAndTrackerDoesntHaveItemThenReturnNull() {
        val fixedUuid = UUID.fromString("00000000-0000-0000-0000-000000000001")
        val item = Item("New item", fixedUuid)
        assertThat(tracker.replace(fixedUuid, item)).isNull()
    }
}
