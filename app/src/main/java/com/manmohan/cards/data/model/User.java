package com.manmohan.cards.data.model;


public class User {
  public String gender;
  public Name name;
  public Location location;
  public String email;
  public Login login;
  public Dob dob;
  public Registered registered;
  public String phone;
  public String cell;
  public Id id;
  public Picture picture;
  public String nat;

  public static class Coordinates {
    public String latitude;
    public String longitude;

  }

  public static class Dob {
    public String date;
    public Integer age;
  }
  public static class Id {
    public String name;
    public String value;
  }

  public static class Info {
    public String seed;
    public Integer results;
    public Integer page;
    public String version;
  }


  public static class Location {
    public String street;
    public String city;
    public String state;
    public Integer postcode;
    public Coordinates coordinates;
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
    public String date;
    public Integer age;
  }

  public class Timezone {
    public String offset;
    public String description;

  }
}
