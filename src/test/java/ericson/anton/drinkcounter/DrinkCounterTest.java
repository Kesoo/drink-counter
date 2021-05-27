package ericson.anton.drinkcounter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DrinkCounterTest {

    private DrinkCounter sut;
    private URL mockDrinksResource;

    @BeforeEach
    void setUp() {
        sut = new DrinkCounter();
        ClassLoader classLoader = this.getClass().getClassLoader();
        mockDrinksResource = classLoader.getResource("mock_drinks_file.txt");
    }

    @Test
    void testCountDrinks() throws Exception{
        File mockDrinksFile = new File(mockDrinksResource.getFile());
        assertTrue(mockDrinksFile.exists());

        Map<String, Integer> actualResponse = sut.countDrinks(mockDrinksFile);
        assertEquals(getExpectedCountedDrinksMap(), actualResponse);
    }

    private Map<String, Integer> getExpectedCountedDrinksMap() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("Holger", 6);
        expected.put("Keso", 6);
        expected.put("Feztis", 6);
        expected.put("Reekan", 6);
        return expected;
    }
}