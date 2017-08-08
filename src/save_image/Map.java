/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package save_image;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 *
 * @author Eduardo Straub
 */
public final class Map {
    private Center center;
    private int zoom;
    private Size size;
    private String maptype;
    private String key;
    private String url;
    private double elevation;

    public void makeURL() {
        this.url = "https://maps.googleapis.com/maps/api/staticmap?" + "center=" + center.toString() + "&zoom=" + Integer.toString(zoom) + "&" + 
                size.toString() + "&maptype=" + maptype + "&key=" + key;
    }
    
    /**
     *
     * @return the name of the file.
     */
    public String nombreArchivo() {
        if(this.getElevation() >= 2500)
            return "m" + center.file_name() + ".png";
        else
            return "v" + center.file_name() + ".png";
    }

    /**
     * @return the center
     */
    public Center getCenter() {
        return center;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param center
     * @param zoom
     * @param size
     * @param maptype
     * @param key
     * @throws IOException
     */
    public Map(Center center, int zoom, Size size, String maptype, String key) throws IOException {
        this.center = center;
        this.zoom = zoom;
        this.size = size;
        this.maptype = maptype;
        this.key = key;
        makeURL();
        setElevationMap();
    }

    /**
     *
     * @throws MalformedURLException
     * @throws IOException
     */
    public void setElevationMap() throws MalformedURLException, IOException{
        String elevationUrlText = "https://maps.googleapis.com/maps/api/elevation/json?locations=" + getCenter().toString() + "&key=" + this.key;
        URL elevationUrl = new URL(elevationUrlText);
        HttpURLConnection conn = (HttpURLConnection) elevationUrl.openConnection();
        conn.setRequestMethod("GET");
        String line, outputString = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        while ((line = reader.readLine()) != null) {
             outputString += line + "\n";
        }
        String keyElev = "\"elevation\" : ";
        int posStart = outputString.indexOf(keyElev) + keyElev.length();
        int posEnd = outputString.indexOf(",",posStart);
        elevation = Double.parseDouble(outputString.substring(posStart, posEnd));
    }
    
    /**
     * @return the elevation
     */
    public double getElevation() {
        return elevation;
    }
    
}
