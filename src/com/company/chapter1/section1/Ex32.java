package com.company.chapter1.section1;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.List;

public class Ex32 {
  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);
    double l = Double.parseDouble(args[1]);
    double r = Double.parseDouble(args[2]);

    List<Double> inputs = new ArrayList<>();
    for (int i = 3; i < args.length; i++) {
      inputs.add(Double.parseDouble(args[i]));
    }

    histogram(inputs, l, r, N);
  }

  public static void histogram(List<Double> numbers, double left, double right, int numberOfIntervals) {
    int[] values = countNumberInInterval(numbers, left, right, numberOfIntervals);
    double intervalSize = (right - left) / numberOfIntervals;

    double minX = left - 1;
    double maxX = right - 0.5;
    double minY = -2;
    double maxY = 0;

    for (int i = 0; i < values.length; i++) {
      if (values[i] > maxY) maxY = values[i];
    }
    maxY += 2;

    StdDraw.setCanvasSize(1024, 512);
    StdDraw.setXscale(minX, maxX);
    StdDraw.setYscale(minY, maxY);

    double middleX = minX + (maxX - minX) / 2;
    double middleY = minY + (maxY - minY) / 2;

    // Labels
    StdDraw.text(middleX, maxY - 0.5, "Numbers in intervals");
    StdDraw.text(minX + 0.25, middleY, "Numbers", 90);
    StdDraw.text(middleX, -1.2, "Intervals");

    // X labels
    for(int x = 0; x < numberOfIntervals; x++) {
      double minValue = left + (intervalSize * x);
      double maxValue = minValue + intervalSize - 0.01;
      String intervalDescription = String.format("[%.2f - %.2f]", minValue, maxValue);
      StdDraw.text(left + (x + 0.5) * intervalSize, -0.25, intervalDescription);
    }


    for (int i = 0; i < values.length; i++) {
      double x = left + i * intervalSize;
      double y = values[i] / 2.0;
      double halfWidth = intervalSize / 4.0;
      double halfHeight = values[i] / 2.0;

      StdDraw.filledRectangle(x, y, halfWidth, halfHeight);
    }
  }

  public static int[] countNumberInInterval(List<Double> numbers, double left, double right, int numberOfIntervals) {
    int[] result = new int[numberOfIntervals];
    double width = (right - left) / numberOfIntervals;
    for (double value : numbers) {
      value -= left;
      int index = (int) Math.ceil(value / width);
      if (index > 0) index -= 1;
      System.out.println(index);
      result[index] += 1;
    }

    return result;
  }
}
