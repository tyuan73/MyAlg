package weekly.week9.day5;

import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.math.BigInteger;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Iterator;
import java.io.IOException;
import java.util.Arrays;
import java.io.InputStream;
import java.util.Random;
import java.util.Map;
import java.io.OutputStreamWriter;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.AbstractSet;
import java.util.Set;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * @author Egor Kulikov (egor@egork.net)
 */
public class EgorKSolution {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        AlexVsFedor solver = new AlexVsFedor();
        solver.solve(1, in, out);
        out.close();
    }
}

class AlexVsFedor {
    int[] mods = new int[2];

    {
        int j = 0;
        for (int i = (int) 1e9; j < mods.length; i++) {
            if (IntegerUtils.isPrime(i)) {
                mods[j++] = i;
            }
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int edgeCount = in.readInt();
        int[] from = new int[edgeCount];
        int[] to = new int[edgeCount];
        int[] weight = new int[edgeCount];
        IOUtils.readIntArrays(in, from, to, weight);
        ArrayUtils.orderBy(weight, from, to);
        MiscUtils.decreaseByOne(from, to);
        int start = 0;
        IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
        long numerator = 1;
        for (int i = 1; i <= edgeCount; i++) {
            if (i == edgeCount || weight[i] != weight[i - 1]) {
                Graph graph = new BidirectionalGraph(count);
                for (int j = start; j < i; j++) {
                    if (setSystem.get(from[j]) != setSystem.get(to[j])) {
                        graph.addSimpleEdge(setSystem.get(from[j]), setSystem.get(to[j]));
                    }
                }
                boolean[] visited = new boolean[count];
                for (int j = 0; j < count; j++) {
                    if (!visited[j] && graph.firstOutbound(j) != -1) {
                        numerator *= count(build(j, graph, visited));
                    }
                }
                for (int j = start; j < i; j++) {
                    setSystem.join(from[j], to[j]);
                }
                start = i;
            }
        }
        long denominator = count(build(0, BidirectionalGraph.createGraph(count, from, to), new boolean[count]));
        out.printLine(new Rational(numerator, denominator));
    }

    private int[][] build(int id, Graph graph, boolean[] visited) {
        Indexer<Integer> indexer = new Indexer<Integer>();
        dfs(id, graph, visited, indexer);
        int[][] matrix = new int[indexer.size()][indexer.size()];
        for (Map.Entry<Integer, Integer> entry : indexer.entrySet()) {
            int from = entry.getValue();
            for (int i = graph.firstOutbound(entry.getKey()); i != -1; i = graph.nextOutbound(i)) {
                int to = indexer.get(graph.destination(i));
                matrix[from][from]--;
                matrix[to][to]--;
                matrix[from][to]++;
                matrix[to][from]++;
            }
        }
        return matrix;
    }

    private void dfs(int id, Graph graph, boolean[] visited, Indexer<Integer> indexer) {
        if (visited[id]) {
            return;
        }
        indexer.get(id);
        visited[id] = true;
        for (int i = graph.firstOutbound(id); i != -1; i = graph.nextOutbound(i)) {
            dfs(graph.destination(i), graph, visited, indexer);
        }
    }

    long count(int[][] matrix) {
        long[][] m = new long[matrix.length - 1][matrix.length - 1];
        long result = 0;
        long mod = 1;
        for (int cMod : mods) {
            for (int j = 0; j < m.length; j++) {
                for (int k = 0; k < m.length; k++) {
                    m[j][k] = matrix[j][k] >> 1;
                }
            }
            for (int j = 0; j < m.length; j++) {
                boolean found = false;
                for (int k = j; k < m.length; k++) {
                    if (m[k][j] != 0) {
                        found = true;
                        for (int l = j; l < m.length; l++) {
                            long temp = m[j][l];
                            m[j][l] = m[k][l];
                            m[k][l] = temp;
                        }
                        break;
                    }
                }
                if (!found) {
                    break;
                }
                long reverse = IntegerUtils.reverse(m[j][j], cMod);
                for (int k = j + 1; k < m.length; k++) {
                    for (int l = m.length - 1; l >= j; l--) {
                        m[k][l] -= m[j][l] * m[k][j] % cMod * reverse;
                        m[k][l] %= cMod;
                    }
                }
            }
            long cResult = m.length % 2 == 0 ? 1 : -1;
            for (int j = 0; j < m.length; j++) {
                cResult *= m[j][j];
                cResult %= cMod;
            }
            if (cResult < 0) {
                cResult += cMod;
            }
            result = IntegerUtils.findCommon(result, mod, cResult, cMod);
            mod *= cMod;
        }
        return result;
    }
}

class IntegerUtils {
    public static long gcd(long a, long b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    public static int longCompare(long a, long b) {
        if (a < b)
            return -1;
        if (a > b)
            return 1;
        return 0;
    }

    public static long power(long base, long exponent, long mod) {
        if (base >= mod)
            base %= mod;
        if (exponent == 0)
            return 1 % mod;
        long result = power(base, exponent >> 1, mod);
        result = result * result % mod;
        if ((exponent & 1) != 0)
            result = result * base % mod;
        return result;
    }

    public static long reverse(long number, long module) {
        return power(number, module - 2, module);
    }

    public static boolean isPrime(long number) {
        if (number < 2)
            return false;
        for (long i = 2; i * i <= number; i++) {
            if (number % i == 0)
                return false;
        }
        return true;
    }

    public static long findCommon(long aRemainder, long aMod, long bRemainder, long bMod) {
        long modGCD = gcd(aMod, bMod);
        long gcdRemainder = aRemainder % modGCD;
        if (gcdRemainder != bRemainder % modGCD)
            return -1;
        aMod /= modGCD;
        aRemainder /= modGCD;
        bMod /= modGCD;
        bRemainder /= modGCD;
        long aReverse = BigInteger.valueOf(aMod).modInverse(BigInteger.valueOf(bMod)).longValue();
        long bReverse = BigInteger.valueOf(bMod).modInverse(BigInteger.valueOf(aMod)).longValue();
        long mod = aMod * bMod;
        long result = BigInteger.valueOf(bReverse * aRemainder % mod).multiply(BigInteger.valueOf(bMod)).add(
                BigInteger.valueOf(aReverse * bRemainder % mod).multiply(BigInteger.valueOf(aMod))
        ).mod(BigInteger.valueOf(mod)).longValue() * modGCD + gcdRemainder;
//		return (bReverse * aRemainder % mod * bMod + aReverse * bRemainder % mod * aMod) % mod * modGCD + gcdRemainder;
        return result;
    }

}

class InputReader {

    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;
    private SpaceCharFilter filter;

