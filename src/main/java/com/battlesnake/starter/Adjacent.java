package com.battlesnake.starter;

import com.battlesnake.starter.Point;
import com.battlesnake.map.MapData;
import com.battlesnake.enums.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;


public class Adjacent {

  protected MapData map;
  private Tile[] tile = new Tile[4];


  public Adjacent(MapData map) {
    this.map = map;

    Point head = map.getHead();

    // Set all adjacent tiles pont value and enum value
    tile[0] = new Tile(new Point(head.getX() + 1, head.getY()));	// up
    tile[1] = new Tile(new Point(head.getX(), head.getY() + 1));	// right
    tile[2] = new Tile(new Point(head.getX() - 1, head.getY()));	// down
    tile[3] = new Tile(new Point(head.getX(), head.getY() - 1));	// left
  }

  private class Tile {

    private Point point;
    private GameBoardObjects value;
    private boolean deadTile;
    // private float preference;

    // constructor
    public Tile(Point point) {
      this.point = point;

      if (isInBounds(point)) {
        this.value = map.getTileValue(point);
        this.deadTile = map.isDeadTile(point);
      } else {
        this.value = GameBoardObjects.OBSTACLE;
        this.deadTile = true;
      }
    }

    public Tile(Point point, GameBoardObjects value, boolean deadTile) {
      this.point = point;
      this.value = value;
      this.deadTile = deadTile;
    }

    // Getters
    public Point getPoint() {
      return point;
    }

    public GameBoardObjects getValue() {
      return value;
    }

    public boolean isDeadTile() {
      return deadTile;
    }

    // Setters
    public void setCoordinate(Point point) {
      this.point = point;
    }

    public void setValue(GameBoardObjects value) {
      this.value = value;
    }

    public void setDeadTile(boolean deadTile) {
      this.deadTile = deadTile;
    }
  }

  public String getMove() {
    String[] possibleMoves = { "up", "right", "down", "left" };
    List<String> goodMoves = new ArrayList<String>();

    for (int i = 0; i < 4; i++) {
      if (tile[i].isDeadTile() == false)
        goodMoves.add(possibleMoves[i]);
    }

    int choice = new Random().nextInt(goodMoves.size());
    String move = goodMoves.get(choice);

    return move;
  }

  public boolean isInBounds(Point point) {
    int x = point.getX(), y = point.getY();
    
    if (x > 0 && x < 11 && y > 0 && y < 11)
      return true;
    else
      return false;
  }

  public void printOptions() {
    for (Tile t : tile) {
      System.out.println(t.isDeadTile());
    }
  }
}
