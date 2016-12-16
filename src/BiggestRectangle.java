import java.util.*;

/**
 * Created by Allan Wang on 2016-12-15.
 * <p>
 * Find the area of the biggest possible rectangle in which all the integer points, including the edges, are given in the list
 */
public class BiggestRectangle {

    private final TreeMap<Integer, TreeSet<Integer>> dataMap = new TreeMap<>();
    private TreeMap<Integer, Stack<Integer>> stackMap = new TreeMap<>();
    private R maxRectangle;
    private static final boolean DEBUG = false;

    public static void main(String[] args) {
        new BiggestRectangle(new P[]{
                new P(0, 0),
                new P(3, 1),
                new P(1, 1),
                new P(2, 2),
                new P(1, 2)}).findRectangle();
    }

    public BiggestRectangle(P[] points) {
        if (points == null || points.length == 0) {
            print("Invalid point array");
            System.exit(0);
        }
        getData(points);
    }

    void findRectangle() {
        /*
         * Datamap is an ordered set of all the coordinates; loop through it to find adjacent points
         */
        P point = new P(0, 0); //reuse memory
        int lastX = dataMap.firstKey();
        boolean gapX = false, gapY = false;
        for (int x : dataMap.keySet()) {
            if (x - lastX > 1) { //gap found
                if (!gapX) {
                    gapX = true;
                    //clear for all and calculate
                    for (int yy : stackMap.keySet()) {
                        findMax(lastX, yy);
                    }
                }
                continue;
            }
            TreeSet<Integer> column = dataMap.get(x);
            int lastYpre = column.first();
            int lastYpost = lastYpre;
            for (int y : column) {
                if (DEBUG) print("(%d, %d)", x, y);
                if (y - lastYpost > 1) { //gap found
                    if (!gapY) {
                        gapY = true;
                        //push upper bound to stack for lower bound
                        addPointToStackRange(lastYpre, lastYpost);
                        for (int yy = lastYpost + 1; yy < y; yy++) findMax(x, yy);
                    }
                    lastYpre = y;

                    //clear current y and calculate
                } else {
                    if (gapY && y - lastYpost == 1) {
                        gapY = false;
                        lastYpre = y;
                    }
                }
                lastYpost = y;
            }

        }
    }

    private void addPointToStackRange(int minY, int maxY) {
        for (Integer i : stackMap.keySet()) {
            if (i < minY) continue;
            else if (i > maxY) return;
            stackMap.get(i).push(maxY);
        }
    }

    private void findMax(int x, int y) {
        Stack<Integer> stack = stackMap.get(y);
        if (stack == null) return;
        int w = 0;
        while (!stack.isEmpty()) {
            w++;
            int h = stack.pop();
            if (w * h > maxRectangle.area) maxRectangle = new R(x - w, y - h, x, y);
        }
    }

    private void getData(final P[] points) {
        for (P p : points) {
            if (dataMap.containsKey(p.x)) {
                if (dataMap.get(p.x).contains(p.y)) print("Duplicate point (%d, %d)", p.x, p.y);
                else dataMap.get(p.x).add(p.y);
            } else
                dataMap.put(p.x, new TreeSet<Integer>() {{
                    add(p.y);
                }});
            if (!stackMap.containsKey(p.y)) stackMap.put(p.y, new Stack<>());
        }
    }

    private static void print(String s, Object... o) {
        System.out.println(String.format(Locale.CANADA, s, o));
    }
}

/*
 * MaxMin - holds two values, max and min
 */
class M {
    final int min, max;

    M(int min, int max) {
        this.min = min;
        this.max = max;
    }
}

/*
 * Bounds; holds minmax for x and y
 */
class B {
    final M x, y;

    B(M x, M y) {
        this.x = x;
        this.y = y;
    }
}

/*
 * Coordinate point - holds x and y
 * Made another class just to make it clearer
 */
class P {
    int x, y;

    P(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class R {
    final int minX, minY, maxY, maxX, area;

    R(int minX, int minY, int maxX, int maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
        area = (maxY - minY) * (maxX - minX);
    }
}
