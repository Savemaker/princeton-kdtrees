/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class PointSET {
    private class Node {
        private Point2D point;
        private double distanceToSearchPoint;

        public Node(Point2D point, Point2D target) {
            this.point = point;
            distanceToSearchPoint = (point.distanceTo(target) > 0 ? point.distanceTo(target) : target.distanceTo(point));
        }

        public Point2D getPoint() {
            return point;
        }

        public double getDistanceToSearchPoint() {
            return distanceToSearchPoint;
        }
    }
    private int size;
    private final TreeSet<Point2D> treeSet;

    public PointSET() {
        size = 0;
        treeSet = new TreeSet<>();
    }                            // construct an empty set of points

    public boolean isEmpty() {
        return size == 0;
    }                   // is the set empty?

    public int size() {
        return size;
    }                     // number of points in the set

    public void insert(Point2D p) {
        if (treeSet.add(p))
            size++;
    }           // add the point to the set (if it is not already in the set)

    public boolean contains(Point2D p) {
        return treeSet.contains(p);
    }         // does the set contain point p?

    public void draw() {
        treeSet.forEach(Point2D::draw);
    }                    // draw all points to standard draw

    public Iterable<Point2D> range(RectHV rect) {
        return treeSet.stream()
                .filter(rect::contains)
                .collect(Collectors.toList());
    }             // all points that are inside the rectangle (or on the boundary)

    public Point2D nearest(Point2D p) {
        if (treeSet.isEmpty())
            return null;
        List<Node> list = treeSet.stream()
                .map(current -> new Node(current, p))
                .sorted(new Comparator<Node>() {
                    @Override
                    public int compare(Node o1, Node o2) {
                        return Double.compare(o1.distanceToSearchPoint, o2.distanceToSearchPoint);
                    }
                }).collect(Collectors.toList());
        return list.get(0).getPoint();
    }        // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args) {

    }
}
