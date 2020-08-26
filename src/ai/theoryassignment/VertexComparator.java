/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.theoryassignment;

import java.util.Comparator;

/**
 *
 * @author jumsh
 */
public class VertexComparator implements Comparator<Vertex> {

    public VertexComparator() {
    }

    @Override
    public int compare(Vertex v1, Vertex v2) {
        if (v1.getHeuristicValue() > v2.getHeuristicValue()) 
                    return 1; 
                else if (v1.getHeuristicValue() < v2.getHeuristicValue()) 
                    return -1; 
                else if(v1.getHeuristicValue() == v2.getHeuristicValue())
                    return -1;
                                return 0; 
    }
    
}
