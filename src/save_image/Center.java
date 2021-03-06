/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package save_image;

import java.util.Locale;

/**
 *
 * @author Eduardo Straub
 */
public class Center {
    
    private double lat;
    private double lng;

    /**
     *
     * @param lat
     * @param lng
     */
    public Center(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }
    
    public Center (Center center) {
        this.lat = center.getLat();
        this.lng = center.getLng();
    }
    
    /**
     * @return the lat
     */
    public double getLat() {
        return lat;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * @return the lng
     */
    public double getLng() {
        return lng;
    }

    /**
     * @param lng the lng to set
     */
    public void setLng(double lng) {
        this.lng = lng;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return String.format(Locale.US, "%.5f", this.lat) + "," + String.format(Locale.US, "%.5f", this.lng);
    }

    public String file_name(){
        return (this.lat < 0 ? "W" : "E") + String.format(Locale.US, "%.5f", Math.abs(this.lat)) + "_" +
               (this.lng < 0 ? "S" : "N") + String.format(Locale.US, "%.5f", Math.abs(this.lng));

    }
}
