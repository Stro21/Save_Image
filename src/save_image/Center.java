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
public class Center {
    
    private double lat;
    private double lng;

    public Center(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
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
        return "center=" + Double.toString(this.lat) + "," + Double.toString(this.lng);
    }

    public String file_name(){
        return (this.lat < 0 ? "W" : "E") + String.format("%.5f", Math.abs(this.lat)) + (this.lng < 0 ? "S" : "N") + String.format("%.5f", Math.abs(this.lng));

    }
}
