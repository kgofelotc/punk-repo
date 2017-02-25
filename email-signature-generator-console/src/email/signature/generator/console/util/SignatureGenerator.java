/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email.signature.generator.console.util;

import email.signature.generator.console.dto.SignatureDetails;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

/**
 *
 * @author F4888723
 */
public class SignatureGenerator {

    public String generateSignature(SignatureDetails details) {
                
        StringBuilder imageLocation = new StringBuilder();
        
        InputStream inputStream = null;
        
        try {
            inputStream = this.getClass().getClassLoader().getResourceAsStream("email/signature/generator/console/image/signature.png");      
            
            ImageInputStream imageInput = ImageIO.createImageInputStream(inputStream);
            BufferedImage bufferedImage = ImageIO.read(imageInput);

            Graphics graphics = bufferedImage.getGraphics();
            graphics.fillRect(0, 0, 200, 50);
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("Tahoma", Font.BOLD, 20));
            String nameAndSurname = details.getFirstName().concat(" ").concat(details.getLastName());
            graphics.drawString(nameAndSurname.toUpperCase(), 25, 25);
            graphics.setFont(new Font("Arial Black", Font.PLAIN, 18));
            graphics.drawString(details.getJobTitle().toUpperCase(), 35, 50);
            graphics.setFont(new Font("Arial Black", Font.PLAIN, 15));
            graphics.drawString(details.getStreetAddress().toUpperCase(), 15, 75);
            graphics.drawString(details.getCityOrSuburb().toUpperCase(), 35, 100);
            graphics.drawString(details.getTel().toUpperCase(), 45, 125);
            graphics.drawString(details.getMobile().toUpperCase(), 45, 150);
            graphics.drawString("http://".concat(details.getWebsite().toUpperCase()), 35, 175);
            
            createDirecory(nameAndSurname);
            
            String dir = "C:".concat(File.separator).concat(nameAndSurname).concat(File.separator);
            String extension = "-signature.png";
            
            imageLocation.append(dir).append(nameAndSurname).append(extension);
            
            ImageIO.write(bufferedImage, "png", new File(imageLocation.toString()));
            
            System.out.println("Image signature created at location : " + imageLocation.toString());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(SignatureGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SignatureGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(SignatureGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return "file:///".concat(imageLocation.toString());
    }
    
    
    public static void createDirecory(String directory){
        
        File file = new File("C:\\".concat(directory));
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
    }
    
    public static void main(String[] args) {
        
        SignatureGenerator generator = new SignatureGenerator();
        
        SignatureDetails signatureDetails = new SignatureDetails();
        signatureDetails.setFirstName("Tebogo");
        signatureDetails.setLastName("Kgofelo");
        signatureDetails.setJobTitle("Developer");
        signatureDetails.setStreetAddress("73 Sisulu");
        signatureDetails.setCityOrSuburb("Pretoria");
        signatureDetails.setTel("011 297 4486");
        signatureDetails.setMobile("078 531 2360");
        signatureDetails.setWebsite("www.punk.co.za");
        
        System.out.println(generator.generateSignature(signatureDetails));
        
    }

}
