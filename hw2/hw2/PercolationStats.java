package hw2;

import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.introcs.StdRandom;

public class PercolationStats {

    private double[] results;
    private int number;
    /**
     * Perform T independent experiments on an N-by-N grid.
     * */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) throw new IllegalArgumentException();
        number = N;
        results =  new double[T];
        for (int i = 0; i < T; i += 1) {
            results[i] = doOnce(N, pf);
        }
    }

    private double doOnce(int N, PercolationFactory pf) {
        Percolation p = pf.make(N);
        while (!p.percolates()) {
            p.open(StdRandom.uniform(N), StdRandom.uniform(N));
        }
        return p.numberOfOpenSites()/(double) (N*N);
    }

    /**
     * Sample mean of percolation threshold
     * */
    public double mean() {
        double meanValue = 0;
        for (int i = 0; i < results.length; i += 1) {
            meanValue += results[i];
        }
        return meanValue / results.length;
    }

    /**
     * // sample standard deviation of percolation threshold
     */
    public double stddev() {
        double stddevValue = 0;
        double mean = mean();
        for (int i = 0; i < results.length; i += 1) {
            stddevValue += Math.pow(results[i] - mean, 2);
        }
        return Math.pow(stddevValue / (results.length - 1), 1/2.0);
    }

    /**
     * Low endpoint of 95% confidence interval
     * */
    public double confidenceLow() {
        return mean() - 1.96*stddev()/Math.pow(results.length, 1/2.0);
    }

    /**
     * High endpoint of 95% confidence interval
     * */
    public double confidenceHigh() {
        return mean() + 1.96*stddev()/Math.pow(results.length, 1/2.0);
    }

    public static void main(String args[]) {
        PercolationFactory pf = new PercolationFactory();
        int N = 200;
        int T = 200;
        PercolationStats p = new PercolationStats(N, T, pf);
        System.out.println("**** Test: Grid: " + N + "*" + N + " times: " + T);
        System.out.println("mean : " + p.mean());
        System.out.println("stddev : " + p.stddev());
        System.out.println("confidenceLow : " + p.confidenceLow());
        System.out.println("confidenceHigh : " + p.confidenceHigh());
    }
}
