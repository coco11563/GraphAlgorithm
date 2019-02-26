package graph.ds;

public class Point {
    protected int index;
    protected Point next;
    public Point(int index) {
        this.index = index;
    }

    public Point getNext() {
        return next;
    }

    public void setNext(Point next) {
        this.next = next;
    }

    public boolean hasNext() {
        return this.next != null;
    }

    @Override
    public int hashCode() {
        return this.index;
    }

    public String toString() {
        return "[" + this.index + "]";
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Point)) return false;
        Point p = (Point) obj;
        return p.hashCode() == this.hashCode();
    }
}
