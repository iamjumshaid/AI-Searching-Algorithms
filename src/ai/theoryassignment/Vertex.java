/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.theoryassignment;

import java.util.ArrayList;

/**
 *
 * @author jumsh
 */
public class Vertex {

    private char id;
    private boolean visited;
    private ArrayList<Edge> edges;
    private int distanceCost;
    private int heuristicValue;
    private int costFunction;

    public Vertex(char id, int hValue) {
        this.id = id;
        this.edges = new ArrayList<>();
        visited = false;
        this.distanceCost = 0;
        this.heuristicValue = hValue;
        this.costFunction = 0;
    }
    
    


    public char getId() {
        return id;
    }

    public void setId(char id) {
        this.id = id;
    }

    
    @Override
    public String toString() {
        return "(" + id + ")"+"(" + this.heuristicValue + ")";
    }
    
  

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    boolean addEdge(Edge edge) {
        return edges.add(edge);
    }

    ArrayList<Edge> getEdges() {
        return edges;
    }

    public int getDistanceCost() {
        return distanceCost;
    }

    public void setDistanceCost(int distanceCost) {
        this.distanceCost = distanceCost;
    }

    public int getHeuristicValue() {
        return heuristicValue;
    }

    public void setHeuristicValue(int heuristicValue) {
        this.heuristicValue = heuristicValue;
    }

    public int getCostFunction() {
        return costFunction;
    }

    public void setCostFunction(int costFunction) {
        this.costFunction = costFunction;
    }

  
   
    
   
}
