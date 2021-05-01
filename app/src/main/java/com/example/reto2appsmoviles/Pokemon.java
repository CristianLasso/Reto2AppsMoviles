package com.example.reto2appsmoviles;

public class Pokemon {

    private String name;
    private String img;
    private String type;
    private String attack;
    private String defense;
    private String speed;
    private String hp;

    public Pokemon(String name, String img, String type, String attack, String defense, String speed, String hp) {
        this.name = name;
        this.img = img;
        this.type = type;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.hp = hp;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getType() {
        return type;
    }

    public String getAttack() {
        return attack;
    }

    public String getDefense() {
        return defense;
    }

    public String getSpeed() {
        return speed;
    }

    public String getHp() {
        return hp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public void setDefense(String defense) {
        this.defense = defense;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

}
