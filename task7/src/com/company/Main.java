package com.company;

import com.company.Graph.AdjMatrixGraph;

public class Main {

    public static void main(String[] args) {
        AdjMatrixGraph graph = new AdjMatrixGraph(6);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(1, 5);
        graph.addEdge(2, 5);
        graph.addEdge(4, 5);
        graph.checkGraphToTree();
        AdjMatrixGraph graph1 = new AdjMatrixGraph(4);
        graph1.addEdge(0, 1);
        graph1.addEdge(0, 2);
        graph1.addEdge(0, 3);
        graph1.addEdge(1, 2);
        graph1.checkGraphToTree();
        AdjMatrixGraph graph2 = new AdjMatrixGraph(4);
        graph2.addEdge(0, 1);
        graph2.addEdge(0, 2);
        graph2.addEdge(0, 3);
        graph2.checkGraphToTree();
        AdjMatrixGraph graph3 = new AdjMatrixGraph(6);
        graph3.addEdge(0, 1);
        graph3.addEdge(1, 2);
        graph3.addEdge(1, 3);
        graph3.addEdge(1, 4);
        graph3.addEdge(1, 5);
        graph3.addEdge(2, 5);
        graph3.addEdge(3, 4);
        graph3.addEdge(4, 5);
        graph3.checkGraphToTree();
    }
}
