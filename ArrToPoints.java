import java.util.*;

public class ArrToPoints{
  
  private int[][] arr;
  private ArrayList<Point> nextVert = new ArrayList<Point>();
  private ArrayList<Point> nextHor = new ArrayList<Point>();
  
  public ArrToPoints(int[][] arr){
    this.arr = arr;
  }
  
  public void convert(){
    //Finds the Start and adds it to nextVert
    for(int j=0; j < arr[0].length; j++){
      if(arr[0][j] == 1){
        Point p = new Point(j,0,true,false);
        System.out.println("Found the start at: " + p); //DELETE
        nextVert.add(p);
      }
    }
    
    //Runs vertical then horizontal expansion until both arrays are empty
    while(!(nextVert.size() + nextHor.size() == 0)){
      
      //Vertical Expansion
      while(nextVert.size() != 0){
        Point basePoint = nextVert.remove(0);
        int row = basePoint.getY();
        int col = basePoint.getX();
        int initRow = row;
        boolean downExpanding = true;
        boolean upExpanding = true;
        Point last = basePoint;
        System.out.println("Began Vert Expansion for " + basePoint);
        while(downExpanding){
          row++;
          //If it is the end CREATE POINT
          //If it has a 1 on the left or right CREATE POINT
          //If it has a zero below it NO POINT
          //If it is 0 NO POINT
          if(row == (arr.length - 1) && arr[row][col] == 1){
            downExpanding = false;
            if(pointExists(col,row) == false){
              Point p = new Point(col, row,false,true);
              System.out.println("Added point " + p + " from: VERT DOWN EXPANDING END CASE");   //DELETE
              //Add connections and corresponding distances
              last.addConnection(p);
              last.addDistance(Math.abs(p.getY() - last.getY()));
              p.addConnection(last);
              p.addDistance(Math.abs(p.getY() - last.getY()));
            }
            else{
              Point p = retrieveExistingPoint(col, row);
              if(areConnected(p,last) == false){
                System.out.println("Added connection betwwen " + p + " and " + last);     //DELETE
                //Add connections and corresponding   distances
                last.addConnection(p);
                last.addDistance(Math.abs(p.getY() - last.getY()));
                p.addConnection(last);
                p.addDistance(Math.abs(p.getY() - last.getY()));
              }
            }
            
          }
          else if(((col+1 < arr[0].length && arr[row][col+1] == 1) || (col-1 >= 0 && arr[row][col-1] == 1)) && arr[row][col] == 1){
            if(pointExists(col,row) == false){
              Point p = new Point(col, row,false,false);
              System.out.println("Added point " + p + " from: VERT DOWN EXPANDING INTER CASE"); //DELETE
              nextHor.add(p);
            
              //Add connections and corresponding distances
              last.addConnection(p);
              last.addDistance(Math.abs(p.getY() - last.getY()));
              p.addConnection(last);
              p.addDistance(Math.abs(p.getY() - last.getY()));
              //Update last
              last = p;
            }
            else{
              downExpanding = false;
              Point p = retrieveExistingPoint(col, row);
              if(areConnected(p,last) == false){
                System.out.println("Added connection betwwen " + p + " and " + last);   //DELETE
                //Add connections and corresponding distances
                last.addConnection(p);
                last.addDistance(Math.abs(p.getY() - last.getY()));
                p.addConnection(last);
                p.addDistance(Math.abs(p.getY() - last.getY()));
              }
              
            }
            
          }
          else if(row + 1 < arr.length && arr[row+1][col] == 0){
            downExpanding = false;
          }
          else if(arr[row][col] == 0){
            downExpanding = false;
          }
          System.out.println("Reached the end of Vert Down Expansion for " + basePoint);
        }
        row = initRow;
        last = basePoint;
        while(upExpanding){
          row--;
          //If it is the start CREATE POINT
          //If it has a 1 on the left or right CREATE POINT
          //If it has a zero above it NO POINT
          //If it is 0 NO POINT
          if(row == 0 && arr[row][col] == 1){
            upExpanding = false;
            if(pointExists(col,row) == false){
              Point p = new Point(col, row,false,true);
              System.out.println("Added point " + p + " from: VERT UP EXPANDING END CASE");   //DELETE
              //Add connections and corresponding distances
              last.addConnection(p);
              last.addDistance(Math.abs(p.getY() - last.getY()));
              p.addConnection(last);
              p.addDistance(Math.abs(p.getY() - last.getY()));
            }
            else{
              Point p = retrieveExistingPoint(col, row);
              if(areConnected(p,last) == false){
                System.out.println("Added connection betwwen " + p + " and " + last);     //DELETE
                //Add connections and corresponding   distances
                last.addConnection(p);
                last.addDistance(Math.abs(p.getY() - last.getY()));
                p.addConnection(last);
                p.addDistance(Math.abs(p.getY() - last.getY()));
              }
            }
            
          }
          else if(row >= 0 && ((col+1 < arr[0].length && arr[row][col+1] == 1) || (col-1 >= 0 && arr[row][col-1] == 1)) && arr[row][col] == 1){
            if(pointExists(col,row) == false){
              Point p = new Point(col, row,false,false);
              System.out.println("Added point " + p + " from: VERT UP EXPANDING INTER CASE"); //DELETE
              nextHor.add(p);
            
              //Add connections and corresponding distances
              last.addConnection(p);
              last.addDistance(Math.abs(p.getY() - last.getY()));
              p.addConnection(last);
              p.addDistance(Math.abs(p.getY() - last.getY()));
              //Update last
              last = p;
            }
            else{
              upExpanding = false;
              Point p = retrieveExistingPoint(col, row);
              if(areConnected(p,last) == false){
                System.out.println("Added connection betwwen " + p + " and " + last);   //DELETE
                //Add connections and corresponding distances
                last.addConnection(p);
                last.addDistance(Math.abs(p.getY() - last.getY()));
                p.addConnection(last);
                p.addDistance(Math.abs(p.getY() - last.getY()));
              }
              
            }
            
          }
          else if(arr[row][col] == 0){
            upExpanding = false;
          }
          else if((row - 1) >= 0 && arr[row-1][col] == 0){
            upExpanding = false;
          }
          System.out.println("Reached the end of Vert Up Expansion");
        }
        
      }
      //Horizontal Expansion
      while(nextHor.size() != 0){
        Point basePoint = nextHor.remove(0);
        int row = basePoint.getY();
        int col = basePoint.getX();
        int initCol = col;
        boolean leftExpanding = true;
        boolean rightExpanding = true;
        Point last = basePoint;
        System.out.println("Began Hor Expansion for " + basePoint);
        //Left
        while(leftExpanding){
          col--;
          //If it has a one above or below CREATE POINT
          //If it has a zero to the left NO POINT
          //If it is a 0 NO POINT
          if(((row - 1 >= 0 && arr[row-1][col] == 1) || (row + 1 < arr.length && arr[row+1][col] == 1)) && arr[row][col] == 1){
            if(pointExists(col,row) == false){
              Point p = new Point(col, row,false,false);
             System.out.println("Added point " + p + " from: HOR-LEFT EXPANDING INTER CASE"); //DELETE
              nextVert.add(p);
            
              //Add connections and corresponding distances
              last.addConnection(p);
              last.addDistance(Math.abs(p.getX() - last.getX()));
              p.addConnection(last);
              p.addDistance(Math.abs(p.getX() - last.getX()));
              //Update last
              last = p;
            }
            else{
              leftExpanding = false;
              Point p = retrieveExistingPoint(col, row);
              if(areConnected(p,last) == false){
                System.out.println("Added connection betwwen " + p + " and " + last + "LEFT EXP CXN ONLY");   //DELETE
                //Add connections and corresponding distances
                last.addConnection(p);
                last.addDistance(Math.abs(p.getX() - last.getX()));
                p.addConnection(last);
                p.addDistance(Math.abs(p.getX() - last.getX()));
              }
            }
            
          }
          else if(col - 1 >= 0 && arr[row][col-1] == 0){
            leftExpanding = false;
          }
          else if(arr[row][col] == 0){
            leftExpanding = false;
          }
          System.out.println("Reached the end of Left-Hor Expansion");
        }
        System.out.println("Transitioning from Left to Right Hor");
        //Right
        col = initCol;
        last = basePoint;
        while(rightExpanding){
          col++;
          //If it has a one above or below CREATE POINT
          //If it has a zero to the right NO POINT
          //If it is a 0 NO POINT
          if( ((row - 1 >= 0 && arr[row-1][col] == 1) || (row + 1 < arr.length && arr[row+1][col] == 1))){
            if(pointExists(col,row) == false){
              Point p = new Point(col, row,false,false);
              System.out.println("Added point " + p + " from: HOR-RIGHT EXPANDING INTER CASE"); //DELETE
              nextVert.add(p);
            
              //Add connections and corresponding distances
              last.addConnection(p);
              last.addDistance(Math.abs(p.getX() - last.getX()));
              p.addConnection(last);
              p.addDistance(Math.abs(p.getX() - last.getX()));
              //Update last
              last = p;
            }
            else{
              rightExpanding = false;
              Point p = retrieveExistingPoint(col, row);
              if(areConnected(p,last) == false){
                System.out.println("Added connection betwwen " + p + " and " + last + " RIGHT EXP CXN ONLY");   //DELETE
                //Add connections and corresponding distances
                last.addConnection(p);
                last.addDistance(Math.abs(p.getX() - last.getX()));
                p.addConnection(last);
                p.addDistance(Math.abs(p.getX() - last.getX()));
              }
              
            }
            
          }
          else if(col + 1 < arr[0].length && arr[row][col+1] == 0){
            rightExpanding = false;
          }
          else if(arr[row][col] == 0){
            rightExpanding = false;
          }
          System.out.println("Reached the end of Right-Hor Expansion");
        }
      }
    }
  }
  
  public boolean pointExists(int x, int y){
    for(int i=0; i < Point.getIntersections().size(); i++){
      if((Point.getIntersections().get(i).getX() == x) && (Point.getIntersections().get(i).getY() == y)){
        return true;
      }
    }
    return false;
  }
  
  public Point retrieveExistingPoint(int x, int y){
    for(int i=0; i < Point.getIntersections().size(); i++){
      if((Point.getIntersections().get(i).getX() == x) && (Point.getIntersections().get(i).getY() == y)){
        return Point.getIntersections().get(i);
      }
    }
    return null;
  }
  
  public boolean areConnected(Point p, Point last){
    for(int i=0; i<p.getConnections().size(); i++){
      if(p.getConnections().get(i).getX() == last.getX() && p.getConnections().get(i).getY() == last.getY()){
        return true;
      }
    }
    return false;
  }
  
}
