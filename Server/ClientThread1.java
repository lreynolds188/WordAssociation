/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author student
 */
public class ClientThread1 extends Thread{
    
    private Socket socket = null;
    private Client1 client = null;
    private DataInputStream in = null;
    
    public ClientThread1(Client1 _client, Socket _socket){
        client = _client;
        socket = _socket;
        Open();
        start();
    }
    
    public void Open(){
        try{
            in = new DataInputStream(socket.getInputStream());
        } catch(IOException ioe){
            System.out.println("Error getting input streams: " + ioe);
            client.Close();
        }
    }
    
    public void Close(){
        try{
            if (in != null)
            {
                in.close();
            }
        }
        catch (IOException ioe)
        {
            System.out.println("Error closing input stream: " + ioe);
        }
    }
    
    public void run(){
        while (true)
        {
            try
            {
                client.Handle(in.readUTF());
            }
            catch (IOException ioe)
            {
                System.out.println("Listening error: " + ioe.getMessage());
                //client1.stop();
                client.Close();
            }
        }
    }
}
