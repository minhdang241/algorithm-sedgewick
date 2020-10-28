package com.company.chapter1.section5;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Animation {
  private Location[] locations;
  private int numberOfSites;

  public Animation(int numberOfSites) {
    this.numberOfSites = numberOfSites;
    locations = new Location[numberOfSites];
  }

  private class Location {
    double x;
    double y;

    public Location(double x, double y) {
      this.x = x;
      this.y = y;
    }
  }

  private void animation() {
    initializeGrid(numberOfSites);
    RandomGridGenerator.Connection[] connections = RandomGridGenerator.generate(numberOfSites);
    QuickUnionPathCompressionUF uf = new QuickUnionPathCompressionUF(numberOfSites);
    int i = 0;
    for (RandomGridGenerator.Connection connection : connections) {
      StdOut.println(i);
      i++;
      int p = connection.p;
      int q = connection.q;
      if (uf.connected(p, q)) continue;
      uf.union(p, q);
      drawConnection(p, q);

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void initializeGrid(int n) {
    StdDraw.setPenRadius(0.015);
    // Add margin to the coordinate system
    StdDraw.setXscale(-1, 2);
    StdDraw.setYscale(-1, 2);
    int numberOfCols = (int) Math.ceil(Math.sqrt(n));
    int numberOfRows = numberOfCols;
    double x = 1.0 / (numberOfCols - 1);

    for (int i = 0; i < numberOfRows; i++) {
      for (int j = 0; j < numberOfCols; j++) {
        if (n == 0) return;
        StdDraw.filledCircle(i * x, j * x, 0.015);
        locations[numberOfSites - n] = new Location(i * x, j * x);
        n--;
      }
    }
  }

  private void drawConnection(int p, int q) {
    Location locationP = locations[p];
    Location locationQ = locations[q];
    StdDraw.setPenRadius();
    StdDraw.setPenColor(StdDraw.ORANGE);
    StdDraw.line(locationP.x, locationP.y, locationQ.x, locationQ.y);
  }

  public static void main(String[] args) {
    int numberOfSites = Integer.parseInt(args[0]);
    Animation animation = new Animation(numberOfSites);
    animation.animation();
  }
}
