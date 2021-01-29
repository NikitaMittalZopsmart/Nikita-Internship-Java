package com.zs.HobbiesProject.Model;

import java.util.Objects;

/**
 * This class is created for Users.
 */
public class User {
    String userID;
    String hobbyName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userID, user.userID) && Objects.equals(hobbyName, user.hobbyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, hobbyName);
    }


    public String getUid() {
        return userID;
    }

    public void setUid(String userID) {
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
