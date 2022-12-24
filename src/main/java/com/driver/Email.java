package com.driver;
public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        int flag1 = 0, flag2 = 0, flag3 = 0, flag4 = 0,flag5=0;
        if(oldPassword == getPassword()){
            if(newPassword.length() >= 8){
                flag1 = 1;
            }
            for(int i = 0; i < newPassword.length(); i++) {
                if ((newPassword.charAt(i) >= 'A' && newPassword.charAt(i) <= 'Z')) {
                    flag2 = 1;
                } else if ((newPassword.charAt(i) >= 'a' && newPassword.charAt(i) <= 'z')){
                    flag3 = 1;
                } else if ((newPassword.charAt(i) >= 48 && newPassword.charAt(i) <= 57)) {
                    flag4 = 1;
                }else{
                    flag5 = 1;
                }
            }
        }

        if((flag1+flag2+flag3+flag4+flag5)==5){
            password = newPassword;
        }
    }
}
