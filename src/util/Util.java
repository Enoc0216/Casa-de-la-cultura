/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;


import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author zahori
 */
public class Util {
    
    private String ruta= "";

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    public void creaCarpeta(String ruta){
        File folder= new File(ruta);
        folder.mkdirs();
        this.ruta = ruta;
    
    }
    
    public void colocaFoto(String ruta, JLabel lblFoto){
    Image preview = Toolkit.getDefaultToolkit().getImage(ruta);
    if(preview!=null){
    ImageIcon icon =new ImageIcon(
    preview.getScaledInstance(lblFoto.getWidth(),
                              lblFoto.getHeight(),
                               Image.SCALE_DEFAULT)
    );
    lblFoto.setIcon(icon);
                               
    }
    }
    public void recuperaImage(byte[] img){
    byte[] imagen = img;
    try{
    FileOutputStream fos =new FileOutputStream("C:\\Casa\\foto.jpg");
    fos.write(imagen);
    fos.close();
    }catch(Exception e){
      System.out.println("Erroor al recuperar la imagen!!");
      e.printStackTrace();
    }
    

    
    }
    
    /*  
    public byte[] estableceImagen(String ruta){
    File file = new File (ruta);
    byte[] bFile = new byte [(int) file.length()];
    try{
    FileInputStream fis =new FileInputStream(file);
    fis.read(bFile);
    fis.close();
    }catch(Exception e){
     
      e.printStackTrace();
    
    }
    return bFile;
    }*/
    
   public byte[] estableceImagen(String ruta) throws IOException, Base64DecodingException{
       ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
       BufferedImage img= ImageIO.read(new File(ruta));
       ImageIO.write(img,"jpg",baos);
       baos.flush();
       String base64String=Base64.encode(baos.toByteArray());
       baos.close();
       byte[]bytearray=Base64.decode(base64String);
       BufferedImage imag=ImageIO.read(new ByteArrayInputStream(bytearray));
        ImageIO.write(imag,"jpg",new File("C:\\Casa\\foto.jpg"));
       return bytearray;
       
   }
    
}
