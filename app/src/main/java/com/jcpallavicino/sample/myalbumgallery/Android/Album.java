package com.jcpallavicino.sample.myalbumgallery.Android;

/**
 * Created by juan.pallavicino on 21/9/2017.
 */

public class Album {
    private String albumName;
    private String artistName;
    private String img;

    public Album(String albumName, String artistName, String smallImageUrl) {
        this.albumName = albumName;
        this.artistName = artistName;
        this.img = smallImageUrl;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setImagen(String imagen){
        this.img = imagen;
    }

    public String getImagen(){
        return this.img;
    }
}
