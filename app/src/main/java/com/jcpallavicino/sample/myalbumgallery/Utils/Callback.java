package com.jcpallavicino.sample.myalbumgallery.Utils;

public interface Callback {
    public void starting();
    public void completed(String res, int responseCode);
    public void completedWithErrors(Exception e);
    public void update();
}
