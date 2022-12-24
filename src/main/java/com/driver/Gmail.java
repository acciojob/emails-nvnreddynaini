package com.driver;

import org.apache.commons.collections.list.AbstractLinkedList;

import java.util.ArrayList;
import java.util.Date;

import java.util.*;
class InboxMessage{
    private Date date;
    private String sender;
    private String message;

    public InboxMessage(Date date, String sender, String message) {
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
public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
    }

    LinkedList<InboxMessage> inbox=new LinkedList<>();
    LinkedList<InboxMessage> trash=new LinkedList<>();
    int current_mail_index = 0;
    int trashSize = 0;
    //HashMap<String,Object>
    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.

        if(getInboxSize() == getInboxCapacity()){
            InboxMessage oldMail  = inbox.getFirst();
            deleteMail(oldMail.getMessage());
        }
        //inbox.add(new InboxMessage(date, sender, message));
        inbox.add(new InboxMessage(date, sender, message));

    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        //inbox = inbox.next;
        int idx = -1;
        Date date = null;
        String sender = null;
        for (int i = 0; i < inbox.size(); i++) {
            if (inbox.get(i).getMessage() == message) {
                idx = i;
                date = inbox.get(i).getDate();
                sender = inbox.get(i).getSender();
                break;
            }
        }
        if(idx != -1){
            inbox.remove(idx);
            trash.add(new InboxMessage(date,sender,message));
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox

        if(getInboxSize() == 0) {
            return null;
        }
        else{
            return inbox.getLast().getMessage();
        }

    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(getInboxSize() == 0){
            return null;
        }else{
            return inbox.getFirst().getMessage();
        }
    }

    int count = 0;
    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        for (int i = 0; i < inbox.size(); i++) {
            if(inbox.get(i).getDate().compareTo(start) > 0 && inbox.get(i).getDate().compareTo(end) < 0){
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
