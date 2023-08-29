import org.junit.Test;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import static org.junit.Assert.*;

import java.security.Key;
import java.util.*;

import java.util.ArrayList;

/**
 * Created by hug.
 */
public class Experiments {

    public static void experiment1() {
        Random r = new Random();
        List<Integer> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();
        List<Double> y2Values = new ArrayList<>();
        Set<Integer> keySet = new HashSet<>();
        BST<Integer> testTree = new BST<>();
        for (int i = 0; i < 5000; i += 1){
            int tmpRandom = r.nextInt(10000);
            keySet.add(tmpRandom);
            testTree.add(tmpRandom);
            xValues.add(i);
            yValues.add(calAverageDepth(testTree, keySet));
            y2Values.add(ExperimentHelper.optimalAverageDepth(i));
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("Random insert average depth", xValues, yValues);
        chart.addSeries("Optimal average depth", xValues, y2Values);
        new SwingWrapper(chart).displayChart();
    }

    public static double calAverageDepth(BST<Integer> testTree, Set<Integer> keySet) {
        double TotalDepth = 0;
        for (Integer key : keySet) {
            TotalDepth += testTree.depth(key);
        }
        return  TotalDepth/(double) keySet.size();
    }

    public static void experiment2() {
        Random r = new Random();
        List<Integer> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();
        List<Double> y2Values = new ArrayList<>();
        List<Double> y3Values = new ArrayList<>();
        Set<Integer> keySet = new HashSet<>();
        Set<Integer> keySet2 = new HashSet<>();
        BST<Integer> testTree = new BST<>();
        BST<Integer> testTree2 = new BST<>();
        for (int i = 0; i < 5000; i += 1){
            int tmpRandom = r.nextInt(10000);
            keySet.add(tmpRandom);
            keySet2.add(tmpRandom);
            testTree.add(tmpRandom);
            testTree2.add(tmpRandom);
            xValues.add(i);
            yValues.add(calAverageDepth(testTree, keySet));
            y2Values.add(ExperimentHelper.optimalAverageDepth(i));
            ExperimentHelper.randomOp(testTree2, keySet2, false);
            y3Values.add(calAverageDepth(testTree2, keySet2));
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("Random insert average depth", xValues, yValues);
        chart.addSeries("Optimal average depth", xValues, y2Values);
        chart.addSeries("randomOp average depth", xValues, y3Values);
        new SwingWrapper(chart).displayChart();
    }

    public static void experiment3() {
        Random r = new Random();
        List<Integer> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();
        List<Double> y2Values = new ArrayList<>();
        List<Double> y3Values = new ArrayList<>();
        List<Double> y4Values = new ArrayList<>();
        Set<Integer> keySet = new HashSet<>();
        Set<Integer> keySet2 = new HashSet<>();
        Set<Integer> keySet3 = new HashSet<>();
        BST<Integer> testTree = new BST<>();
        BST<Integer> testTree2 = new BST<>();
        BST<Integer> testTree3 = new BST<>();
        for (int i = 0; i < 5000; i += 1){
            int tmpRandom = r.nextInt(10000);
            keySet.add(tmpRandom);
            keySet2.add(tmpRandom);
            keySet3.add(tmpRandom);
            testTree.add(tmpRandom);
            testTree2.add(tmpRandom);
            testTree3.add(tmpRandom);
            xValues.add(i);
            yValues.add(calAverageDepth(testTree, keySet));
            y2Values.add(ExperimentHelper.optimalAverageDepth(i));
            ExperimentHelper.randomOp(testTree2, keySet2, false);
            y3Values.add(calAverageDepth(testTree2, keySet2));
            ExperimentHelper.randomOp(testTree3, keySet3, true);
            y4Values.add(calAverageDepth(testTree3, keySet3));
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("Random insert average depth", xValues, yValues);
        chart.addSeries("Optimal average depth", xValues, y2Values);
        chart.addSeries("randomOp false average depth", xValues, y3Values);
        chart.addSeries("randomOp true average depth", xValues, y4Values);
        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
        // experiment2();
        experiment3();
    }

    @Test
    public void testBasic(){
        BST<Integer> testTree = new BST<>();
        for (int i = 5; i < 10; i += 1) {
            testTree.add(i);
            assertEquals(i-5,testTree.depth(i));
        }
        for (int i = 0; i < 5; i += 1) {
            testTree.add(i);
            assertEquals(i+1,testTree.depth(i));
        }
    }
}
