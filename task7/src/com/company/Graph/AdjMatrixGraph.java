package com.company.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Реализация графа на основе матрицы смежности
 */
public class AdjMatrixGraph implements Graph {

    private boolean[][] adjMatrix = null;
    private int vCount = 0;
    private int eCount = 0;

    /**
     * Конструктор
     * @param vertexCount Кол-во вершин графа (может увеличиваться при добавлении ребер)
     */
    public AdjMatrixGraph(int vertexCount) {
        adjMatrix = new boolean[vertexCount][vertexCount];
        vCount = vertexCount;
    }

    /**
     * Конструктор без парметров
     * (лучше не использовать, т.к. при добавлении вершин каждый раз пересоздается матрица)
     */
    public AdjMatrixGraph() {
        this(0);
    }

    @Override
    public int vertexCount() {
        return vCount;
    }

    @Override
    public int edgeCount() {
        return eCount;
    }

    @Override
    public void addEdge(int v1, int v2) {
        int maxV = Math.max(v1, v2);
        if (maxV >= vertexCount()) {
            adjMatrix = Arrays.copyOf(adjMatrix, maxV + 1);
            for (int i = 0; i <= maxV; i++) {
                adjMatrix[i] = i < vCount ? Arrays.copyOf(adjMatrix[i], maxV + 1) : new boolean[maxV + 1];
            }
            vCount = maxV + 1;
        }
        if (!adjMatrix[v1][v2]) {
            adjMatrix[v1][v2] = true;
            adjMatrix[v2][v1] = true;
            eCount++;
        }
    }

    @Override
    public void removeEdge(int v1, int v2) {
        if (adjMatrix[v1][v2]) {
            adjMatrix[v1][v2] = false;
            adjMatrix[v2][v1] = false;
            eCount--;
        }
    }

    @Override
    public Iterable<Integer> adjacencies(int v) {
        return new Iterable<Integer>() {
            Integer nextAdj = null;

            @Override
            public Iterator<Integer> iterator() {
                for (int i = 0; i < vCount; i++) {
                    if (adjMatrix[v][i]) {
                        nextAdj = i;
                        break;
                    }
                }

                return new Iterator<Integer>() {
                    @Override
                    public boolean hasNext() {
                        return nextAdj != null;
                    }

                    @Override
                    public Integer next() {
                        Integer result = nextAdj;
                        nextAdj = null;
                        for (int i = result + 1; i < vCount; i++) {
                            if (adjMatrix[v][i]) {
                                nextAdj = i;
                                break;
                            }
                        }
                        return result;
                    }
                };
            }
        };
    }

    public void checkGraphToTree() {
        final int[] numOfCycle = {0};
        int[] vertexInCycles = new int[vCount];

        class Inner {
            void visit(List<Integer> Path, int vertex) {
                List<Integer> newPath = new ArrayList<>(Path);
                newPath.add(vertex);
                for(int i = 0; i < newPath.size() - 2; i++) {
                    if(newPath.get(i) == vertex) {
                        List<Integer> cycle = newPath.subList(i, newPath.size() - 1);
                        for(int v : cycle) {
                            vertexInCycles[v]++;
                        }
                        numOfCycle[0]++;
                        return;
                    }
                }
                for(Integer v : adjacencies(vertex)) {
                    if(!v.equals(newPath.get(newPath.size() - 2))) visit(newPath, v);
                }
            }
        }
        List<Integer> Path = new ArrayList<>();
        Path.add(0);
        Inner inner = new Inner();
        for(Integer v : adjacencies(0)) {
            inner.visit(Path, v);
        }

        if(numOfCycle[0] == 0) {
            System.out.println("Граф является деревом");
            return;
        }
        int ans = -1;
        for (int i = 0; i < vertexInCycles.length; i++) {
            if (vertexInCycles[i] == numOfCycle[0]) {
                for(int j = 0; j < vCount; j++) {
                    int edges = 0;
                    for(int h = 0; h < vCount; h++) {
                        if(adjMatrix[j][h]) edges++;
                    }
                    if(edges == 1 && adjMatrix[j][i]) {
                        break;
                    }
                    if(j == vCount - 1) ans = i;
                }
            }
            if(ans != -1) {
                System.out.printf("Необходимо удалить вершину №%d\n", i);
                return;
            }
        }
        System.out.println("Из графа невозможно получить дерево удалением одной вершины");
    }
}
