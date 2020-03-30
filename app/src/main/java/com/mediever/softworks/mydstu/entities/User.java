package com.mediever.softworks.mydstu.entities;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("name")
    private String name;
    @SerializedName("surname")
    private String surname;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("faculty")
    private String faculty;
    @SerializedName("group")
    private String group;
    @SerializedName("course")
    private String course;


    public String getName()     { return name; }
    public String getSurname()  { return surname; }
    public String getEmail()    { return email; }
    public String getPassword() { return password; }
    public String getFaculty()  { return faculty; }
    public String getGroup()    { return group; }
    public String getCourse()   { return course; }

    public void setName(String name)         { this.name = name; }
    public void setSurname(String surname)   { this.surname = surname; }
    public void setEmail(String email)       { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setFaculty(String faculty)   { this.faculty = faculty; }
    public void setGroup(String group)       { this.group = group; }
    public void setCourse(String course)     { this.course = course; }

}
