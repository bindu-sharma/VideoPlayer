package com.silverorange.videoplayer;
// Structure of the data to be represemted to the user
public class VedioItem {
    String vedio;
    String description;

    public VedioItem(String vedio, String description) {
        this.vedio = vedio;
        this.description = description;
    }

    public String getVedio() {
        return vedio;
    }

    public void setVedio(String vedio) {
        this.vedio = vedio;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

