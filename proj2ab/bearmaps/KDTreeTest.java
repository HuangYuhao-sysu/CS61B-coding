package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KDTreeTest {

    @Test
    public void naiveBasicTest() {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        assertEquals(3.3, ret.getX(), 0.01);
        assertEquals(4.4, ret.getY(), 0.01); // evaluates to 4.4
    }

    @Test
    public void KDBasicTest() {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        KDTree kdn = new KDTree(List.of(p1, p2, p3));
        kdn.printKDTree();
    }

    @Test
    public void KDMoreTest() {
        int NUMBER = 100;
        Random r = new Random();
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < NUMBER; i += 1) {
            double x, y;
            x = r.nextInt(0, 10);
            y = r.nextInt(0, 10);
            points.add(new Point(x, y));
        }

        KDTree kdn = new KDTree(points);
        kdn.printKDTree();
    }

    @Test
    public void KDMoreTest2() {
        Point p1 = new Point(5, 6);
        Point p2 = new Point(1, 5);
        Point p3 = new Point(7, 3);
        Point p4 = new Point(2, 2);
        Point p5 = new Point(4, 9);
        Point p6 = new Point(9, 1);
        Point p7 = new Point(8, 7);
        Point p8 = new Point(6, 2);

        Point[] points = new Point[]{p8,p7,p6,p5,p4,p3,p2,p1};
        Random r = new Random();
        List<Point> pointsList = new ArrayList<>();
        for (Point p : points) {
            pointsList.add(p);
        }
        KDTree kdn = new KDTree(pointsList);
        kdn.printKDTree();
    }

    @Test
    public void KDNearestTest1() {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        KDTree kdn = new KDTree(List.of(p1, p2, p3));
        Point ret = kdn.nearest(3.0, 4.0); // returns p2
        assertEquals(3.3, ret.getX(), 0.01);
        assertEquals(4.4, ret.getY(), 0.01); // evaluates to 4.4
    }

    @Test
    public void KDNearestTest2() {
        Point p1 = new Point(5, 6); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(1, 5);
        Point p3 = new Point(7, 3);
        Point p4 = new Point(2, 2);
        Point p5 = new Point(4, 9);
        Point p6 = new Point(9, 1);
        Point p7 = new Point(8, 7);
        Point p8 = new Point(6, 2);

        Point[] points = new Point[]{p1, p2, p3, p4, p5, p6, p7, p8};

        List<Point> pointsList = new ArrayList<>();
        for (Point point : points) {
            pointsList.add(point);
        }

        KDTree kdn = new KDTree(pointsList);
        NaivePointSet nn = new NaivePointSet(pointsList);
        kdn.printKDTree();

        assertEquals(kdn.nearest(3,6), nn.nearest(3, 6));
        System.out.println(kdn.nearest(3,6).toString());
        System.out.println(nn.nearest(3,6).toString());

        assertEquals(kdn.nearest(6,2.5), nn.nearest(6,2.5));
        System.out.println(kdn.nearest(6,2.5).toString());
        System.out.println(nn.nearest(6,2.5).toString());

        assertEquals(kdn.nearest(9,2), nn.nearest(9,2));
        System.out.println(kdn.nearest(9,2).toString());
        System.out.println(nn.nearest(9,2).toString());
    }

    @Test
    public void finalTest() {
        int NUMBER = 1000;
        Random r = new Random();
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < NUMBER; i += 1) {
            double x, y;
            x = r.nextDouble(-100, 100);
            y = r.nextDouble(-100, 100);
            points.add(new Point(x, y));
        }

        KDTree kdn = new KDTree(points);
        NaivePointSet nn = new NaivePointSet(points);

        for (int i = 0; i < NUMBER; i += 1) {
            Point target = new Point(r.nextDouble(-100,100),r.nextDouble(-100,100));
            assertEquals(kdn.nearest(target.getX(), target.getY()), nn.nearest(target.getX(), target.getY()));
        }
    }

    @Test
    public void speedTest() {
        int NUMBER = 100000;
        Random r = new Random();
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < NUMBER; i += 1) {
            double x, y;
            x = r.nextDouble(-100, 100);
            y = r.nextDouble(-100, 100);
            points.add(new Point(x, y));
        }

        KDTree kdn = new KDTree(points);
        NaivePointSet nn = new NaivePointSet(points);

        ArrayList<Point> targets = new ArrayList<>();
        for (int i = 0; i < NUMBER; i += 1) {
            targets.add(new Point(r.nextDouble(-100,100),r.nextDouble(-100,100)));
        }

        Stopwatch sw1= new Stopwatch();
        for (Point t : targets) {
            kdn.nearest(t.getX(),t.getY());
        }
        System.out.println("Total time KDTree elapsed: " + sw1.elapsedTime() +  " seconds.");

        Stopwatch sw2= new Stopwatch();
        for (Point t : targets) {
            nn.nearest(t.getX(),t.getY());
        }
        System.out.println("Total time NaivePointSet elapsed: " + sw2.elapsedTime() +  " seconds.");
    }
}
