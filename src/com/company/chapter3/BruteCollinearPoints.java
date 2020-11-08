package com.company.chapter3;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
  private LineSegment[] lineSegments;

  public BruteCollinearPoints(Point[] points) {
    validate(points);
    List<LineSegment> lineSegmentList = new ArrayList<>();
    for (int i = 0; i < points.length - 3; i++) {
      for (int j = i + 1; j < points.length - 2; j++) {
        for (int k = j + 1; k < points.length - 1; k++) {
          for (int m = k + 1; m < points.length; m++) {
            double slope1 = points[i].slopeTo(points[j]);
            double slope2 = points[i].slopeTo(points[k]);
            double slope3 = points[i].slopeTo(points[m]);
            if (slope1 == slope2 && slope2 == slope3) {
              Point maxPoint = points[i];
              Point minPoint = points[i];
              Point[] p = {points[i], points[j], points[k], points[m]};
              for (Point point : p) {
                if (point.compareTo(maxPoint) > 0) maxPoint = point;
                if (point.compareTo(minPoint) < 0) minPoint = point;
              }
              lineSegmentList.add(new LineSegment(minPoint, maxPoint));
            }
          }
        }
      }
    }
    lineSegments = new LineSegment[lineSegmentList.size()];
    int i = 0;
    for (LineSegment segment : lineSegmentList) {
      lineSegments[i++] = segment;
    }
  }

  public int numberOfSegments() {
    return lineSegments.length;
  }

  public LineSegment[] segments() {
    return lineSegments;
  }

  private static void validate(Point[] points) {
    if (points == null) throw new IllegalArgumentException();
    for (Point point : points) {
      if (point == null) throw new IllegalArgumentException();
    }
    Point[] copy = points.clone();
    Arrays.sort(copy);
    Point currPoint = null;
    for (Point point : copy) {
      if (currPoint == null) {
        currPoint = point;
      } else if (point.compareTo(currPoint) == 0) throw new IllegalArgumentException();
      else currPoint = point;
    }
  }

  public static void main(String[] args) {
    // read the n points from a file
    In in = new In(args[0]);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      int x = in.readInt();
      int y = in.readInt();
      points[i] = new Point(x, y);
    }
    // draw the points
    StdDraw.setPenRadius(0.015);
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
      p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();
  }
}