/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package save_image;

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
        this.url = "https://maps.googleapis.com/maps/api/staticmap?" + center.toString() + "&zoom=" + Integer.toString(zoom) + "&" + 
                size.toString() + "&maptype=" + maptype + "&key=" + key;
    }
    
    public String nombreArchivo(){
        if(this.elevation >= 2500)
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
     * @param center the center to set
     */
    public void setCenter(Center center) {
        this.center = center;
    }

    /**
     * @return the zoom
     */
    public int getZoom() {
        return zoom;
    }

    /**
     * @param zoom the zoom to set
     */
    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    /**
     * @return the size
     */
    public Size getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * @return the maptype
     */
    public String getMaptype() {
        return maptype;
    }

    /**
     * @param maptype the maptype to set
     */
    public void setMaptype(String maptype) {
        this.maptype = maptype;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public Map(Center center, int zoom, Size size, String maptype, String key) {
        this.center = center;
        this.zoom = zoom;
        this.size = size;
        this.maptype = maptype;
        this.key = key;
        makeURL();
    }

    /**
     * @return the elevation
     */
    public double getElevation() {
        return elevation;
    }

    /**
     * @param elevation the elevation to set
     */
    public void setElevation(double elevation) {
        this.elevation = elevation;
    }
    
}
