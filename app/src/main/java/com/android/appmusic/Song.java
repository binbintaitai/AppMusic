package com.android.appmusic;

public class Song {
    private String Name;
    private String Singer;
    private String Type;
    private int Img;
    private int FileMp3;

    public Song(String name, String singer, String type, int img, int fileMp3) {
        Name = name;
        Singer = singer;
        Type = type;
        Img = img;
        FileMp3 = fileMp3;
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

    public String getSinger() {
        return Singer;
    }

    public void setSinger(String singer) {
        Singer = singer;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getFileMp3() {
        return FileMp3;
    }

    public void setFileMp3(int fileMp3) {
        FileMp3 = fileMp3;
    }
}
