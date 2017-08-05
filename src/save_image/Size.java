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
public class Size {
    
    private int height;
    private int width;

    public Size(int height, int width) {
        this.height = height;
        this.width = width;
    }
    
    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }
    
    @Override
    public String toString(){
        return "size=" + Integer.toString(this.height) + "x" + Integer.toString(width);
    }
}
