package com.driver;

import org.apache.commons.collections.list.AbstractLinkedList;

import java.util.ArrayList;
import java.util.Date;

import java.util.*;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)

    ArrayList<EmailTemplate> inbox;
    ArrayList<EmailTemplate> trash;

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;

        inbox = new ArrayList<>();
        trash = new ArrayList<>();
    }


    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.

        if(inbox.size() == inboxCapacity){
            EmailTemplate emailTemplate = inbox.get(0);
            inbox.remove(0);
            trash.add(emailTemplate);
        }
        //add latest email
        EmailTemplate emailTemplate = new EmailTemplate(date, sender, message);
        inbox.add(emailTemplate);

    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        //inbox = inbox.next;
        EmailTemplate emailTemplate = null;
        for (int i = 0; i < inbox.size(); i++) {
            EmailTemplate emailTemplate1 = inbox.get(i);
            if (emailTemplate1.getMessage().equals(message)) {
                emailTemplate = emailTemplate1;
                break;
            }
        }
        if(emailTemplate!=null){
            inbox.remove(emailTemplate);
            trash.add(emailTemplate);
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox

        if(getInboxSize() == 0) {
            return null;
        }
        else{
            EmailTemplate emailTemplate = inbox.get(inbox.size()-1);
            return emailTemplate.getMessage();
        }

    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(getInboxSize() == 0){
            return null;
        }else{
            EmailTemplate emailTemplate = inbox.get(0);
            return emailTemplate.getMessage();
        }
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count = 0;

        for (int i = 0; i < inbox.size(); i++) {

            EmailTemplate emailTemplate = inbox.get(i);

            if(emailTemplate.getDate().compareTo(start) >= 0 && emailTemplate.getDate().compareTo(end) <= 0){
                count++;
            }
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
