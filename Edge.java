public class Edge{
  private double weight;
  private Node destination;
  
  public Edge(double w, Node dest){
    weight = w;
    destination = dest;
  }
  
  public double getWeight(){
    return weight;
  }
  public Node getDestination(){
    return destination;
  }
  
  public String toString(){
    return "An edge going to node " + destination.getTag() + " with a weight of " + weight;
  }
}
