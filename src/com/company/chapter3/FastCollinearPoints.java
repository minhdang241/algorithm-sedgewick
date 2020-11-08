package com.company.chapter3;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class FastCollinearPoints {

  private final LineSegment[] lineSegments;
  private Point[] duplicate;

  public FastCollinearPoints(Point[] points) {
    validate(points);

    List<LineSegment> lineSegmentsList = new ArrayList<>();

    Arrays.sort(points);
    for (Point point : points) {
      int count = 0;

      double currSlope = Double.NEGATIVE_INFINITY;

      duplicate = points.clone();

      Arrays.sort(duplicate, point.slopeOrder());
      int j = 0;
      for (j = 1; j < duplicate.length; j++) {

        double slope = duplicate[j].slopeTo(point);

        if (slope != currSlope) {
          if (count >= 3) {
            Arrays.sort(duplicate, j - count, j);
            if (point.compareTo(duplicate[j - count]) < 0) {
              LineSegment lineSegment = new LineSegment(point, duplicate[j - 1]);
              lineSegmentsList.add(lineSegment);
            }
          }
          count = 1;
          currSlope = slope;
        } else {
          count++;
        }
      }

      if (count >= 3) {
        Arrays.sort(duplicate, j - count, j);
        if (point.compareTo(duplicate[j - count]) < 0) {
          LineSegment lineSegment = new LineSegment(point, duplicate[j - 1]);
          lineSegmentsList.add(lineSegment);
        }
      }
    }

    lineSegments = new LineSegment[lineSegmentsList.size()];
    int i = 0;
    for (LineSegment line : lineSegmentsList) {
      lineSegments[i++] = line;
    }
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

  public int numberOfSegments() {
    return lineSegments.length;
  }

  public LineSegment[] segments() {
    return lineSegments;
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
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();
    double a = Double.POSITIVE_INFINITY;
    double b = Double.POSITIVE_INFINITY;
    StdOut.println(a == b);
  }
}
