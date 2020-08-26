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
public class VertexComparatorAStar implements Comparator<Vertex> {

    public VertexComparatorAStar() {
    }

    @Override
    public int compare(Vertex v1, Vertex v2) {
        if (v1.getCostFunction() > v2.getCostFunction()) 
                    return 1; 
                else if (v1.getCostFunction() < v2.getCostFunction()) 
                    return -1; 
                else if(v1.getCostFunction() == v2.getCostFunction())
                    return -1;
                                return 0; 
    }
    
}
