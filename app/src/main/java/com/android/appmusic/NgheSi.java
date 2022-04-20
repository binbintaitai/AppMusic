package com.android.appmusic;

public class NgheSi {

    private String Name;
    private int Img;
    public NgheSi(String name, int img) {
        Name = name;

        Img = img;

    }

    public int getImg() {
        return Img;
    }
    public void setImg(int img) {
        Img = img;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
}
