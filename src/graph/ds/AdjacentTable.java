package graph.ds;

import java.util.*;

public class AdjacentTable {
    private ArrayList<ArrayList<Integer>> adjTable;
    private int tableSize;
    private int[] Indegree;
    private int[] Outdegree;
    private ArrayList<Point> symbolTable;
    public AdjacentTable(int tableSize) {
        this.tableSize = tableSize;
        this.adjTable = new ArrayList<>(tableSize);
        this.symbolTable = new ArrayList<>();
        for (int i = 0 ; i < tableSize ; i ++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j = 0 ; j < tableSize ; j ++) {
                temp.add(0);
            }
            this.adjTable.add(temp);
            this.symbolTable.add(new Point(i));
        }
        this.Indegree = new int[tableSize];
        this.Outdegree = new int[tableSize];
    }

    public boolean isConnected(int indexA, int indexB) {
        return adjTable.get(indexA).get(indexB) != 0;
    }

    public int get(int indexA, int indexB) {
        return adjTable.get(indexA).get(indexB);
    }
    public ArrayList<Integer> get(int indexA) {
        ArrayList<Integer> ret = new ArrayList<>();
        ArrayList<Integer> adj = adjTable.get(indexA);
        for (int i = 0 ; i < adj.size() ; i ++) {
            if (adj.get(i) != 0) ret.add(i);
        }
        return ret;
    }
    // A => B
    public void connect(int indexA, int indexB) {
        connect(indexA, indexB, 1);
    }
    public void doubleConnect(int indexA, int indexB) {
        connect(indexA, indexB, 1);
        connect(indexB, indexA, 1);
    }
    //A =[WEIGHT]=> B
    public void connect(int indexA, int indexB, int weight) {
        assert indexA < tableSize && indexB < tableSize;
        assert indexA != indexB;
        assert weight > 0;

        this.adjTable.get(indexA).set(indexB, weight);
        Indegree[indexB] += 1;
        Outdegree[indexA] += 1;
    }

    public int[] topologySort() throws Exception {
        int[] temp = Indegree.clone();
        int[] ret = new int[tableSize];
        HashSet<Integer> hs = new HashSet<>();
        for (int i = 0; i < tableSize; i ++) {
            if (isCycle(temp)) throw new Exception("the graph has a cycle, so can't sorted by top");
            ret[i] = firstZero(temp, hs);
            hs.add(ret[i]);
            int index = 0;
            for (int in : this.adjTable.get(ret[i])) {
                if (in != 0 && index != ret[i]) temp[index] -= 1;
                index ++;
            }
        }
        return ret;
    }

    public int getTableSize() {
        return tableSize;
    }

    public boolean isCycle(int[] indegree) {
        int tmp = 1;
        for (int i : indegree) {
            tmp = tmp * i;
        }
        return tmp != 0;
    }

    public void Dijkstra(int startIndex) {
//        Map<Integer, Trace> art = new HashMap<>();
        int[] dist = new int[tableSize];
        int[] pre = new int[tableSize];
        for (int i = 0 ; i < tableSize ; i ++) {
            if (i == startIndex) continue;
//            art.put(i, new Trace(new Point(startPoint.index)));
            dist[i] = Integer.MAX_VALUE;
            pre[i] = -1;
        }
        int[] know = new int[tableSize];
        while (arrayContain(know, 0)) {
            int index = firstMinimal(know, dist);
            know[index] = 1;
//            pre[index] = index;
            List<int[]> conn = getConnected(index);
            for (int[] i : conn) {
                if (dist[i[0]] > (i[1] + dist[index]) && know[i[0]] == 0) {
                    dist[i[0]] = (i[1] + dist[index]);
                    pre[i[0]] = index;
                }
            }
        }
//        return art;
        for (int i = 0; i < tableSize; i++) {
            printRoute(pre, i);
        }
    }
    public void printRoute(int[] pre, int to) {
        System.out.print(to);
        int temp = to;
        while(pre[temp] != temp) {
            System.out.println(" => " + pre[temp]);
            temp = pre[temp];
        }
        System.out.println();
    }
    public List<int[]> getConnected(int index) {
        List<int[]> ret = new LinkedList<>();
        int ind = 0;
        for (int i : adjTable.get(index)) {
            if (i != 0) ret.add(new int[]{ind, i});
            ind ++;
        }
        return ret;
    }

    public static int firstMinimal(int[] knowArr, int[] distArr) {
        int[] temp = distArr.clone();
        int index = 0;
        List<int[]> st = new LinkedList<>();
        for (int i : knowArr) {
            if (i == 0) {
                st.add(new int[]{index, distArr[index]});
            }
            index ++;
        }
        int minIndex = st.get(0)[0];
        int minDist = st.get(0)[1];
        for (int[] i : st) {
            if (minDist > i[1]) minIndex = i[0];
        }
        return minIndex;
    }
    public int firstUnknown (int[] knowArr) {
        int index = 0;
        for (int i : knowArr) {
            if (i == 0)
                return index;
            index ++;
        }
        return -1;
    }

    public static boolean arrayContain(int[] arr, int elem) {
        for (int i : arr) {
            if (i == elem) return true;
        }
        return false;
    }
    public boolean isCycle() {
        return isCycle(this.Indegree);
    }

    public int firstZero(int[] arr, HashSet<Integer> hs) {
        int index = 0;
        for (int i : arr){
            if (i == 0 && !hs.contains(index)) return index;
            index ++;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < tableSize ; i ++) {
            for (int j = 0 ; j < tableSize ; j ++) {
                sb.append(adjTable.get(i).get(j));
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        else if (obj == this) return true;
        else if (! (obj instanceof AdjacentTable)) return false;
        else {
            AdjacentTable other = (AdjacentTable) obj;
            if (other.tableSize != this.tableSize) return false;
            else {
                for (int i = 0 ; i < tableSize ; i ++) {
                    for (int j = 0 ; j < tableSize ; j ++) {
                        if (other.get(i, j) != this.get(i, j)) return false;
                    }
                }
                return true;
            }
        }
    }


    public static void main(String[] args) {
        AdjacentTable adj = new AdjacentTable(7);
        adj.connect(0,2);
        adj.connect(0,3);
        adj.connect(0,1);
        adj.connect(3,2);
        adj.connect(3,5);
        adj.connect(3,6);
        adj.connect(1,5);
        adj.connect(1,3);
        adj.connect(1,4);
        adj.connect(4,6);
        adj.connect(4,3);
        adj.connect(6,5);
        try {
            System.out.println(Arrays.toString(adj.topologySort()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        AdjacentTable adj2 = new AdjacentTable(7);
        adj2.connect(0,1,2);
        adj2.connect(0,3,1);
        adj2.connect(1,4,10);
        adj2.connect(1,3,3);
        adj2.connect(2,0,4);
        adj2.connect(2,5,5);
        adj2.connect(3,2,2);
        adj2.connect(3,4,2);
        adj2.connect(3,5,8);
        adj2.connect(3,6,4);
        adj2.connect(4,6,6);
        adj2.connect(6,5,1);

        adj2.Dijkstra(0);
    }
}
