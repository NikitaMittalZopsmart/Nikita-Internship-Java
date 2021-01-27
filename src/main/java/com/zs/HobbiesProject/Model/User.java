package main.java.com.zs.HobbiesProject.Model;

public class User {
    String userID;
    String hobbyName;

    public String getuID() {
        return userID;
    }
    public void setuID(String userID) {
        this.userID = userID;
    }
    public void setHobbyName(String hobbyName) {
        this.hobbyName = hobbyName;
    }
    public String getHobbyName() {
        return hobbyName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", name='" + hobbyName + '\'' +
                '}';
    }
}
