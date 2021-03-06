package com.battlesnake.enums;

/**
 * @author Anton Zenin
 */
public enum GameBoardObjects {
  EMPTY(0), SNAKE_HEAD(1), OBSTACLE(2), FOOD(3), OUR_HEAD(4);

  public final int value;

  private GameBoardObjects(int value) {
    this.value = value;
  }

  public int getValue() {
        return value;
  }
}
