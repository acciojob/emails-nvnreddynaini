package com.driver;

import java.util.Date;

public class EmailTemplate {

    private Date date;
    private String sender;
    private String message;

    public EmailTemplate(Date date, String sender, String message) {
        this.date = date;
        this.sender = sender;
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }
}
