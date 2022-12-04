package ca.cmpt276.myapplication2.model;

public class ConfigDisplay {
    byte[] image_Display;
    String name_Display;

    public ConfigDisplay() {
        image_Display = null;
        name_Display = null;
    }

    public ConfigDisplay(byte[] image, String name) {
        this.image_Display = image;
        this.name_Display = name;
    }

    public void setImageDisplay(byte[] image) {
        this.image_Display = image;
    }

    public void setNameDisplay(String name) {
        this.name_Display = name;
    }

    public byte[] getImageDisplay() {
        return image_Display;
    }

    public String getNameDisplay() {
        return name_Display;
    }
}
