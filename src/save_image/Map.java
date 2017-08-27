/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package save_image;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 *
 * @author Eduardo Straub
 */
public final class Map {
    private Center center;
    private int zoom;
    private Size size;
    private int scale;
    private String maptype;
    private String key;
    private double elevation;
    private byte[] image;

    public String makeURL() {
        return "https://maps.googleapis.com/maps/api/staticmap?" + "center=" + center.toString() + "&zoom=" + Integer.toString(zoom) +  
                size.toString() + "&scale=" + Integer.toString(scale) + "&maptype=" + maptype + "&key=" + key;
    }
    
    /**
     *
     * @return the name of the file.
     */
    public String nombreArchivo() {
        if(elevation >= 2500)
            return "m" + center.file_name() + ".png";
        else
            return "v" + center.file_name() + ".png";
    }

    /**
     *
     * @param center
     * @param zoom
     * @param size
     * @param maptype
     * @param key
     * @param scale
     */
    public Map(Center center, int zoom, Size size, String maptype, String key, int scale) {
        this.center = center;
        this.zoom = zoom;
        this.size = size;
        this.maptype = maptype;
        this.key = key;
        this.scale = scale;
    }

    /**
     *
     * @throws MalformedURLException
     * @throws IOException
     */
    public void setElevationMap() throws MalformedURLException, IOException{
        String elevationUrlText = "https://maps.googleapis.com/maps/api/elevation/json?locations=" + center.toString() + "&key=" + key;
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
     *
     * @throws IOException
     */
    public void Get_Image() throws IOException{
        URL url = new URL(makeURL());
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
        image = out.toByteArray();
    }
    
    /**
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void Save_Image() throws FileNotFoundException, IOException{
        String path_name = "C:\\Users\\eduar\\Documents\\NetBeansProjects\\Save_Image\\test\\images";
        Path path = Paths.get(path_name);
        String image_name = nombreArchivo();
        FileOutputStream fos;
        if (Files.exists(path)){
            fos = new FileOutputStream(path_name + "\\" + image_name);
            fos.write(image);
            fos.close();
        }
        else{
            path_name = "C:\\Users\\Eduardo Straub\\Documents\\NetBeansProjects\\Save_Image\\test\\images\\";
            fos = new FileOutputStream(path_name + image_name);
            fos.write(image);
            fos.close();
        }
    }

    /**
     * @param center the center to set
     */
    public void setCenter(Center center) {
        this.center = center;
    }
    
    /**
     * @throws IOException 
     */
    public void getGoogleData() throws IOException {
        setElevationMap();
        Get_Image();
    }
    
}