    public InputReader(InputStream stream) {
        this.stream = stream;
    }

    public int read() {
        if (numChars == -1)
            throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }

    public int readInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public boolean isSpaceChar(int c) {
        if (filter != null)
            return filter.isSpaceChar(c);
        return isWhitespace(c);
    }

    public static boolean isWhitespace(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public interface SpaceCharFilter {
        public boolean isSpaceChar(int ch);
    }
}

class OutputWriter {
    private final PrintWriter writer;

    public OutputWriter(OutputStream outputStream) {
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
    }

    public OutputWriter(Writer writer) {
        this.writer = new PrintWriter(writer);
    }

    public void print(Object...objects) {
        for (int i = 0; i < objects.length; i++) {
            if (i != 0)
                writer.print(' ');
            writer.print(objects[i]);
        }
    }

    public void printLine(Object...objects) {
        print(objects);
        writer.println();
    }

    public void close() {
        writer.close();
    }

}

class IOUtils {

    public static void readIntArrays(InputReader in, int[]... arrays) {
        for (int i = 0; i < arrays[0].length; i++) {
            for (int j = 0; j < arrays.length; j++)
                arrays[j][i] = in.readInt();
        }
    }

}

class ArrayUtils {
    private static int[] tempInt = new int[0];

    public static int[] createOrder(int size) {
        int[] order = new int[size];
        for (int i = 0; i < size; i++)
            order[i] = i;
        return order;
    }

    public static int[] sort(int[] array, IntComparator comparator) {
        return sort(array, 0, array.length, comparator);
    }

    public static int[] sort(int[] array, int from, int to, IntComparator comparator) {
        if (from == 0 && to == array.length)
            new IntArray(array).inPlaceSort(comparator);
        else
            new IntArray(array).subList(from, to).inPlaceSort(comparator);
        return array;
    }

    private static void ensureCapacityInt(int size) {
        if (tempInt.length >= size)
            return;
        size = Math.max(size, tempInt.length << 1);
        tempInt = new int[size];
    }

