package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;


/**
 * This class will represent the map of cities and roads,
 * and will contain methods to add cities, connect them with roads,
 * and find paths.
 * This class will hold a list of cities
 * and a list of roads.
 * It will also provide methods to
 * add cities,
 * connect them with roads,
 * and a method stub for finding paths
 *
 * */

public class Map {
  private List<City> cities;
  private List<Road> roads;

  public Map() {
    cities = new ArrayList<>();
    roads = new ArrayList<>();
  }

  public void addCity(City city) {
    cities.add(city);
  }

  public void addRoad(City city1, City city2, int cost, int time) {
    roads.add(new Road(city1, city2, cost, time));
  }

  public List<City> getCities() {
    return new ArrayList<>(cities); // Return a copy of the cities list
  }

  // Method stubs for path finding algorithms

  /**
   * This method appears to be an implementation of Dijkstra's algorithm, which is used to find the shortest path between nodes in a graph. We'll use hypothetical `City` and `Road` classes, and assume they have methods like `getCity1`, `getCity2`, and `getTime` to access their properties.
   *
   * 1. **Initialization:**
   *    - A `HashMap<City, Integer> distances` is created to store the shortest known distance from the start city to every other city. Initially, all distances are unknown, so they are set to `Integer.MAX_VALUE`.
   *    - A `HashMap<City, City> previous` is created to store the previous city in the shortest path from the start city. This helps in reconstructing the path once the algorithm is complete.
   *    - A `PriorityQueue<City>` is created to determine which city to process next based on the shortest distance. It uses a comparator that retrieves distances from the `distances` map.
   *
   * 2. **Setting initial values:**
   *    - The method iterates over all cities (assumed to be a collection named `cities` accessible within the method's scope) and initializes their distance to `Integer.MAX_VALUE` and their previous city to `null`.
   *    - The distance from the start city to itself is set to `0`.
   *    - The start city is added to the priority queue.
   *
   * 3. **Processing the cities:**
   *    - The algorithm enters a `while` loop that continues as long as there are cities left to process in the priority queue.
   *    - Inside the loop, the city with the shortest distance (`current`) is removed from the queue.
   *    - If the current city is the destination (`end`), the algorithm can terminate early as the shortest path has been found.
   *
   * 4. **Exploring neighbors:**
   *    - The method iterates over all roads (assumed to be a collection named `roads` accessible within the method's scope).
   *    - For each road, it checks if the current city is one of the cities connected by the road.
   *    - If so, it identifies the neighboring city (`neighbor`) that is on the other end of the road.
   *    - It calculates the new distance to this neighbor as the sum of the current city's shortest distance and the time to travel the road.
   *    - If this new distance is shorter than the previously known distance to the neighbor, it updates the neighbor's distance in the `distances` map and sets the current city as the `previous` city on the path to the neighbor.
   *    - The neighbor is then added to the priority queue to ensure it is processed in subsequent iterations.
   *
   * 5. **Reconstructing the path:**
   *    - After the while loop completes (either by processing all cities or by reaching the destination early), the shortest path can be reconstructed by backtracking from the destination city using the `previous` map.
   *    - This part of the code is not shown in the provided method stub, but typically you would start at the end city and follow the chain of previous cities until you reach the start city, reversing the order to get the path from start to end.
   *
   * **Example:**
   * Imagine you have a map with cities A, B, C, and D, and roads connecting them with different travel times. You want to find the fastest path from A to D.
   *
   * - Initially, distances are set to `Integer.MAX_VALUE` for all cities except A, which is set to `0`.
   * - The priority queue starts with A.
   * - The algorithm processes A, finds its neighbors (say B and C), and updates their distances and previous cities.
   * - B and C are added to the queue.
   * - The queue now contains B and C, with B likely having a shorter distance and thus being processed next.
   * - This process continues until all reachable cities are processed or until D is reached.
   * - Once D is reached or all cities are processed, you can backtrack from D to A using the `previous` map to find the fastest path.
   *
   * This method stub sets up the framework for the algorithm but does not include the actual path reconstruction or the complete implementation details.
   * */

