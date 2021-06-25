package com.example.mainactivity;

public class Speech {
    String speechID;
    String speechDay;
    String speechYear;
    String speechEra;
    String speechTitle;
    String speechPerson;
    String speechContent;
    String speechLink;

    public Speech(String speechID, String speechDay, String speechYear, String speechEra, String speechTitle, String speechPerson, String speechContent, String speechLink) {
        this.speechID = speechID;
        this.speechDay = speechDay;
        this.speechYear = speechYear;
        this.speechEra = speechEra;
        this.speechTitle = speechTitle;
        this.speechPerson = speechPerson;
        this.speechContent = speechContent;
        this.speechLink = speechLink;
    }

    public String getSpeechID() {
        return speechID;
    }

    public void setSpeechID(String speechID) {
        this.speechID = speechID;
    }

    public String getSpeechDay() {
        return speechDay;
    }

    public void setSpeechDay(String speechDay) {
        this.speechDay = speechDay;
    }

    public String getSpeechYear() {
        return speechYear;
    }

    public void setSpeechYear(String speechYear) {
        this.speechYear = speechYear;
    }

    public String getSpeechEra() {
        return speechEra;
    }

    public void setSpeechEra(String speechEra) {
        this.speechEra = speechEra;
    }

    public String getSpeechTitle() {
        return speechTitle;
    }

    public void setSpeechTitle(String speechTitle) {
        this.speechTitle = speechTitle;
    }

    public String getSpeechPerson() {
        return speechPerson;
    }

    public void setSpeechPerson(String speechPerson) {
        this.speechPerson = speechPerson;
    }

    public String getSpeechContent() {
        return speechContent;
    }

    public void setSpeechContent(String speechContent) {
        this.speechContent = speechContent;
    }

    public String getSpeechLink() {
        return speechLink;
    }

    public void setSpeechLink(String speechLink) {
        this.speechLink = speechLink;
    }
}
