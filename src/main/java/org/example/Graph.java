package org.example;

import java.util.*;

class GraphVertex {
    String name;
    char element;

    public GraphVertex(String name, char element) {
        this.name = name;
        this.element = element;
    }

    @Override
    public String toString() {
        return "GraphNode{" +
                "name='" + name + '\'' +
                ", element=" + element +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphVertex graphVertex = (GraphVertex) o;
        return element == graphVertex.element && Objects.equals(name, graphVertex.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, element);
    }
}


public class Graph{
    public static void findPath(GraphVertex startVertex, GraphVertex endVertex, Map<GraphVertex, Set<GraphVertex>> graph) {
        Set<GraphVertex> visited = new HashSet<>();
        Stack<GraphVertex> invertedPath = new Stack<>();
        List<String> letters = new Stack<>();
        visited.add(startVertex);
        invertedPath.push(startVertex);

        while(!invertedPath.isEmpty() && !invertedPath.peek().equals(endVertex)) {
            GraphVertex currentVertex = invertedPath.peek();
            GraphVertex tmp = currentVertex;

            for(GraphVertex vertex : graph.get(currentVertex)) {
                tmp = vertex;
                if(!visited.contains(vertex)) {
                    break;
                }
            }

            if(!visited.contains(tmp)) {
                visited.add(tmp);
                invertedPath.push(tmp);
            }
            else {
                invertedPath.pop();
            }
        }

        Stack<GraphVertex> path = new Stack<>();
        while(!invertedPath.isEmpty()) {
            path.push(invertedPath.pop());
        }
        System.out.print("Path ");
        while(!path.isEmpty()) {

            GraphVertex graphVertex = path.pop();
            System.out.print(graphVertex.element);
            if(Character.isUpperCase(graphVertex.element))
            {
                letters.add(String.valueOf(graphVertex.element));
            }
        }
        System.out.println("\nLetters " + String.join("", letters));
    }


    public static void main(String[] args) {
        Map<GraphVertex, Set<GraphVertex>> graph = new HashMap<>();
        Map<String, GraphVertex> vertices = new HashMap<>();
        Scanner s = new Scanner(System.in);

        List<String> lines = new ArrayList<>();
        while (s.hasNextLine())
        {
            String line = s.nextLine();
            lines.add(line);
        }

        GraphVertex startVertex = null;
        GraphVertex endVertex = null;

        String [][] matrix = new String[lines.size()][lines.get(0).length()];
        for(int i = 0; i < lines.size(); i++)
        {
            if(i == 4)
            {
                System.out.println();
            }
            for(int j = 0; j < lines.get(i).length(); j++)
            {
                if(lines.get(i).charAt(j) != ' ')
                {
                    GraphVertex graphVertex = new GraphVertex(i + "," + j, lines.get(i).charAt(j));
                    vertices.put(i + "," + j, graphVertex);
                    graph.put(graphVertex, new HashSet<>());

                    if(lines.get(i).charAt(j) == '>') {
                        startVertex = vertices.get(i + "," + j);
                    } else if(lines.get(i).charAt(j) == 's') {
                        endVertex = vertices.get(i + "," + j);
                    }

                    if(i > 0 && lines.get(i - 1).charAt(j) != ' ') {
                        GraphVertex vertex = vertices.get((i - 1) + "," + j);
                        graph.get(vertex).add(graphVertex);
                        graph.get(graphVertex).add(vertex);
                    }

                    if(j > 0 && lines.get(i).charAt(j - 1) != ' ') {
                        GraphVertex vertex = vertices.get(i + "," + (j - 1));
                        graph.get(vertex).add(graphVertex);
                        graph.get(graphVertex).add(vertex);
                    }
                }

            }
        }

        findPath(startVertex, endVertex, graph);
    }
}