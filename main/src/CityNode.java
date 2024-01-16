package src;




class CityNode {
  City city;
  int time;
  int cost;

  CityNode(City city, int time, int cost) {
    this.city = city;
    this.time = time;
    this.cost = cost;
  }

  int getScore() {
    return time + cost; // The score is the sum of time and cost
  }
}