  public List<City> findFastestPath(City start, City end) {
    // TODO: Implement the dynamic programming algorithm for fastest path

    // Initialize a map to hold the shortest distance from start to each city
    HashMap<City, Integer> distances = new HashMap<>();
    // Initialize a map to hold the previous city in the shortest path
    HashMap<City, City> previous = new HashMap<>();
    // Priority queue to select the city with the shortest distance next
    PriorityQueue<City> queue = new PriorityQueue<>(Comparator.comparing(distances::get));

    // Set initial distances to MAX_VALUE and previous cities to null
    for (City city : cities) {
      distances.put(city, Integer.MAX_VALUE);
      previous.put(city, null);
    }
    // Distance from start to itself is 0
    distances.put(start, 0);
    // Add start city to the priority queue
    queue.add(start);

    // While there are cities to process
    while (!queue.isEmpty()) {
      // Get the city with the shortest distance from the queue
      City current = queue.poll();
      // If we reached the destination, we can break early
      if (current.equals(end)) {
        break;
      }

      // Check all roads from the current city
      for (Road road : roads) {
        // If the road is connected to the current city
        if (road.getCity1().equals(current) || road.getCity2().equals(current)) {
          // Get the neighboring city
          City neighbor = road.getCity1().equals(current) ? road.getCity2() : road.getCity1();
          // Calculate new distance to neighbor
          int newDistance = distances.get(current) + road.getTime();
          // If new distance is shorter, update the distance and previous city
          if (newDistance < distances.get(neighbor)) {
            distances.put(neighbor, newDistance);
            previous.put(neighbor, current);
            // Add the neighbor to the queue for further processing
            queue.add(neighbor);
          }
        }
      }
    }

    // Reconstruct the shortest path from end to start using the previous map
    List<City> path = new ArrayList<>();
    for (City at = end; at != null; at = previous.get(at)) {
      path.add(at);
    }
    // Reverse the path to get the correct order from start to end
    Collections.reverse(path);
    return path;
  }

  public List<City> findCheapestPath(City start, City end) {
    // TODO: Implement the dynamic programming algorithm for cheapest path
    HashMap<City, Integer> costs = new HashMap<>();
    HashMap<City, City> previous = new HashMap<>();
    PriorityQueue<City> queue = new PriorityQueue<>(Comparator.comparing(costs::get));

    // Initialize the costs map with infinity and set the cost for the start city to 0
    for (City city : cities) {
      costs.put(city, Integer.MAX_VALUE);
      previous.put(city, null);
    }
    costs.put(start, 0);
    queue.add(start);

    while (!queue.isEmpty()) {
      City current = queue.poll();
      if (current.equals(end)) {
        break;
      }

      // Explore the neighbors of the current city
      for (Road road : roads) {
        if (road.getCity1().equals(current) || road.getCity2().equals(current)) {
          City neighbor = road.getCity1().equals(current) ? road.getCity2() : road.getCity1();
          int newCost = costs.get(current) + road.getCost();
          if (newCost < costs.get(neighbor)) {
            costs.put(neighbor, newCost);
            previous.put(neighbor, current);
            queue.add(neighbor);
          }
        }
      }
    }

    // Reconstruct the path from end to start using the previous map
    List<City> path = new ArrayList<>();
    for (City at = end; at != null; at = previous.get(at)) {
      path.add(at);
    }
    Collections.reverse(path); // Reverse the path to get the correct order from start to end
    return path;
  }

  /**
   * Now, let's implement the `findBestPath` method.
   * As mentioned earlier, we will define "best" as the path that minimizes the sum of time and cost.
   * To do this,
   * we will create a new class called `CityNode` that encapsulates a city along with its total time and cost.
   * We will use this class in our priority queue to order the cities by the sum of time and cost.
   * */

  public List<City> findBestPath(City start, City end) {
    // TODO: Implement the dynamic programming algorithm for best path

    HashMap<City, Integer> distance = new HashMap<>();
    HashMap<City, City> previous = new HashMap<>();
    HashSet<City> visited = new HashSet<>();
    PriorityQueue<City> queue = new PriorityQueue<>(Comparator.comparing(distance::get));

    // Initialize distances and previous maps
    for (City city : cities) {
      distance.put(city, Integer.MAX_VALUE);
      previous.put(city, null);
    }
    distance.put(start, 0);
    queue.add(start);

    while (!queue.isEmpty()) {
      City current = queue.poll();
      if (current.equals(end)) {
        break;
      }
      if (visited.contains(current)) {
        continue;
      }
      visited.add(current);

      for (Road road : roads) {
        if (road.getCity1().equals(current) || road.getCity2().equals(current)) {
          City neighbor = road.getCity1().equals(current) ? road.getCity2() : road.getCity1();
          if (visited.contains(neighbor)) {
            continue;
          }
          int newDistance = distance.get(current) + road.getTime() + road.getCost();
          if (newDistance < distance.get(neighbor)) {
            distance.put(neighbor, newDistance);
            previous.put(neighbor, current);
            queue.add(neighbor);
          }
        }
      }
    }

    // Reconstruct the path
    List<City> path = new ArrayList<>();
    for (City at = end; at != null; at = previous.get(at)) {
      path.add(at);
    }
    Collections.reverse(path);

    // If the path starts with the start city, it's valid; otherwise, return an empty list
    if (!path.isEmpty() && path.get(0).equals(start)) {
      return path;
    } else {
      return new ArrayList<>();
    }
  }
}
