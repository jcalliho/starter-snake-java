package com.battlesnake.starter;

import com.battlesnake.starter.Point;
import com.battlesnake.map.MapData;
import com.battlesnake.enums.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;


public class Adjacent {

  private static final Logger LOG = LoggerFactory.getLogger(Adjacent.class);

  protected MapData mapData;
  private Tile[] tile = new Tile[4];


  public Adjacent(MapData mapData) {
    this.mapData = mapData;

    // for (int i = 0; i < mapData.map.length; i++) {
    //   for (int j = 0; j < mapData.map[0].length; j++) {
    //     System.out.print(mapData.map[i][j]);
    //     System.out.print(" ");		
    //   }
    //   System.out.println("");
    // }

    // for (int i = 0; i < mapData.length; i++) {
    //   LOG.info(
    //     "{} {} {} {} {} {} {} {} {} {} {}",
    //     mapData[i][0], mapData[i][1], mapData[i][2], mapData[i][3], mapData[i][4], mapData[i][5], mapData[i][6], mapData[i][7], mapData[i][8], mapData[i][9], mapData[i][10], mapData[i][11]
    //   );
    // }

    Point head = mapData.getHead();

    // Set all adjacent tiles pont value and enum value
    tile[0] = new Tile(new Point(head.getX(), head.getY() + 1));	// up
    tile[1] = new Tile(new Point(head.getX() + 1, head.getY()));	// right
    tile[2] = new Tile(new Point(head.getX(), head.getY() - 1));	// down
    tile[3] = new Tile(new Point(head.getX() - 1, head.getY()));	// left
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
        LOG.info("Is in bounds");
        LOG.info("x: {}", point.getX());
        LOG.info("y: {}", point.getY());
        this.value = mapData.getTileValue(point);
        this.deadTile = mapData.isDeadTile(point);
      } else {
        this.value = GameBoardObjects.OBSTACLE;
        this.deadTile = true;
        LOG.info("Is out of bounds");
        LOG.info("x: {}", point.getX());
        LOG.info("y: {}", point.getY());
      }
    }

    // public Tile(Point point, GameBoardObjects value, boolean deadTile) {
    //   this.point = point;
    //   this.value = value;
    //   this.deadTile = deadTile;
    // }

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
    // List<String> goodMoves = new ArrayList<String>();
    int liveTiles = 0;
    for (Tile t : tile) {
      if (t.isDeadTile() == false) {
        liveTiles++;
      }
    }
    
    if (liveTiles > 0) {
      String[] goodMoves = new String[liveTiles];

      int index = 0;
      for (int i = 0; i < tile.length; i++) {
        if (tile[i].isDeadTile() == false) {
          goodMoves[index] = possibleMoves[i];
          index++;
        }
      }
      for (String s : goodMoves) {
        LOG.info("goodmove: {}", s);
      }
      return goodMoves[0];
    } else {
      LOG.info("liveTiles is 0");
      return "down";
    }

    // for (int i = 0; i < 4; i++) {
    //   if (tile[i].isDeadTile() == false)
    //     goodMoves.add(possibleMoves[i]);
    // }

    // int choice = new Random().nextInt(goodMoves.size());
    // String move = goodMoves.get(choice);

    // return move;
  }

  public boolean isInBounds(Point point) {
    int x = point.getX(), y = point.getY();
    
    if (x >= 0 && x < 11 && y >= 0 && y < 11) {
      return true;
    } else {
      return false;
    }
  }

  public void printOptions() {
    for (Tile t : tile) {
      System.out.println(t.isDeadTile());
    }
  }
}
