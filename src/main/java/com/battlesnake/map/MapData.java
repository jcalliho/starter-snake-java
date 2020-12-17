package com.battlesnake.map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.battlesnake.enums.GameBoardObjects;
import com.battlesnake.starter.Point;

public class MapData {

  private GameBoardObjects[][] map = new GameBoardObjects[11][11];

  public MapData(JsonNode moveRequest){
    // init map
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        map[i][j] = GameBoardObjects.EMPTY;
      }
    }

    JsonNode board = moveRequest.get("board");
    ArrayNode snakesArrayNode = (ArrayNode) board.get("snakes");
    
    ArrayNode snakeBody;
    JsonNode snake;
    JsonNode coordinate;
    JsonNode snakeHead;

    // Gets the snakes
    for (int i = 0; i < snakesArrayNode.size(); i++) {
      snake = snakesArrayNode.get(i);
      snakeBody = (ArrayNode) snake.get("body");
      
      // Gets the snake's bodies
      for (int j = 0; j < snakeBody.size(); j++) {
        coordinate = snakeBody.get(j);
        // Gets the x and y coordinates of the body and places it into the array
        map[coordinate.get("x").asInt()][coordinate.get("y").asInt()] = GameBoardObjects.OBSTACLE;
      }
      snakeHead = snake.get("head");
      // Gets the x and y coordinates of snake head and places it into the array
      map[snakeHead.get("x").asInt()][snakeHead.get("y").asInt()] = GameBoardObjects.SNAKE_HEAD;
    }

    ArrayNode foodArrayNode = (ArrayNode) board.get("food");
    JsonNode food;

    // Gets all the food
    for (int i = 0; i < foodArrayNode.size(); i++) {
      food = foodArrayNode.get(i);
      // Gets the x and y coordinates of the food and places it into the array
      map[food.get("x").asInt()][food.get("y").asInt()] = GameBoardObjects.FOOD;
    }

    JsonNode you = moveRequest.get("you");
    JsonNode youHead = you.get("head");
    // Gets the x and y coordinates of our snake head and places it into the array
    map[youHead.get("x").asInt()][youHead.get("y").asInt()] = GameBoardObjects.OUR_HEAD;
  }
    
  public GameBoardObjects getTileValue(Point point) {
    return map[point.getX()][point.getY()];
  }

  public boolean isDeadTile(Point point) {
    GameBoardObjects objs = map[point.getX()][point.getY()];
    if (objs == GameBoardObjects.OBSTACLE
    || objs == GameBoardObjects.SNAKE_HEAD) {
      return true;
    } else {
      return false;
    }
  }

  public Point getHead() {
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        if (map[i][j] == GameBoardObjects.OUR_HEAD)
          return new Point(i, j);
      }
    }
    return new Point(0,0);
  }
}

/*
JsonNode example = {
  "game" : {
    "id" : "194a6588-b3d9-4e37-97cf-a65ad44d9dc7",
    "ruleset" : {
      "name" : "solo",
      "version" : "v1.0.15"
    },
    "timeout" : 500
  },
  "turn" : 1,
  "board" : {
    "height" : 11,
    "width" : 11,
    "snakes" : [ {
      "id" : "gs_6Bd64TwKvq6jvg4h4Wyfp83D",
      "name" : "Test2",
      "latency" : "265",
      "health" : 99,
      "body" : [ {
        "x" : 0,
        "y" : 9
      }, {
        "x" : 1,
        "y" : 9
      }, {
        "x" : 1,
        "y" : 9
      } ],
      "head" : {
        "x" : 0,
        "y" : 9
      },
      "length" : 3,
      "shout" : ""
    } ],
    "food" : [ {
      "x" : 0,
      "y" : 8
    }, {
      "x" : 5,
      "y" : 5
    }, {
      "x" : 8,
      "y" : 4
    } ],
    "hazards" : [ ]
  },
  "you" : {
    "id" : "gs_6Bd64TwKvq6jvg4h4Wyfp83D",
    "name" : "Test2",
    "latency" : "265",
    "health" : 99,
    "body" : [ {
      "x" : 0,
      "y" : 9
    }, {
      "x" : 1,
      "y" : 9
    }, {
      "x" : 1,
      "y" : 9
    } ],
    "head" : {
      "x" : 0,
      "y" : 9
    },
    "length" : 3,
    "shout" : ""
  }
}
*/