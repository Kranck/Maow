/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meowjavaapp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author tchoalien
 */
public class Client
{
    private Socket _socket;
    private Reception _reception;
    private OutputStream _os;
    
    public boolean _isActive = false;
    
    public Client(String address, int port) throws IOException
    {
        _socket = new Socket(address, port);
        _os = _socket.getOutputStream();
        _reception = new Reception(_socket);
        _reception.start();
        System.out.println("HEY");
        Loop();
        
    }
    
    public void Loop() throws IOException
    {
        _isActive= true;
        Scanner scanner = new Scanner(System.in);
        while(_isActive)
        {
            System.out.print("Ecrivez votre message :");
            String message = scanner.nextLine();
            _os.write(message.getBytes());
        }
    }
}
