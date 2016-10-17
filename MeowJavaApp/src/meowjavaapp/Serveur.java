/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meowjavaapp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author frizz
 */
public class Serveur{
    
    private ServerSocket _servSocket;
    private ArrayList _clients = new ArrayList();
    
    public Serveur(int port) throws IOException
    {
        _servSocket = new ServerSocket(port);
    }
    
    public void Loop() throws IOException
    {
        int count = 0;
        System.out.println("Launching loop !");
        while(true)
        {
            Socket tmpsock = _servSocket.accept();
        
            System.out.println("New client : " + tmpsock.getInetAddress().toString());
            
            Client _tmp = new Client(tmpsock,this);
            _tmp.start();
            _clients.add(_tmp);
        }
        
    }
    
    public void Broadcast(String msg) throws IOException
    {
        for(int i = 0 ; i < _clients.size() ; i++)
        {
            Client tm = (Client)_clients.get(i);
            System.out.println(tm.GetSocket().getInetAddress().toString() + " : " + msg);
            tm.Send("["+tm.GetSocket().getInetAddress().toString() + "] : " + msg);
        }
    }
    
}
