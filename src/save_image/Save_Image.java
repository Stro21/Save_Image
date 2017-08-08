/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package save_image;

import java.io.IOException;


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
    public static void main(String[] args) throws IOException {

        Center centro = new Center (-33.2481, -70.05066);
        int zoom = 18;
        Size tamaño = new Size(500, 500);
        String googleKey = "AIzaSyBZJU70hMfKFnthdUFimKGLS17xkBntGWI";
        Map mapa = new Map(centro, zoom, tamaño, "satellite", googleKey); //Creates the map.
        mapa.Save_Image(); //Save image file in a folder.
    }
    
}
