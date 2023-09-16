package bearmaps;

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

    private Node root;

    public KDTree(List<Point> points) {
        for (Point p : points) {
            add(p);
        }
    }

    // Get a point and add to kd tree
    private void add(Point point) {
        this.root = add(root, point, 'x');
    }

    private Node add(Node n, Point p, Character attr) {
        if (n == null) return new Node(p, attr);
        double cmp;
        Character newAttr;
        if (attr == 'x') {
            cmp = p.getX() - n.p.getX();
            newAttr = 'y';
        } else {
            cmp = p.getY() - n.p.getY();
            newAttr = 'x';
        }
        if (cmp < 0) {
            n.left = add(n.left, p, newAttr);
        } else if (cmp > 0) {
            n.right = add(n.right, p, newAttr);
        } else {
            n.p = p;
        }
        return n;
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
