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
        // These four declarations come from Excel file Bounds.xlsx
        final Center southWestArea = new Center(-33.698082423659, -70.1891172736674);
        final Center northEastArea = new Center(-33.677917576341, -70.1648827263326);
        final Double stepLat = 0.00252060591476108;
        final Double stepLng = 0.00302931841684493;

        Center center = new Center(southWestArea);
        int zoom = 18;
        Size tamaño = new Size(640, 640);
        int scale = 1;
        String googleKey = "AIzaSyBZJU70hMfKFnthdUFimKGLS17xkBntGWI";
        Map mapa = new Map(center, zoom, tamaño, "satellite", googleKey, scale); //Creates the map.
//        System.out.println("Init: Center " + center);
        while (center.getLat() < northEastArea.getLat()) {
            while (center.getLng() < northEastArea.getLng()) {
                mapa.getGoogleData();
                mapa.Save_Image(); //Save image file in a folder.
                center.setLng(center.getLng() + stepLng);
                mapa.setCenter(center);
//                System.out.println("Inner loop : Center " + center);
           }
            center.setLat(center.getLat() + stepLat);
            center.setLng(southWestArea.getLng());
            mapa.setCenter(center);
//            System.out.println("Outer loop: Center " + center);
        }
    }
    
}
