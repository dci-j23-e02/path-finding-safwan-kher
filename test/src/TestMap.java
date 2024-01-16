package src;

import java.util.List;
import src.City;
import src.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.City;

import static org.junit.jupiter.api.Assertions.*;

public class TestMap {
  private Map map;
  private City cityA, cityB, cityC, cityD, cityE;

  @BeforeEach
  public void setUp() {
    // Create a new map
    map = new Map();

    // Create cities
    cityA = new City("City A");
    cityB = new City("City B");
    cityC = new City("City C");
    cityD = new City("City D");
    cityE = new City("City E");

    // Add cities to the map
    map.addCity(cityA);
    map.addCity(cityB);
    map.addCity(cityC);
    map.addCity(cityD);
    map.addCity(cityE);

    // Connect cities with roads
    map.addRoad(cityA, cityB, 10, 60); // Cost, Time
    map.addRoad(cityA, cityC, 20, 120);
    map.addRoad(cityB, cityC, 15, 90);
    map.addRoad(cityB, cityD, 50, 30);
    map.addRoad(cityC, cityD, 30, 60);
    map.addRoad(cityC, cityE, 10, 10);
    map.addRoad(cityD, cityE, 20, 20);
  }

  @Test
  public void testFindFastestPath() {
    List<City> fastestPath = map.findFastestPath(cityA, cityE);
    assertNotNull(fastestPath, "Fastest path should not be null");
    assertFalse(fastestPath.isEmpty(), "Fastest path should not be empty");
    assertEquals(cityA, fastestPath.get(0), "Fastest path should start with the start city");
    assertEquals(cityE, fastestPath.get(fastestPath.size() - 1), "Fastest path should end with the end city");
    // Additional checks can be added here
  }

  @Test
  public void testFindCheapestPath() {
    List<City> cheapestPath = map.findCheapestPath(cityA, cityE);
    assertNotNull(cheapestPath, "Cheapest path should not be null");
    assertFalse(cheapestPath.isEmpty(), "Cheapest path should not be empty");
    assertEquals(cityA, cheapestPath.get(0), "Cheapest path should start with the start city");
    assertEquals(cityE, cheapestPath.get(cheapestPath.size() - 1), "Cheapest path should end with the end city");
    // Additional checks can be added here
  }

  @Test
  public void testFindBestPath() {
    List<City> bestPath = map.findBestPath(cityA, cityE);
    assertNotNull(bestPath, "Best path should not be null");
    assertFalse(bestPath.isEmpty(), "Best path should not be empty");
    assertEquals(cityA, bestPath.get(0), "Best path should start with the start city");
    assertEquals(cityE, bestPath.get(bestPath.size() - 1), "Best path should end with the end city");
    // Additional checks can be added here
  }
}
