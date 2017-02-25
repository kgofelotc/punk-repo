/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email.signature.generator.console.listener;

import email.signature.generator.console.dto.SignatureDetails;
import email.signature.generator.console.util.SignatureGenerator;
import java.io.*;
import java.net.*;

/**
 *
 * @author F4888723
 */
public class TCPServer {

    public static void startServer() throws Exception {
        
        System.out.println("TCP Server Started! \nReady to accept client request...");
        String clientInput;
        String clientResponse = "";
        ServerSocket welcomeSocket = new ServerSocket(6789);

        while (true) {
            
            Socket socketConnection = welcomeSocket.accept();
            BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(socketConnection.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(socketConnection.getOutputStream());
            clientInput = inputFromClient.readLine();
            System.out.println("TCP Server Received : " + clientInput);
            
            String[] parts = clientInput.split("#");
            
            SignatureDetails signatureDetails = new SignatureDetails();
            
            signatureDetails.setFirstName(parts[0]);
            signatureDetails.setLastName(parts[1]);
            signatureDetails.setJobTitle(parts[2]);
            signatureDetails.setStreetAddress(parts[3]);
            signatureDetails.setCityOrSuburb(parts[4]);
            signatureDetails.setTel(parts[5]);
            signatureDetails.setMobile(parts[6]);
            signatureDetails.setWebsite(parts[7]);
                    
            SignatureGenerator generator = new SignatureGenerator();            
            clientResponse = generator.generateSignature(signatureDetails)  + '\n';   
            
            System.out.println("TCP server responding with : " + clientResponse);
            
            outToClient.writeBytes(clientResponse);
            
            System.out.println("TCP server done processing client request! \nReady to accepts client request...");
        }

    }

}
