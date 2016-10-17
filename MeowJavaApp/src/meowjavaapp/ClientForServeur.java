/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meowjavaapp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author frizz
 */
public class ClientForServeur extends Thread{
    
    protected Socket _clientSocket;
    protected Serveur _serveur;
    
    public boolean _active = false;
    
    public ClientForServeur(Socket s , Serveur serv)
    {
        _clientSocket = s;
        _serveur = serv;
    }
    
    public void Send(String msg) throws IOException
    {
        OutputStream is = this._clientSocket.getOutputStream();
        is.write(msg.getBytes());
    }

    @Override
    public void run() {
        this._active = true;
        while(this._active)
        {
            InputStream is = null;
            try {
                is = this._clientSocket.getInputStream();
                byte[] b = new byte[100];
                is.read(b);
                
                String mesg = new String(b);
                
                
                
                if(mesg.compareTo("Exit") == 96)
                {
                    this._clientSocket.close();
                    this._active = false;
                    break;
                }
                
                
                
                this._serveur.Broadcast(new String(b));
            } catch (IOException ex) {
                Logger.getLogger(ClientForServeur.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public Socket GetSocket()
    {
        return this._clientSocket;
    }
    
    
    
}
