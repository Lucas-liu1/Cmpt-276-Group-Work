package ca.cmpt276.myapplication2.model;

public class ConfigDisplay {
    byte[] image;
    String name;

    public ConfigDisplay(byte[] image, String name) {
        this.image = image;
        this.name = name;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
