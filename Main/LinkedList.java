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
public class LinkedList {
    
    static LinkedNode root;
    static LinkedNode temp;
    static LinkedNode current;
    
    public static void AddNewNode(String data){
        LinkedNode linkedNode = new LinkedNode(data);
        
        // if root has not been set, set root node
        if(root==null){
            root = linkedNode;
            root.nextNode = null;
            root.prevNode = null;
        
        } else {
            // else starting from the root node
            current = root;
            
            // check to see if we are at the end of the Linked List
            while(current.nextNode!=null){
                current = current.nextNode;
            }
            
            // add the data to the next null pointer in the list
            current.nextNode = linkedNode;
            // set the previous node
            linkedNode.prevNode = current;
            linkedNode.nextNode = null;
            current.nextNode = linkedNode;
        }
    }
    
    public static String ReturnStrListdata(){
        String myNodeString = "";
        
        current = root;
        
        while (current!=null){
            myNodeString += current.data + " <-> ";
            current = current.nextNode;
        }
        
        return myNodeString;
    }
    
}
