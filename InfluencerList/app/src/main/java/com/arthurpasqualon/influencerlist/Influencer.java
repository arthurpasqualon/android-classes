package com.arthurpasqualon.influencerlist;

public class Influencer {

    public int id;
    public String name;
    public String username;
    public String category;

    public Influencer(int id, String name, String username, String category) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.category = category;
    }

    public Influencer() {

    }

    @Override
    public String toString() {
        return  "Nome: "  + name + "\n" + "Username: " + username +"\n" + "Categoria: " + category;
    }
}
