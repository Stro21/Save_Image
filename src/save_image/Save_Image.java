/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package save_image;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 *
 * @author Eduardo Straub
 */
public class Save_Image {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     * @throws java.net.MalformedURLException
     */
    public static void main(String[] args) throws FileNotFoundException, MalformedURLException, IOException {
        // TODO code application logic here
        Center centro = new Center (-33.2481, -70.05066);
        int zoom = 18;
        Size tamaño = new Size(500, 500);
        String googleKey = "AIzaSyBZJU70hMfKFnthdUFimKGLS17xkBntGWI";
        Map mapa = new Map(centro, zoom, tamaño, "satellite", googleKey);
        
        URL url = new URL(mapa.getUrl());
        ByteArrayOutputStream out;
        try (InputStream in = new BufferedInputStream(url.openStream())) {
            out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1!=(n=in.read(buf))) {
                out.write(buf, 0, n);
            }   
            out.close();
        }
        byte[] response = out.toByteArray();
        FileOutputStream fos;
        String path_name = "C:\\Users\\eduar\\Documents\\NetBeansProjects\\Save_Image\\test\\images";
        Path path = Paths.get(path_name);
        String image_name = mapa.nombreArchivo();
        if (Files.exists(path)){
            fos = new FileOutputStream(path_name + image_name);
            fos.write(response);
            fos.close();
        }
        else{
            fos = new FileOutputStream("C:\\Users\\Eduardo Straub\\Documents\\NetBeansProjects\\Save_Image\\test\\images\\" + image_name);
            fos.write(response);
            fos.close();
        }

    }
    
}
