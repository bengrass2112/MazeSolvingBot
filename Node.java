import java.util.*;

public class Node implements Comparable<Node>{
  private Node prev;
  private String tag;
  private double dist;
  private ArrayList<Edge> edges;
  public static ArrayList<Node> list = new ArrayList<Node>();
  public static PriorityQueue<Node> allNodes = new PriorityQueue<Node>();
  public static ArrayList<String> expanded = new ArrayList<String>();//TRY HERE
  public static ArrayList<Node> solution = new ArrayList<Node>();
  public static int finalDist;
  public Node(String t){
    final Double infinity = Double.POSITIVE_INFINITY;
    prev = null;
    edges = new ArrayList<Edge>();
    tag = t;
    dist = infinity;
    if(t.equals("S")){
      dist = 0;
    }
    allNodes.add(this); //***********************
    list.add(this);
  }
  
  public void addEdge(Edge e){
    edges.add(e);
  }
  
  public Node getPrev(){
    return prev;
  }
  public void setPrev(Node n){
    prev = n;
  }
  public String getTag(){
    return tag;
  }
  public double getDist(){
    return dist;
  }
  public void changeDist(double length){
    dist = length;
  }
  public ArrayList<Edge> getEdges(){
    return edges;
  }
  public static Node getSmallest(){
    Node min = list.get(0);
    for(int i=1; i < list.size(); i++){
      if(list.get(i).getDist() < min.getDist()){
        min = list.get(i);
      }
    }
    return min;
  }
  
  public String toString(){
    return tag;
  }
  public int compareTo(Node n){
    if(dist < n.getDist()){
      return -1;
    }
    else if(dist == n.getDist()){
      return 0;
    }
    else{
      return 1;
    }
  }
  public Point findPointWithTag(){
    for(int i=0; i < Point.getIntersections().size(); i++){
      if(tag.equals(Point.getIntersections().get(i).getTag())){
        return Point.getIntersections().get(i);
      }
    }
    return null;
  }
}
