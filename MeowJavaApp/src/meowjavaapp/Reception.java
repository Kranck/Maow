/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meowjavaapp;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tchoalien
 */
public class Reception extends Thread
{
    private Socket _socket;
    private InputStream _is;
    public boolean _isActive = false;
    
    public Reception(Socket s) throws IOException
    {
        _socket = s;
        _is = s.getInputStream();
    }

    @Override
    public void run()
    {
        _isActive = true;
        while(_isActive)
        {
            byte b[] = new byte[100];
        
            try {            
                _is.read(b);
                System.out.println("Message reçu : " + new String(b));
            } catch (IOException ex) {
                Logger.getLogger(Reception.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
