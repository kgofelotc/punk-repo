/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email.signature.generator.console;

import email.signature.generator.console.listener.TCPServer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author F4888723
 */
public class EmailSignatureGeneratorConsole {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // your logic here
                    TCPServer.startServer();
                } catch (Exception ex) {
                    Logger.getLogger(EmailSignatureGeneratorConsole.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}
