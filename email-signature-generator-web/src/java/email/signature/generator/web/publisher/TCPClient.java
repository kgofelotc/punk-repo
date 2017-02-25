/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email.signature.generator.web.publisher;

import java.io.*;
import java.net.*;

/**
 *
 * @author F4888723
 */
public class TCPClient {

    public static String send(String clientInput) throws Exception {
        
        String serverResponse;
        Socket clientSocket = new Socket("localhost", 6789);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        outToServer.writeBytes(clientInput + '\n');
        serverResponse = inFromServer.readLine();
        clientSocket.close();
        
        return serverResponse;
    }

}
