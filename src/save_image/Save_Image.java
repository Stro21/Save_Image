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
        final Center southWestArea = new Center(-33.25456025, -70.05838476); 
        final Center northEastArea = new Center(-33.24648494, -70.04872881);
        final Double stepLat = 0.001615063;
        final Double stepLng = 0.00193119;
        Center center = new Center(southWestArea);
        int zoom = 18;
        Size tamaño = new Size(500, 500);
        String googleKey = "AIzaSyBZJU70hMfKFnthdUFimKGLS17xkBntGWI";
        Map mapa = new Map(center, zoom, tamaño, "satellite", googleKey); //Creates the map.
        System.out.println("Init: Center " + center);
        while (center.getLat() < northEastArea.getLat()) {
            while (center.getLng() < northEastArea.getLng()) {
                mapa.getGoogleData();
                mapa.Save_Image(); //Save image file in a folder.
                center.setLng(center.getLng() + stepLng);
                mapa.setCenter(center);
                System.out.println("Inner loop : Center " + center);
           }
            center.setLat(center.getLat() + stepLat);
            center.setLng(southWestArea.getLng());
            mapa.setCenter(center);
            System.out.println("Outer loop: Center " + center);
        }
    }
    
}
