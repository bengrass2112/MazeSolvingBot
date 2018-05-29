import java.util.*;

public class Point{
  private int x;
  private int y;
  private ArrayList<Point> connections;
  private ArrayList<Integer> distances;
  private boolean isStart;
  private boolean isEnd;
  private String tag;
  
  private static ArrayList<Point> intersections = new ArrayList<Point>();
  
  public Point(int x, int y, boolean s, boolean e){
    this.x = x;
    this.y = y;
    isStart = s;
    isEnd = e;
    connections = new ArrayList<Point>();
    distances = new ArrayList<Integer>();
    
    intersections.add(this);
  }
  
  public int getX(){
    return x;
  }
  public int getY(){
    return y;
  }
  public boolean getIsStart(){
    return isStart;
  }
  public boolean getIsEnd(){
    return isEnd;
  }
  public String getTag(){
    return tag;
  }
  public void setTag(String t){
    tag = t;
  }
  public ArrayList<Point> getConnections(){
    return connections;
  }
  public ArrayList<Integer> getDistances(){
    return distances;
  }
  public void addConnection(Point p){
    connections.add(p);
  }
  public void addDistance(int n){
    distances.add(n);
  }
  public String toString(){
    return "(" + x + "," + y + ")";
  }
  public static ArrayList<Point> getIntersections(){
    return intersections;
  }
  public static void pointsToNodes(){
    //Creates all the Nodes
    for(int i=0; i < intersections.size(); i++){
      if(intersections.get(i).getIsStart() == true){
        Node n = new Node("S");
        intersections.get(i).setTag("S");
      }
      else if(intersections.get(i).getIsEnd() == true){
        Node n = new Node("En");
        intersections.get(i).setTag("En");
      }
      else{
        Node n = new Node("" + i);
        intersections.get(i).setTag("" + i);
      }
    }
    //Creates all the Edges
    //For each point, for each connection that point has...
    for(int i=0; i < intersections.size(); i++){
      for(int j=0; j < intersections.get(i).getConnections().size(); j++){
        //Create edge
        //Tag of edge destination
        Node.list.get(i).addEdge(new Edge(intersections.get(i).getDistances().get(j),findNodeWithTag(intersections.get(i).getConnections().get(j).getTag())));
        
      }
    }
    
  }
  public static Node findNodeWithTag(String t){
    for(int i=0; i<Node.list.size(); i++){
      if(Node.list.get(i).getTag().equals(t)){
        return Node.list.get(i);
      }
    }
    return null;
  }
  
}
