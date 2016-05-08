package Server;
//Source relating to Chat:
//  Creating a simple Chat Client/Server Solution 
//  http://pirate.shu.edu/~wachsmut/Teaching/CSAS2214/Virtual/Lectures/chat-client-server.html


//CHAT RELATED ---------------------------
import Main.Interface2;
import java.net.*;
import java.io.*;
//----------------------------------------

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.SpringLayout;

public class Client2{

    private Socket socket = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;
    private ClientThread2 client = null;
    private String serverName = "localhost";
    private int serverPort = 4444;  
    Interface2 parent;

    public Client2(Interface2 p)
    {
        this.parent = p;
        GetParameters(); 
        Connect(serverName, serverPort);
    }

    public void Connect(String serverName, int serverPort)
    {
        System.out.println("Establishing connection. Please wait ...");
        try
        {
            socket = new Socket(serverName, serverPort);
            System.out.println("Connected: " + socket);
            Open();
        }
        catch (UnknownHostException uhe)
        {
            System.out.println("Host unknown: " + uhe.getMessage());
        }
        catch (IOException ioe)
        {
            System.out.println("Unexpected exception: " + ioe.getMessage());
        }
    }
    
    public void Send()
    {
        try
        {
            out.writeUTF(parent.GetWords());
            out.flush();
        }
        catch (IOException ioe)
        {
            System.out.println("Sending error: " + ioe.getMessage());
            Close();
        }
    }

    public void Handle(String msg)
    {
        if (msg.equals(".bye"))
        {
            System.out.println("Good bye. Press EXIT button to exit ...");
            Close();
        }
        else
        {
            System.out.println("Handle: " + msg);
            Println(msg);
        }
    }

    public void Open()
    {
        try
        {
            out = new DataOutputStream(socket.getOutputStream());
            client = new ClientThread2(this, socket);
        }
        catch (IOException ioe)
        {
            System.out.println("Error opening output stream: " + ioe);
        }
    }

    public void Close()
    {
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
        client.Close();
        client.stop();
    }

    void Println(String msg)
    {
        parent.Append(msg);
    }

    public void GetParameters()
    {
//        serverName = getParameter("host");
//        serverPort = Integer.parseInt(getParameter("port"));
        
        serverName = "localhost";
        serverPort = 4444;        
    }

}
