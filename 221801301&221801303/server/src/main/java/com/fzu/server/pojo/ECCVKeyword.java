package com.fzu.server.pojo;

public class ECCVKeyword {
    int ID;
    int pID;
    String keyword;

    ECCVKeyword(int ID, int pID, String keyword) {
        this.ID = ID;
        this.pID = pID;
        this.keyword = keyword;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setpID(int pID) {
        this.pID = pID;
    }

    public int getpID() {
        return pID;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }
}
