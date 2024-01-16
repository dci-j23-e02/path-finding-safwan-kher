package src;



import java.util.Scanner;



public class Demo   {

  public static void main(String[] args) {
    // Create a new map
    Map map = new Map();

    // Create a Scanner object for user input
    Scanner scanner = new Scanner(System.in);

    // User interface loop
    while (true) {
      System.out.println("Choose an option:");
      System.out.println("1. Add a city");
      System.out.println("2. Add a road");
      System.out.println("3. Find a path");
      System.out.println("4. Exit");
      String option = scanner.nextLine();

      switch (option) {
        case "1":
          // Add a city
          System.out.println("Enter the name of the new city:");
          String cityName = scanner.nextLine();
          map.addCity(new City(cityName));
          System.out.println("City '" + cityName + "' added.");
          break;
        case "2":
          // Add a road
          System.out.println("Enter the name of the first city:");
          String city1Name = scanner.nextLine();
          System.out.println("Enter the name of the second city:");
          String city2Name = scanner.nextLine();
          System.out.println("Enter the cost of the road:");
          int cost = scanner.nextInt();
          System.out.println("Enter the time of the road:");
          int time = scanner.nextInt();
          scanner.nextLine(); // Consume the newline left-over

          City city1 = findCityByName(map, city1Name);
          City city2 = findCityByName(map, city2Name);

          if (city1 != null && city2 != null) {
            map.addRoad(city1, city2, cost, time);
            System.out.println("Road added between '" + city1Name + "' and '" + city2Name + "'.");
          } else {
            System.out.println("One or both cities not found.");
          }
          break;
        case "3":
          // Find a path
          findPath(map, scanner);
          break;
        case "4":
          // Exit
          System.out.println("Exiting program.");
          scanner.close();
          return;
        default:
          System.out.println("Invalid option. Please enter a number between 1 and 4.");
          break;
      }
    }
  }

  private static City findCityByName(Map map, String cityName) {
    return map.getCities().stream()
        .filter(city -> city.getName().equalsIgnoreCase(cityName))
        .findFirst()
        .orElse(null);
  }

  private static void findPath(Map map, Scanner scanner) {
    System.out.println("Enter the name of the start city:");
    String startCityName = scanner.nextLine();
    System.out.println("Enter the name of the end city:");
    String endCityName = scanner.nextLine();

    City startCity = findCityByName(map, startCityName);
    City endCity = findCityByName(map, endCityName);

    if (startCity == null || endCity == null) {
      System.out.println("One or both of the cities entered do not exist on the map.");
      return;
    }

    System.out.println("Choose the pathfinding method: (1) Fastest, (2) Cheapest, (3) Best");
    String method = scanner.nextLine();

    switch (method) {
      case "1":
        printPath(map.findFastestPath(startCity, endCity));
        break;
      case "2":
        printPath(map.findCheapestPath(startCity, endCity));
        break;
      case "3":
        printPath(map.findBestPath(startCity, endCity));
        break;
      default:
        System.out.println("Invalid method choice. Please enter 1, 2, or 3.");
        break;
    }
  }

  private static void printPath(java.util.List<City> path) {
    if (path.isEmpty()) {
      System.out.println("No path found.");
    } else {
      path.forEach(city -> System.out.println(city.getName()));
    }
  }
}
