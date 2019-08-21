package com.manmohan.cards.data.model;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "user_table")
public class User {
  public String gender;
  @Embedded
  public Name name;
  @Embedded
  public Location location;
  @PrimaryKey
  @NonNull
  public String email;
  @Embedded
  public Login login;
  @Embedded
  public Dob dob;
  @Embedded
  public Registered registered;
  public String phone;
  public String cell;
  @Embedded
  public Id id;
  @Embedded
  public Picture picture;
  public String nat;
  public Boolean isDecline = false;
  public Boolean isAccept = false;

  public static class Coordinates {
    public String latitude;
    public String longitude;

  }

  public static class Dob {
    public String date;
    public String age;
  }
  public static class Id {
    public String name;
    public String value;
  }

  public static class Info {
    public String seed;
    public String results;
    public String page;
    public String version;
  }


  public static class Location {
    public String street;
    public String city;
    public String state;
    public String postcode;
    @Embedded
    public Coordinates coordinates;
    @Embedded
    public Timezone timezone;
  }

  public static class Login {
    public String uuid;
    public String username;
    public String password;
    public String salt;
    public String md5;
    public String sha1;
    public String sha256;
  }

  public static class Name {
    public String title;
    public String first;
    public String last;
  }

  public static class Picture {
    public String large;
    public String medium;
    public String thumbnail;
  }

  public static class Registered {
    @SerializedName("date")
    public String registeredDate;
    @SerializedName("age")
    public String registeredAge;
  }

  public static class Timezone {
    public String offset;
    public String description;

  }
}
