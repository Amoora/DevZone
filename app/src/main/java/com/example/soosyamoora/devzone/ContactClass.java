package com.example.soosyamoora.devzone;


public class ContactClass {

    Integer id;
    public String Name, PhoneNumber, Email, Github,  Hub, Area, Specialization, Sp, Pics;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getWords() {
        return Hub;
    }

    public void setWords(String words) {

        Hub = words;
    }
    public void setPhoneNumber(String phoneNumber){
        PhoneNumber = phoneNumber;
    }
    public String getPhoneNumber(){
        return PhoneNumber;
    }
    public void setEmail(String email){
        Email = email;
    }
    public String getEmail(){
        return Email;
    }

    public void setPics(String pics){
        Pics = pics;
    }
    public String getPics(){
        return Pics;
    }

    public void setGithub(String github){
        Github = github;
    }
    public String getGithub(){
        return Github;
    }

    public void setArea(String area){
        Area = area;
    }
    public String getArea(){
        return Area;
    }
    public void setPSp(String sp){
        Sp = sp;
    }
    public String getSp(){
        return Sp;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



}
