package com.example.newsapp.data.api;

public class CallInstanceModel {

    private  NetworkService instance;
    private String url;

    public NetworkService getInstance(){
        return this.instance;
    }
    public void setInstance(NetworkService instance){
        this.instance = instance;
    }

}
