/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author student
 */
public class Server implements Runnable{
    
    private ServerThread clients[] = new ServerThread[50];
    private ServerSocket serverSocket = null;
    private Thread thread = null;
    private int clientCount = 0;
    
    public Server(int port){
        try
        {
            System.out.println("Binding to port "+ port +", please wait...");
            serverSocket = new ServerSocket(port);
            System.out.println("Server started: " + serverSocket);
            StartServer();
        } 
        catch(IOException ioe)
        {
            System.out.println("Can not bind to port " + port + ": " + ioe.getMessage());
        }
    }
    
    public void run(){
        while(thread != null){
            try{
                System.out.println("Waiting for a client...");
                CreateThread(serverSocket.accept());
            } catch(IOException ioe){
                System.out.println("Server accept error: " + ioe);
                StopServer();
            }
        }
    }
    
    public void StartServer(){
        if(thread == null){
            thread = new Thread(this);
            thread.start();
        }
    }
    
    public void StopServer(){
        if(thread != null){
            thread.stop();
            thread = null;
        }
    }
    
    private int FindClient(int _ID){
        for (int i = 0; i < clientCount; i++){
            if(clients[i].getID() == _ID){
                return i;
            }
        }
        return -1;
    }
    
    public synchronized void Handle(int ID, String input){
        if (input.equals(".bye")){
            clients[FindClient(ID)].Send(".bye");
            Remove(ID);
        } else {
            for(int i = 0; i < clientCount; i++){
                clients[i].Send(input);
            }
        }
    }
    
    public synchronized void Remove(int _ID){
        int pos = FindClient(_ID);
        if(pos >= 0){
            ServerThread toTerminate = clients[pos];
            System.out.println("Removing client thread " + _ID + " at " + pos);
            if(pos < clientCount - 1){
                for(int i = pos + 1; i < clientCount; i++){
                    clients[i - 1] = clients[i];
                }
            }
            clientCount--;
            try{
                toTerminate.Close();
            } catch (IOException ioe){
                System.out.println("Error closing thread: " + ioe);
            }
            toTerminate.stop();
        }
    }
    
    private void CreateThread(Socket socket){
        if(clientCount < clients.length){
            System.out.println("Client accepted: " + socket);
            clients[clientCount] = new ServerThread(this, socket);
            try{
                clients[clientCount].Open();
                clients[clientCount].start();
                clientCount++;
            } catch(IOException ioe){
                System.out.println("Error opening thread: " + ioe);
            }
        }
        else{
            System.out.println("Client refused: maximum " + clients.length + " reached.");
        }
    }
    
    public static void main(String args[]){
        Server server;
        if(args.length != 1){
            server = new Server(4444);
        } else {
            server = new Server(Integer.parseInt(args[0]));
        }
    }
}
