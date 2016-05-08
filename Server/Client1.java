/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Main.Interface1;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author student
 */
public class Client1 {
    
    private Socket socket = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;
    private ClientThread1 clientThread = null;
    private String serverName = "localhost";
    private int serverPort = 4444;
    
    static int numberOfWords = 50;
    static Message messageList[] = new Message[numberOfWords];
    static int currentWord = 0;
    
    Interface1 parent;    
    
    public Client1(Interface1 p){
        this.parent = p;
        GetParameters();
        messageList[currentWord] = new Message("START");
        Connect(serverName, serverPort);
    }
    
    public void Connect(String serverName, int serverPort){
        System.out.println("Establishing connection. Please wait...");
        try{
            socket = new Socket(serverName, serverPort);
            System.out.println("Connected: " + socket);
            Open();
        } catch(IOException ioe){
            System.out.println("Error connecting");
        }
    }
    
    public void Send(){
        try{
            out.writeUTF(parent.GetWords());
            out.flush();
        } catch(IOException ioe){
            System.out.println("Sending error: " + ioe.getMessage());
            Close();
        }
    }
    
    public void Handle(String msg){
        if(msg.equals(".bye")){
            System.out.println("Good bye. Press EXIT button to exit...");
            Close();
        } else {
            Println(msg);
        }
        
        currentWord++;
        messageList[currentWord] = new Message(msg);
        for(int i = 0; i < currentWord; i++){
            System.out.println("Handle Method: " + i + " - " + messageList[i].msg);
        }
    }
    
    public void Open(){
        try{
            out = new DataOutputStream(socket.getOutputStream());
            clientThread = new ClientThread1(this, socket);
        } catch(IOException ioe){
            System.out.println("Error opening output stream: " + ioe);
        }
    }
    
    public void Close(){
        try
        {
            if (out != null)
            {
                out.close();
            }
            if (socket != null)
            {
                socket.close();
            }
        }
        catch (IOException ioe)
        {
            System.out.println("Error closing ...");
        }
        clientThread.Close();
        clientThread.stop();
    }
    
    void Println(String msg){
        parent.Append(msg);
    }
    
    public void GetParameters(){
        serverName = "localhost";
        serverPort = 4444;
    }
}
