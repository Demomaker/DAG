package net.demomaker.applegame.game.shop;

import java.util.PriorityQueue;
import java.util.Queue;

public class ShopActQueue {

  private static Queue<ShopAct> queue = new PriorityQueue<>();

  public static void add(ShopAct shopAct) {
    queue.add(shopAct);
  }

  public static ShopAct pop() {
    return queue.remove();
  }

  public static void clear() {
    queue.clear();
  }

  public static int getLength() {
    return queue.size();
  }
}
