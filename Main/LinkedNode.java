/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author student
 */
public class LinkedNode {
    
    static int NodeCount;
    public LinkedNode nextNode;
    public LinkedNode prevNode;
    
    public String data;
    
    public LinkedNode(String _data){
        this.data = _data;
        NodeCount++;
    }
}
