/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.theoryassignment;

/**
 *
 * @author jumsh
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author jumsh
 */
public class AdjList {

    private ArrayList<Vertex> vertices;
    private ArrayList<Vertex> exploredAStarPath;
    private int costAStar;
    private int costGreedyBest;
    private int costUCS;
    private ArrayList<Vertex> exploredGreedyPath;
    private ArrayList<Vertex> exploredUCSPath;

    public AdjList(int vertices) {
        this.vertices = new ArrayList<>(vertices);
    }

    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
    }

    public String toString() {
        String s = "";
        for (Vertex v : vertices) {
            s += v.toString() + ": ";
            for (Edge n : v.getEdges()) {
                s += "\n" + n.toString() + " ";
            }
            s += "\n\n";
        }
        return s;
    }

    public void greedyBestFirstSearch(Vertex root, char goal) {
        PriorityQueue<Vertex> pq = new PriorityQueue<>(20, new VertexComparator());
        exploredGreedyPath = new ArrayList<>();
        pq.add(root);

        while (!pq.isEmpty()) {
            Vertex current = pq.poll();
            exploredGreedyPath.add(current);
            current.setVisited(true);
            if (current.getId() == goal) {
                System.out.println("Cost From Vertex " + root.getId() + " to Vertex " + goal
                        + " = " + current.getDistanceCost());
                this.costGreedyBest = current.getDistanceCost();
                System.out.println("Path From Start Vertex(" + root.getId() + ")"
                        + " Goal Vertex(" + goal + "): " + exploredGreedyPath);
                break;
            } else {
                for (Edge child : current.getEdges()) {
                    if (child.getTo().isVisited() == false) {
                        child.getTo().setVisited(true);
                        pq.add(child.getTo());
                        child.getTo().setDistanceCost(current.getDistanceCost() + child.getWeight());
                    }
                }
            }
        }

    }

    public void aStarSearch(Vertex root, char goal) {
        PriorityQueue<Vertex> pq = new PriorityQueue<>(20, new VertexComparatorAStar());
        exploredAStarPath = new ArrayList<>();
        root.setCostFunction(root.getDistanceCost() + root.getHeuristicValue());
        pq.add(root);
        while (!pq.isEmpty()) {
            Vertex current = pq.poll();
            exploredAStarPath.add(current);
            current.setVisited(true);
            if (current.getId() == goal) {
                System.out.println("Cost From Vertex " + root.getId() + " to Vertex " + goal
                        + " = " + current.getCostFunction());
                this.costAStar = current.getCostFunction();
                System.out.println("Path From Start Vertex(" + root.getId() + ")"
                        + " Goal Vertex(" + goal + "): " + exploredAStarPath);
                break;
            } else {
                for (Edge child : current.getEdges()) {
                    if (child.getTo().isVisited() == false) {
                        child.getTo().setDistanceCost(current.getDistanceCost() + child.getWeight());
                        child.getTo().setCostFunction(child.getTo().getDistanceCost() + child.getTo().getHeuristicValue());
                        child.getTo().setVisited(true);
                        pq.add(child.getTo());
                    } else {
                        int newCost = current.getDistanceCost() + child.getWeight() + child.getTo().getHeuristicValue();
                        int newDistanceCost = current.getDistanceCost() + child.getWeight();

                        if (newCost < child.getTo().getCostFunction()) {
                            child.getTo().setCostFunction(newCost);
                            child.getTo().setDistanceCost(newDistanceCost);
                            pq.remove(child.getTo());
                            exploredAStarPath.remove(child.getTo());
                            pq.add(child.getTo());
                            child.getTo().setVisited(false);
                        }
                    }
                }
            }
        }
    }

    public void uniformCostSearch(Vertex root, char goal) {
        PriorityQueue<Vertex> pq = new PriorityQueue<>(7, new VertexComparatorUCS());
        exploredUCSPath = new ArrayList<>();
        pq.add(root);

        while (!pq.isEmpty()) {
            Vertex current = pq.poll();
            exploredUCSPath.add(current);
            current.setVisited(true);
            if (current.getId() == goal) {
                System.out.println("Cost From Vertex " + root.getId() + " to Vertex " + goal
                        + " = " + current.getDistanceCost());
                this.costUCS = current.getDistanceCost();

                System.out.println("Path From Start Vertex(" + root.getId() + ")"
                        + " Goal Vertex(" + goal + "): " + this.exploredUCSPath);
                break;
            } else {
                for (Edge child : current.getEdges()) {
                    if (child.getTo().isVisited() == false) {
                        child.getTo().setDistanceCost(current.getDistanceCost() + child.getWeight());
                        child.getTo().setVisited(true);
                        pq.add(child.getTo());
                    } else {
                        int newCost = current.getDistanceCost() + child.getWeight();
                        if (newCost < child.getTo().getDistanceCost()) {
                            child.getTo().setDistanceCost(newCost);
                            exploredUCSPath.remove(child.getTo());
                            pq.remove(child.getTo());
                            pq.add(child.getTo());
                            child.getTo().setVisited(false);
                        }
                    }
                }
            }
        }
    }

    public ArrayList<Vertex> getExploredAStarPath() {
        return exploredAStarPath;
    }

    public void setExploredAStarPath(ArrayList<Vertex> exploredAStarPath) {
        this.exploredAStarPath = exploredAStarPath;
    }

    public int getCostAStar() {
        return costAStar;
    }

    public void setCostAStar(int costAStar) {
        this.costAStar = costAStar;
    }

    public int getCostGreedyBest() {
        return costGreedyBest;
    }

    public void setCostGreedyBest(int costGreedyBest) {
        this.costGreedyBest = costGreedyBest;
    }

    public int getCostUCS() {
        return costUCS;
    }

    public void setCostUCS(int costUCS) {
        this.costUCS = costUCS;
    }

    public ArrayList<Vertex> getExploredGreedyPath() {
        return exploredGreedyPath;
    }

    public void setExploredGreedyPath(ArrayList<Vertex> exploredGreedyPath) {
        this.exploredGreedyPath = exploredGreedyPath;
    }

    public ArrayList<Vertex> getExploredUCSPath() {
        return exploredUCSPath;
    }

    public void setExploredUCSPath(ArrayList<Vertex> exploredUCSPath) {
        this.exploredUCSPath = exploredUCSPath;
    }

}
