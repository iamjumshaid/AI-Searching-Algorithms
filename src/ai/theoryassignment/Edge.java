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
public class Edge {
    private int weight;
    private Vertex to;

    public Edge(Vertex to, int weight) {
        this.weight = weight;
        this.to = to;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Vertex getTo() {
        return to;
    }

    public void setTo(Vertex to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "--" + weight + "-->" +  to ;
    }
    
}
