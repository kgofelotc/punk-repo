/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email.signature.generator.console.test;

import java.io.*;
import java.net.*;

/**
 *
 * @author F4888723
 */
public class TCPClient {

    public static void main(String argv[]) throws Exception {
        
        String sentence;
        String modifiedSentence;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket("localhost", 6789);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        sentence = inFromUser.readLine();
        
//        sentence = "Tebogo#Kgofelo#Developer#73 Sisulu#Pretoria#011 297 4486#078 531 2360#www.punk.co.za";
        
        
        outToServer.writeBytes(sentence + '\n');
        modifiedSentence = inFromServer.readLine();
        System.out.println("FROM SERVER: " + modifiedSentence);
        clientSocket.close();
    }

}