    public static int[] order(final int[] array) {
        return sort(createOrder(array.length), new IntComparator() {
            public int compare(int first, int second) {
                if (array[first] < array[second])
                    return -1;
                if (array[first] > array[second])
                    return 1;
                return 0;
            }
        });
    }

    public static void orderBy(int[] base, int[]... arrays) {
        int[] order = ArrayUtils.order(base);
        order(order, base);
        for (int[] array : arrays)
            order(order, array);
    }

    public static void order(int[] order, int[] array) {
        ensureCapacityInt(order.length);
        for (int i = 0; i < order.length; i++)
            tempInt[i] = array[order[i]];
        System.arraycopy(tempInt, 0, array, 0, array.length);
    }

}

class MiscUtils {

    public static void decreaseByOne(int[]...arrays) {
        for (int[] array : arrays) {
            for (int i = 0; i < array.length; i++)
                array[i]--;
        }
    }

}

interface IndependentSetSystem {
    public boolean join(int first, int second);

    public int get(int index);

    public static interface Listener {
        public void joined(int joinedRoot, int root);
    }
}

class RecursiveIndependentSetSystem implements IndependentSetSystem {
    private final int[] color;
    private final int[] rank;
    private int setCount;
    private Listener listener;

    public RecursiveIndependentSetSystem(int size) {
        color = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++)
            color[i] = i;
        setCount = size;
    }

    public RecursiveIndependentSetSystem(RecursiveIndependentSetSystem other) {
        color = other.color.clone();
        rank = other.rank.clone();
        setCount = other.setCount;
    }

    public boolean join(int first, int second) {
        first = get(first);
        second = get(second);
        if (first == second)
            return false;
        if (rank[first] < rank[second]) {
            int temp = first;
            first = second;
            second = temp;
        } else if (rank[first] == rank[second])
            rank[first]++;
        setCount--;
        color[second] = first;
        if (listener != null)
            listener.joined(second, first);
        return true;
    }

    public int get(int index) {
        if (color[index] == index)
            return index;
        return color[index] = get(color[index]);
    }

}

class Graph {
    public static final int REMOVED_BIT = 0;

    protected int vertexCount;
    protected int edgeCount;

    private int[] firstOutbound;
    private int[] firstInbound;

    private Edge[] edges;
    private int[] nextInbound;
    private int[] nextOutbound;
    private int[] from;
    private int[] to;
    private long[] weight;
    private long[] capacity;
    private int[] reverseEdge;
    private int[] flags;

    public Graph(int vertexCount) {
        this(vertexCount, vertexCount);
    }

    public Graph(int vertexCount, int edgeCapacity) {
        this.vertexCount = vertexCount;
        firstOutbound = new int[vertexCount];
        Arrays.fill(firstOutbound, -1);

        from = new int[edgeCapacity];
        to = new int[edgeCapacity];
        nextOutbound = new int[edgeCapacity];
        flags = new int[edgeCapacity];
    }

    public int addEdge(int fromID, int toID, long weight, long capacity, int reverseEdge) {
        ensureEdgeCapacity(edgeCount + 1);
        if (firstOutbound[fromID] != -1)
            nextOutbound[edgeCount] = firstOutbound[fromID];
        else
            nextOutbound[edgeCount] = -1;
        firstOutbound[fromID] = edgeCount;
        if (firstInbound != null) {
            if (firstInbound[toID] != -1)
                nextInbound[edgeCount] = firstInbound[toID];
            else
                nextInbound[edgeCount] = -1;
            firstInbound[toID] = edgeCount;
        }
        this.from[edgeCount] = fromID;
        this.to[edgeCount] = toID;
        if (capacity != 0) {
            if (this.capacity == null)
                this.capacity = new long[from.length];
            this.capacity[edgeCount] = capacity;
        }
        if (weight != 0) {
            if (this.weight == null)
                this.weight = new long[from.length];
            this.weight[edgeCount] = weight;
        }
        if (reverseEdge != -1) {
            if (this.reverseEdge == null) {
                this.reverseEdge = new int[from.length];
                Arrays.fill(this.reverseEdge, 0, edgeCount, -1);
            }
            this.reverseEdge[edgeCount] = reverseEdge;
        }
        if (edges != null)
            edges[edgeCount] = createEdge(edgeCount);
        return edgeCount++;
    }

