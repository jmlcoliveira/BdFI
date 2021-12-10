import dataStructures.Dictionary;
import dataStructures.hashTable.SepChainHashTable;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestsHashMap {

    private Dictionary<String, Integer> map;

    // Set up an empty map before each test
    @Before
    public void setUp() {
        this.map = new SepChainHashTable<>();
    }

    // Check that a new HashMap returns 'true' for isEmpty
    @Test
    public void testIsEmptyForNewMap() {
        assertTrue(map.isEmpty());
    }

    // Test adding an element makes isEmpty return 'false'
    @Test
    public void testAddMakesIsEmptyFalse() {
        map.insert("Hello", 5);
        assertFalse(map.isEmpty());
    }

    // Check that size returns 0 for new HashMaps
    @Test
    public void testSizeForNewMap() {
        assertEquals(0, map.size());
    }

    // Test size increases as elements are added
    @Test
    public void testSizeIncrementsWhenAddingElements() {
        map.insert("Hello", 5);
        assertEquals(1, map.size());

        map.insert("Goodbye", 5);
        assertEquals(2, map.size());
    }

    // Make sure get returns the values added under keys
    @Test
    public void testGetReturnsCorrectValue() {
        map.insert("Hello", 5);
        map.insert("Goodbye", 6);
        assert 5 == map.find("Hello");
        assert 6 == map.find("Goodbye");
    }

    // Test that an exception is thrown if a key does not exist
    @Test
    public void testThrowsExceptionIfKeyDoesNotExist() {
        assertNull(map.find("Hello"));
    }

    // Test thats an added element replaces another with the same key
    @Test
    public void testReplacesValueWithSameKey() {
        map.insert("Hello", 5);
        map.insert("Hello", 6);

        assert 6 == map.find("Hello");
    }

    // Make sure that two (non-equal) keys with the same hash do not overwrite each other
    @Test
    public void testDoesNotOverwriteSeparateKeysWithSameHash() {
        map.insert("Ea", 5);
        map.insert("FB", 6);

        assert 5 == map.find("Ea");
        assert 6 == map.find("FB");
    }

    // Make sure size doesn't decrement below 0
    @Test
    public void testRemoveDoesNotEffectNewMap() {
        map.remove("Hello");

        assertEquals(0, map.size());
    }

    // Make sure that size decrements as elements are used
    @Test
    public void testRemoveDecrementsSize() {
        map.insert("Hello", 5);
        map.insert("Goodbye", 6);

        map.remove("Hello");

        assertEquals(1, map.size());

        map.remove("Goodbye");

        assertEquals(0, map.size());
    }

    // Test elements are actually removed when remove is called
    @Test
    public void testRemoveDeletesElement() {
        map.insert("Hello", 5);
        map.remove("Hello");

        assertNull(map.find("Hello"));
    }

    // Test that contains is 'false' for new maps
    @Test
    public void testContainsKeyForNewMap() {
        assertNull(map.find("Hello"));
    }

    // Test that contains returns 'false' when key doesn't exist
    @Test
    public void testContainsKeyForNonExistingKey() {
        map.insert("Hello", 5);
        assertNull(map.find("Goodbye"));
    }

    // Make sure that contains returns 'true' when the key does exist
    @Test
    public void testContainsKeyForExistingKey() {
        map.insert("Hello", 5);
        assertNotNull(map.find("Hello"));
    }

    // Check that contains is not fooled by equivalent hash codes
    @Test
    public void testContainsKeyForKeyWithEquivalentHash() {
        map.insert("Ea", 5);

        assertNull(map.find("FB"));
    }
}
