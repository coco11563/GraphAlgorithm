package graph.ds;

public class Trace {
    private Point start;
    private Point end;
    public Trace(Point point) {
        this.start = point;
        this.end = start;
    }

    public void add(Point p) {
        end.next = p;
        end = end.next;
    }

    @Override
    public String toString() {
        Point cur = start;
        StringBuilder sb = new StringBuilder();
        while (cur.hasNext()) {
            sb.append(cur.toString() + " ");
            cur = cur.next;
        }
        return sb.toString();
    }

}
