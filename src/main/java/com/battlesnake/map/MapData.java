package com.battlesnake.map;

import com.battlesnake.models.Snake;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;

public class MapData {
  public int[][] map = new int[11][11];

  private void resetMap() {
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        map[i][j] = 0;
      }
    }
  }

  public void fillMap(JsonNode moveRequest) {
    resetMap();
    
    JsonNodeType node = moveRequest.get("board").get("snakes").getNodeType();

    for () {

    }
  }

  public int getMapCoordinate(int x, int y) {
    return map[x][y];
  }
}
/*
 * Example how to retrieve data from the request payload:
 * 
 * String gameId = moveRequest.get("game").get("id").asText(); int height =
 * moveRequest.get("board").get("height").asInt();
 * 
 */