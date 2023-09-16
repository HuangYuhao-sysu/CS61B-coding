package bearmaps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KDTree {
    
    private class Node {
        Point p;
        Node left;
        Node right;
        Character attr; // 'x' or 'y'

        public Node(Point p, Character attr) {
            this.p = p;
            this.attr = attr;
        }
    }

    Comparator<Point> xComparator = (p1, p2) -> Double.compare(p1.getX(), p2.getX());
    Comparator<Point> yComparator = (p1, p2) -> Double.compare(p1.getY(), p2.getY());

    private Node root;
    private ArrayList<Point> mutablePoints;

    public KDTree(List<Point> points) {
        mutablePoints = new ArrayList<>();
        mutablePoints.addAll(points);
        root = buildTree(root, mutablePoints, 'x');
    }

    private Node buildTree(Node n, ArrayList<Point> points, Character attr) {
        if (points.size() == 0) return null;
        if (points.size() == 1) return new Node(points.get(0), attr);

        int middleIndex = findMiddle(points, attr);
        Point middlePoint = points.get(middleIndex);
        n = new Node(middlePoint, attr);

        Character newAttr = attr == 'x' ? 'y' : 'x';
        ArrayList<Point> left = splitPoints(points, middleIndex, "left");
        ArrayList<Point> right = splitPoints(points, middleIndex, "right");
        n.left = buildTree(n.left, left, newAttr);
        n.right = buildTree(n.right, right, newAttr);
        return n;
    }

    private int findMiddle(List<Point> points, Character attr) {

        if (attr == 'x') {
            points.sort(xComparator);
        } else {
            points.sort(yComparator);
        }
        // 2 - > [] [0] [1]
        // ...
        // 7 - > [0-2] [3] [4-6]
        // 8 - > [0-2] [3] [4-7]
        // 9 - > [0-3] [4] [5-8]
        return (points.size()-1)/2;
    }

    private ArrayList<Point> splitPoints(ArrayList<Point> points, int middleIndex, String leftOrRight) {
        ArrayList<Point> Points = new ArrayList<>();
        int startIndex = leftOrRight == "left" ? 0 : middleIndex + 1;
        int endIndex = leftOrRight == "left" ? middleIndex : points.size();
        for (int i = startIndex; i < endIndex; i += 1) {
            Points.add(points.get(i));
        }
        return Points;
    }

    public Point nearest(double x, double y) {
        Point targetPoint = new Point(x, y);
        return nearest(root, root, targetPoint);
    }

    private Point nearest(Node currentNode, Node nearestNode, Point targetPoint) {
        if (currentNode == null) return nearestNode.p;
        if (Point.distance(currentNode.p, targetPoint) < Point.distance(nearestNode.p, targetPoint)) {
            nearestNode = currentNode;
        }

        Node goodChild;
        Node badChild;
        if (currentNode.attr == 'x') {
            if (targetPoint.getX() < currentNode.p.getX()) {
                goodChild = currentNode.left;
                badChild = currentNode.right;
            } else {
                goodChild = currentNode.right;
                badChild = currentNode.left;
            }
        } else {
            if (targetPoint.getY() < currentNode.p.getY()) {
                goodChild = currentNode.left;
                badChild = currentNode.right;
            } else {
                goodChild = currentNode.right;
                badChild = currentNode.left;
            }
        }

        // Always check goodChild.
        Point goodNearest = nearest(goodChild, nearestNode, targetPoint);

        // Not always check badChild.
        Point badChildCouldNearest;
        Point badNearest;
        if (currentNode.attr == 'x') {
            badChildCouldNearest = new Point(currentNode.p.getX(), targetPoint.getY());
        } else {
            badChildCouldNearest = new Point(targetPoint.getX(), currentNode.p.getY());
        }

        if (Point.distance(nearestNode.p, targetPoint) > Point.distance(badChildCouldNearest, targetPoint)) {
            badNearest = nearest(badChild, nearestNode, targetPoint);
        } else {
            badNearest = nearestNode.p;
        }
        
        if (Point.distance(goodNearest, targetPoint) >= Point.distance(badNearest, targetPoint)) {
            return badNearest;
        }
        return goodNearest;
    }

    public void printKDTree() {
        printKDTree(root, "");
    }

    private void printKDTree(Node n, String add) {
        if (n == null) return;
        System.out.println(add + n.p.toString());
        printKDTree(n.left, add + "    ");
        printKDTree(n.right, add + "    ");
    }
}
