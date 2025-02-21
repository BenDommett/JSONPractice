package JSON;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserName {
    private String userName;
    private int age;
    private double height;
    private String country;
    private String film;
    private int watches;

    public UserName(){
    }


    public String getuserName(){
        return userName;
    }

    public void setuserName(String UserName){
        this.userName = UserName;
    }

    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }

    public double getHeight(){
        return height;
    }

    public void setHeight(double height){
        this.height = height;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public String getFilm(){
        return film;
    }

    public void setFilm(String film){
        this.film = film;
    }

    public  int getWatches(){
        return watches;
    }

    public void setWatches(int whatches){
        this.watches = whatches;
    }

}
