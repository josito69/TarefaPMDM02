package com.example.tarefapmdm02;

public class Pedido {
    String item;
    int code,num;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Pedido(int code,String item, int num) {
        this.item = item;
        this.code = code;
        this.num = num;
    }
}
