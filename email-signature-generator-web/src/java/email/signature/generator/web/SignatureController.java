package email.signature.generator.web;

import email.signature.generator.web.publisher.TCPClient;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignatureController
 */
public class SignatureController extends HttpServlet {

    private String firstName;
    private String lastName;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String jobTitle = request.getParameter("jobTitle");
        String streetAddress = request.getParameter("streetAddress");
        String cityOrSuburb = request.getParameter("cityOrSuburb");
        String tel = request.getParameter("tel");
        String mobile = request.getParameter("mobile");
        String website = request.getParameter("website");

        if (!firstName.isEmpty() || !lastName.isEmpty() || !jobTitle.isEmpty() || !streetAddress.isEmpty()
                || !cityOrSuburb.isEmpty() || !tel.isEmpty() || !mobile.isEmpty() || !website.isEmpty()) {
            
            System.out.println("Sufficient input provided, lets generate the signature");
            
            try {

                TCPClient client = new TCPClient();

//                "Tebogo#Kgofelo#Developer#73 Sisulu#Pretoria#011 297 4486#078 531 2360#www.punk.co.za"; 
                String serverResponse = client.send(firstName + "#" + lastName + "#" + jobTitle + "#" + streetAddress + "#" + cityOrSuburb + "#" + tel + "#" + mobile + "#" + website);

                System.out.println("FROM SERVER : " + serverResponse);     
                
                request.setAttribute("signatureLocation", serverResponse);
                request.getRequestDispatcher("/success.jsp").forward(request, response);
                              
                return;

            } catch (Exception ex) {
                System.err.println("Handshake with the server was unsusessful! \nPlease make sure email-signature-generator-console is running!");
                Logger.getLogger(SignatureController.class.getName()).log(Level.SEVERE, null, ex);
                
                request.setAttribute("errorMessage", "Handshake with the server was unsusessful! \nPlease make sure email-signature-generator-console is running!");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            }
            
        } else {
            request.setAttribute("errorMessage", "All fields are required, Please try again!");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }
    }

}
