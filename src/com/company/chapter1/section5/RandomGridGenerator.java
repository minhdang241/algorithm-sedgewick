package com.company.chapter1.section5;

import com.company.chapter1.section3.RandomBag;
import edu.princeton.cs.algs4.StdOut;

public class RandomGridGenerator {
    static class Connection {
        int p;
        int q;

        public Connection(int p, int q) {
            this.p = p;
            this.q = q;
        }
    }

    public static Connection[] generate(int n) {
        //Considering the NxN grid as
        /*
          1 2 3 4
        1 - * * *
        2 * - * *
        3 * * - *
        4 * * * -
        */

        RandomBag<Connection> randomBag = new RandomBag<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    Connection connection = new Connection(i, j);
                    randomBag.add(connection);
                }
            }
        }

        Connection[] connections = new Connection[randomBag.size()];
        int index = 0;
        for (Connection connection : randomBag) {
           connections[index] = connection;
           index++;
        }

        return connections;
    }


    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Connection[] connections = generate(n);
        StdOut.println("Size: " + connections.length);
        for (Connection connection: connections) {
            StdOut.println(connection.p + " - " + connection.q);
        }
    }
}