    protected final GraphEdge createEdge(int id) {
        return new GraphEdge(id);
    }

    public final int addFlowWeightedEdge(int from, int to, long weight, long capacity) {
        if (capacity == 0) {
            return addEdge(from, to, weight, 0, -1);
        } else {
            int lastEdgeCount = edgeCount;
            addEdge(to, from, -weight, 0, lastEdgeCount + entriesPerEdge());
            return addEdge(from, to, weight, capacity, lastEdgeCount);
        }
    }

    protected int entriesPerEdge() {
        return 1;
    }

    public final int addWeightedEdge(int from, int to, long weight) {
        return addFlowWeightedEdge(from, to, weight, 0);
    }

    public final int addSimpleEdge(int from, int to) {
        return addWeightedEdge(from, to, 0);
    }

    protected final int edgeCapacity() {
        return from.length;
    }

    public final int firstOutbound(int vertex) {
        int id = firstOutbound[vertex];
        while (id != -1 && isRemoved(id))
            id = nextOutbound[id];
        return id;
    }

    public final int nextOutbound(int id) {
        id = nextOutbound[id];
        while (id != -1 && isRemoved(id))
            id = nextOutbound[id];
        return id;
    }

    public final int destination(int id) {
        return to[id];
    }

    public final boolean flag(int id, int bit) {
        return (flags[id] >> bit & 1) != 0;
    }

    public final boolean isRemoved(int id) {
        return flag(id, REMOVED_BIT);
    }

    protected void ensureEdgeCapacity(int size) {
        if (from.length < size) {
            int newSize = Math.max(size, 2 * from.length);
            if (edges != null)
                edges = resize(edges, newSize);
            from = resize(from, newSize);
            to = resize(to, newSize);
            nextOutbound = resize(nextOutbound, newSize);
            if (nextInbound != null)
                nextInbound = resize(nextInbound, newSize);
            if (weight != null)
                weight = resize(weight, newSize);
            if (capacity != null)
                capacity = resize(capacity, newSize);
            if (reverseEdge != null)
                reverseEdge = resize(reverseEdge, newSize);
            flags = resize(flags, newSize);
        }
    }

