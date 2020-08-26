/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.theoryassignment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author jumsh
 */
public class AITheoryAssignment {

    /**
     * @param args the command line arguments
     */
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        studentIntroduction();
        while (true) {
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    directedGraph();
                    break;
                case 2:
                    unidrectedGraph();
                    break;
                case 3:
                    System.out.println("Program ended.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, re-enter of press 3 to exit");
            }

        }
    }

    public static void studentIntroduction() {
        System.out.println("-->AI Lab Theory - Assignment ");
        System.out.println("Student: Jumshaid Khan");
        System.out.println("Registration: FA17-BSE-004");
        System.out.println("SE-6A");
        System.out.println("Submitted to: Dr. Faisal Azam");
        System.out.println("(Please expand output screen full to view all results)");
        System.out.println("------------------------------------");
        toContinue();
    }

    public static int getUserChoice() {
        System.out.println("\nSelect From Below:"
                + "\n1:Directed Graph"
                + "\n2:Undirected Graph"
                + "\n3:End program");
        System.out.println("\nEnter your choice:");
        int ch = input.nextInt();
        return ch;
    }

    public static void directedGraph() {
        System.out.println("Making a Directed Graph");
        System.out.print("Enter Number of Vertices: ");
        int vertices = input.nextInt();
        char label;
        int hValue;
        AdjList graph = new AdjList(vertices);
        ArrayList<Vertex> listOfVertices = new ArrayList<>();
        System.out.println("Creating Vertices:");
        for (int i = 0; i < vertices; i++) {
            System.out.print("Enter Vertix Label: ");
            label = input.next().charAt(0);
            System.out.print("Enter Heurisitc Value for Vertix: ");
            hValue = input.nextInt();
            listOfVertices.add(new Vertex(label, hValue));
        }
        vertexWithIndex(listOfVertices);
        toContinue();
        System.out.println("\nAdding Edges: ");
        while (true) {
            System.out.print("Enter index of Vertix From: ");
            int index = input.nextInt();
            Vertex from = listOfVertices.get(index);
            System.out.print("Enter index of Vertix To: ");
            index = input.nextInt();
            Vertex to = listOfVertices.get(index);
            System.out.print("Enter edge cost from " + from.getId() + " to " + to.getId() + ": ");
            int edgeCost = input.nextInt();
            from.addEdge(new Edge(to, edgeCost));
            System.out.println("\nEnter 1 to keep adding edges or press 0 to finish adding:");
            int choice = input.nextInt();
            if (choice == 0) {
                break;
            }
        }
        System.out.println("Edges have been added..");
        toContinue();
        System.out.println("Your Graph Representation:");
        for (Vertex v : listOfVertices) {
            graph.addVertex(v);
        }
        System.out.println(graph.toString());
        graphOperations(graph);
    }

    public static void unidrectedGraph() {
        System.out.println("Making an Undirected Graph (enter edge just once)");
        System.out.print("Enter Number of Vertices: ");
        int vertices = input.nextInt();
        char label;
        int hValue;
        AdjList graph = new AdjList(vertices);
        ArrayList<Vertex> listOfVertices = new ArrayList<>();
        System.out.println("Creating Vertices:");
        for (int i = 0; i < vertices; i++) {
            System.out.print("Enter Vertix Label: ");
            label = input.next().charAt(0);
            System.out.print("Enter Heurisitc Value for Vertix: ");
            hValue = input.nextInt();
            listOfVertices.add(new Vertex(label, hValue));
        }

        System.out.println("\nYour added vertices along with their index:");
        vertexWithIndex(listOfVertices);
        toContinue();
        System.out.println("\nAdding Edges: ");
        while (true) {
            System.out.print("Enter index of Vertix From: ");
            int index = input.nextInt();
            Vertex from = listOfVertices.get(index);
            System.out.print("Enter index of Vertix To: ");
            index = input.nextInt();
            Vertex to = listOfVertices.get(index);
            System.out.print("Enter edge cost from " + from.getId() + " to " + to.getId() + ": ");
            int edgeCost = input.nextInt();
            from.addEdge(new Edge(to, edgeCost));
            to.addEdge(new Edge(from, edgeCost));
            System.out.println("\nEnter 1 to keep adding edges or press 0 to finish adding:");
            int choice = input.nextInt();
            if (choice == 0) {
                break;
            }
        }
        System.out.println("Edges have been added..");
        toContinue();
        System.out.println("Your Graph Representation:");
        for (Vertex v : listOfVertices) {
            graph.addVertex(v);
        }
        System.out.println(graph.toString());
        graphOperations(graph);
    }

    public static void graphOperations(AdjList graph) {
        toContinue();
        vertexWithIndex(graph.getVertices());
        System.out.println("Enter Starting Vertex Index: ");
        int startIndex = input.nextInt();
        Vertex startNode = graph.getVertices().get(startIndex);
        System.out.println("Enter Goal Vertex Index: ");
        int goalIndex = input.nextInt();
        char goal = graph.getVertices().get(goalIndex).getId();
        System.out.println("Results From A* Search:");
        graph.aStarSearch(startNode, goal);
        resetValues(graph);
        System.out.println("\nResults From Greedy Best Search:");
        graph.greedyBestFirstSearch(startNode, goal);
        resetValues(graph);
        
        
        System.out.println("\nResults From Uniform Cost Search:");
        graph.uniformCostSearch(startNode, goal);
        resetValues(graph);
        
        toContinue();
        System.out.println("\nComparison From Start to Goal Vertex:");
        System.out.println("A* Total Cost: " + graph.getCostAStar());
        System.out.println("Path A*: " + graph.getExploredAStarPath());
        System.out.println("Greedy Best Search Total Cost: " + graph.getCostGreedyBest());
        System.out.println("Path Greedy Best Search: " + graph.getExploredGreedyPath());
        System.out.println("Uniform Cost Search Total Cost: " + graph.getCostUCS());
        System.out.println("Path Uniform Cost Search: " + graph.getExploredUCSPath());

    }

    public static void vertexWithIndex(ArrayList<Vertex> listOfVertices) {
        int j = 0;
        for (Vertex v : listOfVertices) {
            System.out.println("Index: " + j + "\tVertix: " + v.getId());
            j++;
        }
    }

    public static void resetValues(AdjList graph) {
        for (Vertex v : graph.getVertices()) {
            v.setDistanceCost(0);
            v.setCostFunction(0);
            v.setVisited(false);
        }
    }

    public static void toContinue() {

        System.out.println("\nPress enter continue..");
        try {
            System.in.read();
        } catch (IOException e) {
            System.out.print("Program restarting due to input error.");
        }
    }
    

}
