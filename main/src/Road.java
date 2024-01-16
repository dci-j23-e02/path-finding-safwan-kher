package src;

/**
 * This class will represent a road connecting two cities,
 * with attributes for cost and time.
 * */

public class Road {
  private City city1;
  private City city2;
  private int cost;
  private int time;

  public Road(City city1, City city2, int cost, int time) {
    this.city1 = city1;
    this.city2 = city2;
    this.cost = cost;
    this.time = time;
  }

  public City getCity1() {
    return city1;
  }

  public City getCity2() {
    return city2;
  }

  public int getCost() {
    return cost;
  }

  public int getTime() {
    return time;
  }
}
