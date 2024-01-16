package src;

import java.util.List;



public class Main {

  public static void main(String[] args) {
    // Create a new map
    Map map = new Map();

    // Create cities
    City cityA = new City("City A");
    City cityB = new City("City B");
    City cityC = new City("City C");
    City cityD = new City("City D");
    City cityE = new City("City E");

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

    // Test the findFastestPath method
    System.out.println("Fastest path from City A to City E:");
    for (City city : map.findFastestPath(cityA, cityE)) {
      System.out.println(city.getName());
    }

    // Test the findCheapestPath method
    System.out.println("\nCheapest path from City A to City E:");
    for (City city : map.findCheapestPath(cityA, cityE)) {
      System.out.println(city.getName());
    }

    // Test the findBestPath method
    System.out.println("Best path from City A to City E:");
    List<City> bestPath = map.findBestPath(cityA, cityE);
    if (bestPath.isEmpty()) {
      System.out.println("No path found.");
    } else {
      for (City city : bestPath) {
        System.out.println(city.getName());
      }

    }
  }
}