    protected final int[] resize(int[] array, int size) {
        int[] newArray = new int[size];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    private long[] resize(long[] array, int size) {
        long[] newArray = new long[size];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    private Edge[] resize(Edge[] array, int size) {
        Edge[] newArray = new Edge[size];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    protected class GraphEdge implements Edge {
        protected int id;

        protected GraphEdge(int id) {
            this.id = id;
        }

    }

}

class BidirectionalGraph extends Graph {
    public int[] transposedEdge;

    public BidirectionalGraph(int vertexCount) {
        this(vertexCount, vertexCount);
    }

    public BidirectionalGraph(int vertexCount, int edgeCapacity) {
        super(vertexCount, 2 * edgeCapacity);
        transposedEdge = new int[2 * edgeCapacity];
    }

    public static BidirectionalGraph createGraph(int vertexCount, int[] from, int[] to) {
        BidirectionalGraph graph = new BidirectionalGraph(vertexCount, from.length);
        for (int i = 0; i < from.length; i++)
            graph.addSimpleEdge(from[i], to[i]);
        return graph;
    }

    public int addEdge(int fromID, int toID, long weight, long capacity, int reverseEdge) {
        int lastEdgeCount = edgeCount;
        super.addEdge(fromID, toID, weight, capacity, reverseEdge);
        super.addEdge(toID, fromID, weight, capacity, reverseEdge == -1 ? -1 : reverseEdge + 1);
        this.transposedEdge[lastEdgeCount] = lastEdgeCount + 1;
        this.transposedEdge[lastEdgeCount + 1] = lastEdgeCount;
        return lastEdgeCount;
    }

    protected int entriesPerEdge() {
        return 2;
    }

    protected void ensureEdgeCapacity(int size) {
        if (size > edgeCapacity()) {
            super.ensureEdgeCapacity(size);
            transposedEdge = resize(transposedEdge, edgeCapacity());
        }
    }
}

class Rational implements Comparable<Rational> {

    public final long numerator;
    public final long denominator;

    public Rational(long numerator, long denominator) {
        if (denominator == 0)
            throw new IllegalArgumentException();
        long gcd = IntegerUtils.gcd(Math.abs(numerator), Math.abs(denominator));
        if (denominator > 0) {
            this.numerator = numerator / gcd;
            this.denominator = denominator / gcd;
        } else {
            this.numerator = -numerator / gcd;
            this.denominator = -denominator / gcd;
        }
    }

    public String toString() {
        return numerator + "/" + denominator;
    }

    public int compareTo(Rational other) {
        return IntegerUtils.longCompare(numerator * other.denominator, denominator * other.numerator);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rational rational = (Rational) o;

        if (denominator != rational.denominator) return false;
        if (numerator != rational.numerator) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (numerator ^ (numerator >>> 32));
        result = 31 * result + (int) (denominator ^ (denominator >>> 32));
        return result;
    }

}

class Indexer<K> extends EHashMap<K, Integer> {
    private int index = 0;

    public Integer get(Object key) {
        if (!containsKey(key))
            put((K) key, index++);
        return super.get(key);
    }
}

class EHashMap<E, V> extends AbstractMap<E, V> {
    private static final int[] shifts = new int[10];

    private int size;
    private HashEntry<E, V>[] data;
    private int capacity;
    private Set<Entry<E, V>> entrySet;

    static {
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 10; i++)
            shifts[i] = 1 + 3 * i + random.nextInt(3);
    }

    public EHashMap() {
        this(4);
    }

    private void setCapacity(int size) {
        capacity = Integer.highestOneBit(4 * size);
        //noinspection unchecked
        data = new HashEntry[capacity];
    }

    public EHashMap(int maxSize) {
        setCapacity(maxSize);
        entrySet = new AbstractSet<Entry<E, V>>() {
            @Override
            public Iterator<Entry<E, V>> iterator() {
                return new Iterator<Entry<E, V>>() {
                    private HashEntry<E, V> last = null;
                    private HashEntry<E, V> current = null;
                    private HashEntry<E, V> base = null;
                    private int lastIndex = -1;
                    private int index = -1;

                    public boolean hasNext() {
                        if (current == null) {
                            for (index++; index < capacity; index++) {
                                if (data[index] != null) {
                                    base = current = data[index];
                                    break;
                                }
                            }
                        }
                        return current != null;
                    }

                    public Entry<E, V> next() {
                        if (!hasNext())
                            throw new NoSuchElementException();
                        last = current;
                        lastIndex = index;
                        current = current.next;
                        if (base.next != last)
                            base = base.next;
                        return last;
                    }

                    public void remove() {
                        if (last == null)
                            throw new IllegalStateException();
                        size--;
                        if (base == last)
                            data[lastIndex] = last.next;
                        else
                            base.next = last.next;
                    }
                };
            }

            @Override
            public int size() {
                return size;
            }
        };
    }

    public EHashMap(Map<E, V> map) {
        this(map.size());
        putAll(map);
    }

    public Set<Entry<E, V>> entrySet() {
        return entrySet;
    }

    public void clear() {
        Arrays.fill(data, null);
        size = 0;
    }

    private int index(Object o) {
        return getHash(o.hashCode()) & (capacity - 1);
    }

    private int getHash(int h) {
        int result = h;
        for (int i : shifts)
            result ^= h >>> i;
        return result;
    }

    public V remove(Object o) {
        if (o == null)
            return null;
        int index = index(o);
        HashEntry<E, V> current = data[index];
        HashEntry<E, V> last = null;
        while (current != null) {
            if (current.key.equals(o)) {
                if (last == null)
                    data[index] = current.next;
                else
                    last.next = current.next;
                size--;
                return current.value;
            }
            last = current;
            current = current.next;
        }
        return null;
    }

    public V put(E e, V value) {
        if (e == null)
            return null;
        int index = index(e);
        HashEntry<E, V> current = data[index];
        if (current != null) {
            while (true) {
                if (current.key.equals(e)) {
                    V oldValue = current.value;
                    current.value = value;
                    return oldValue;
                }
                if (current.next == null)
                    break;
                current = current.next;
            }
        }
        if (current == null)
            data[index] = new HashEntry<E, V>(e, value);
        else
            current.next = new HashEntry<E, V>(e, value);
        size++;
        if (2 * size > capacity) {
            HashEntry<E, V>[] oldData = data;
            setCapacity(size);
            for (HashEntry<E, V> entry : oldData) {
                while (entry != null) {
                    HashEntry<E, V> next = entry.next;
                    index = index(entry.key);
                    entry.next = data[index];
                    data[index] = entry;
                    entry = next;
                }
            }
        }
        return null;
    }

    public V get(Object o) {
        if (o == null)
            return null;
        int index = index(o);
        HashEntry<E, V> current = data[index];
        while (current != null) {
            if (current.key.equals(o))
                return current.value;
            current = current.next;
        }
        return null;
    }

    public boolean containsKey(Object o) {
        if (o == null)
            return false;
        int index = index(o);
        HashEntry<E, V> current = data[index];
        while (current != null) {
            if (current.key.equals(o))
                return true;
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    private static class HashEntry<E, V> implements Entry<E, V> {
        private final E key;
        private V value;
        private HashEntry<E, V> next;

        private HashEntry(E key, V value) {
            this.key = key;
            this.value = value;
        }


        public E getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }
}

abstract class IntList extends IntCollection implements Comparable<IntList> {
    private static final int INSERTION_THRESHOLD = 16;

    public abstract int get(int index);
    public abstract void set(int index, int value);

    public IntIterator iterator() {
        return new IntIterator() {
            private int size = size();
            private int index = 0;

            public int value() throws NoSuchElementException {
                if (!isValid())
                    throw new NoSuchElementException();
                return get(index);
            }

            public void advance() throws NoSuchElementException {
                if (!isValid())
                    throw new NoSuchElementException();
                index++;
            }

            public boolean isValid() {
                return index < size;
            }
        };
    }

    public IntList subList(final int from, final int to) {
        return new SubList(from, to);
    }

    private void swap(int first, int second) {
        if (first == second)
            return;
        int temp = get(first);
        set(first, get(second));
        set(second, temp);
    }

    public IntSortedList inPlaceSort(IntComparator comparator) {
        quickSort(0, size() - 1, (Integer.bitCount(Integer.highestOneBit(size()) - 1) * 5) >> 1, comparator);
        return new IntSortedArray(this, comparator);
    }

    private void quickSort(int from, int to, int remaining, IntComparator comparator) {
        if (to - from < INSERTION_THRESHOLD) {
            insertionSort(from, to, comparator);
            return;
        }
        if (remaining == 0) {
            heapSort(from, to, comparator);
            return;
        }
        remaining--;
        int pivotIndex = (from + to) >> 1;
        int pivot = get(pivotIndex);
        swap(pivotIndex, to);
        int storeIndex = from;
        int equalIndex = to;
        for (int i = from; i < equalIndex; i++) {
            int value = comparator.compare(get(i), pivot);
            if (value < 0)
                swap(storeIndex++, i);
            else if (value == 0)
                swap(--equalIndex, i--);
        }
        quickSort(from, storeIndex - 1, remaining, comparator);
        for (int i = equalIndex; i <= to; i++)
            swap(storeIndex++, i);
        quickSort(storeIndex, to, remaining, comparator);
    }

    private void heapSort(int from, int to, IntComparator comparator) {
        for (int i = (to + from - 1) >> 1; i >= from; i--)
            siftDown(i, to, comparator, from);
        for (int i = to; i > from; i--) {
            swap(from, i);
            siftDown(from, i - 1, comparator, from);
        }
    }

    private void siftDown(int start, int end, IntComparator comparator, int delta) {
        int value = get(start);
        while (true) {
            int child = ((start - delta) << 1) + 1 + delta;
            if (child > end)
                return;
            int childValue = get(child);
            if (child + 1 <= end) {
                int otherValue = get(child + 1);
                if (comparator.compare(otherValue, childValue) > 0) {
                    child++;
                    childValue = otherValue;
                }
            }
            if (comparator.compare(value, childValue) >= 0)
                return;
            swap(start, child);
            start = child;
        }
    }

    private void insertionSort(int from, int to, IntComparator comparator) {
        for (int i = from + 1; i <= to; i++) {
            int value = get(i);
            for (int j = i - 1; j >= from; j--) {
                if (comparator.compare(get(j), value) <= 0)
                    break;
                swap(j, j + 1);
            }
        }
    }

    public int hashCode() {
        int hashCode = 1;
        for (IntIterator i = iterator(); i.isValid(); i.advance())
            hashCode = 31 * hashCode + i.value();
        return hashCode;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof IntList))
            return false;
        IntList list = (IntList)obj;
        if (list.size() != size())
            return false;
        IntIterator i = iterator();
        IntIterator j = list.iterator();
        while (i.isValid()) {
            if (i.value() != j.value())
                return false;
            i.advance();
            j.advance();
        }
        return true;
    }

    public int compareTo(IntList o) {
        IntIterator i = iterator();
        IntIterator j = o.iterator();
        while (true) {
            if (i.isValid()) {
                if (j.isValid()) {
                    if (i.value() != j.value()) {
                        if (i.value() < j.value())
                            return -1;
                        else
                            return 1;
                    }
                } else
                    return 1;
            } else {
                if (j.isValid())
                    return -1;
                else
                    return 0;
            }
            i.advance();
            j.advance();
        }
    }

    private class SubList extends IntList {
        private final int to;
        private final int from;
        private int size;

        public SubList(int from, int to) {
            this.to = to;
            this.from = from;
            size = to - from;
        }

        public int get(int index) {
            if (index < 0 || index >= size)
                throw new IndexOutOfBoundsException();
            return IntList.this.get(index + from);
        }

        public void set(int index, int value) {
            if (index < 0 || index >= size)
                throw new IndexOutOfBoundsException();
            IntList.this.set(index + from, value);
        }

        public int size() {
            return size;
        }

    }
}

abstract class IntCollection {
    public abstract IntIterator iterator();
    public abstract int size();

}

interface IntIterator {
    public int value() throws NoSuchElementException;
    /*
     * @throws NoSuchElementException only if iterator already invalid
     */
    public void advance() throws NoSuchElementException;
    public boolean isValid();
}

interface IntComparator {
    public static final IntComparator DEFAULT = new IntComparator() {
        public int compare(int first, int second) {
            if (first < second)
                return -1;
            if (first > second)
                return 1;
            return 0;
        }
    };

    public int compare(int first, int second);
}

abstract class IntSortedList extends IntList {
    protected final IntComparator comparator;

    protected IntSortedList(IntComparator comparator) {
        this.comparator = comparator;
    }

    public void set(int index, int value) {
        throw new UnsupportedOperationException();
    }

    public IntSortedList inPlaceSort(IntComparator comparator) {
        if (comparator == this.comparator)
            return this;
        throw new UnsupportedOperationException();
    }

    protected void ensureSorted() {
        int size = size();
        if (size == 0)
            return;
        int last = get(0);
        for (int i = 1; i < size; i++) {
            int current = get(i);
            if (comparator.compare(last, current) > 0)
                throw new IllegalArgumentException();
            last = current;
        }
    }

    public IntSortedList subList(final int from, final int to) {
        return new IntSortedList(comparator) {
            private int size = to - from;

            @Override
            public int get(int index) {
                if (index < 0 || index >= size)
                    throw new IndexOutOfBoundsException();
                return IntSortedList.this.get(index + from);
            }

            @Override
            public int size() {
                return size;
            }
        };
    }
}

class IntArray extends IntList {
    private final int[] array;

    public IntArray(int[] array) {
        this.array = array;
    }

    public IntArray(IntCollection collection) {
        array = new int[collection.size()];
        int i = 0;
        for (IntIterator iterator = collection.iterator(); iterator.isValid(); iterator.advance())
            array[i++] = iterator.value();
    }

    public int get(int index) {
        return array[index];
    }

    public void set(int index, int value) {
        array[index] = value;
    }

    public int size() {
        return array.length;
    }

}

interface Edge {
}

class IntSortedArray extends IntSortedList {
    private final int[] array;

    public IntSortedArray(int[] array) {
        this(array, IntComparator.DEFAULT);
    }

    public IntSortedArray(IntCollection collection) {
        this(collection, IntComparator.DEFAULT);
    }

    public IntSortedArray(int[] array, IntComparator comparator) {
        super(comparator);
        this.array = array;
        ensureSorted();
    }

    public IntSortedArray(IntCollection collection, IntComparator comparator) {
        super(comparator);
        array = new int[collection.size()];
        int i = 0;
        for (IntIterator iterator = collection.iterator(); iterator.isValid(); iterator.advance())
            array[i++] = iterator.value();
        ensureSorted();
    }

    public int get(int index) {
        return array[index];
    }

    public int size() {
        return array.length;
    }
}

