/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author student
 */
public class ServerThread extends Thread {
    private Server server = null;
    private Socket socket = null;
    private int ID = -1;
    private DataInputStream in = null;
    private DataOutputStream out = null;
    
    public ServerThread(Server _server, Socket _socket){
        super();
        server = _server;
        socket = _socket;
        ID = socket.getPort();
    }
    
    public void Send(String msg){
        try{
            out.writeUTF(msg);
            out.flush();
        } catch(IOException ioe){
            System.out.println(ID + " Error sending: " + ioe.getMessage());
            server.Remove(ID);
            stop();
        }
    }
    
    public int getID(){
        return ID;
    }
    
    public void run(){
        System.out.println("Server thread " + ID + "running.");
        while(true){
            try{
                server.Handle(ID, in.readUTF());
            } catch(IOException ioe){
                System.out.println(ID + " Error reading: " + ioe.getMessage());
                server.Remove(ID);
                stop();
            }
        }
    }
    
    public void Open() throws IOException{
        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    }
    
    public void Close() throws IOException{
        if(socket != null){
            socket.close();
        }
        if(in != null){
            in.close();
        }
        if(out != null){
            out.close();
        }
    }
}
