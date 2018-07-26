package com.example.soosyamoora.devzone;


public class ResourcesClass {

    Integer id;
    public String Title, Detail, Type, Points, Author;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getWords() {
        return Author;
    }

     public void setWords(String words) {

        Author = words;
    }
    public void setDetail(String detail){
        Detail = detail;
    }
    public String getDetail(){
        return Detail;
    }
    public void setType(String type){
        Type = type;
    }
    public String getType(){
        return Type;
    }
    public void setPoints(String points){
        Points = points;
    }
    public String getPoints(){
        return Points;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



}
