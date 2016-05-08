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
public class BinaryTree {
    
    public static BinaryNode root;
    public static String strPreOrder = "";
    public static String strInOrder = "";
    public static String strPostOrder = "";
    public static boolean firstRun = true;
    
    public static void AddNewNode(String name) {

        BinaryNode ndeNewNode = new BinaryNode(name);

        // if there is no root node make this node the root.
        if (root == null) {
            root = ndeNewNode;
        // if there is a root node set the root to the focus node.
        } else {
            BinaryNode ndeFocus = root;
            BinaryNode ndeParent;

            while (true) {
                ndeParent = ndeFocus;

                if (name.compareToIgnoreCase(ndeFocus.name) < 0){
                    ndeFocus = ndeFocus.ndeLeftChild;

                    if (ndeFocus == null) {
                        ndeParent.ndeLeftChild = ndeNewNode;
                        return;
                    }
                } else if (name.compareToIgnoreCase(ndeFocus.name) > 0){
                    ndeFocus = ndeFocus.ndeRightChild;

                    if (ndeFocus == null) {
                        ndeParent.ndeRightChild = ndeNewNode;
                        return;
                    }
                } else {
                    if(ndeFocus.ndeLeftChild == null){
                        
                    }
                }
            }
        }
    }
    
    public static void ClearBinaryString(){
        strPreOrder = "";
        strInOrder = "";
        strPostOrder = "";
    }
    
    public static String CreateBinaryString(int _intBinaryType){
        //BinaryData
        String myBinaryData;
        ClearBinaryString();
        if (_intBinaryType == 1) {
            myBinaryData = "Pre-Order: ";
            firstRun = true;
            PreOrderTraverse(root);
            return myBinaryData + strPreOrder;
        } else if (_intBinaryType == 2) {
            myBinaryData = "In-Order: ";
            firstRun = true;
            InOrderTraverse(root);
            return myBinaryData + strInOrder;
        } else {
            myBinaryData = "Post-Order: ";
            firstRun = true;
            PostOrderTraverse(root);
            return myBinaryData + strPostOrder;
        }
    }
    
    public static void PreOrderTraverse(BinaryNode focusNode) {
        firstRun = true;
        if (focusNode != null) {
            if (focusNode != root) {
                strPreOrder += ", ";
            }
            strPreOrder += focusNode.name;
            PreOrderTraverse(focusNode.ndeLeftChild);
            PreOrderTraverse(focusNode.ndeRightChild);
        }
    }

    public static void InOrderTraverse(BinaryNode focusNode) {
        if (focusNode != null) {
            InOrderTraverse(focusNode.ndeLeftChild);
            if (!firstRun) {
                strInOrder += ", ";
            }
            firstRun = false;
            strInOrder += focusNode.name;
            InOrderTraverse(focusNode.ndeRightChild);
            
        }
    }

    public static void PostOrderTraverse(BinaryNode focusNode) {
        firstRun = true;
        if (focusNode != null) {
            PostOrderTraverse(focusNode.ndeLeftChild);
            PostOrderTraverse(focusNode.ndeRightChild);
            strPostOrder += focusNode.name;
            if (focusNode != root) {
                strPostOrder += ", ";
            }
        }
    }
    
}
