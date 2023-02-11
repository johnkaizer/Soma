package com.example.angel1.Model;

public class PdfModel {

    public String tile,url;

    public PdfModel() {
    }

    public PdfModel(String tile, String url) {
        this.tile = tile;
        this.url = url;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
