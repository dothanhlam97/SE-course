package com.test.lam;

import java.util.HashMap;
import java.util.Map;

public class Account { 
    public String Name;
    public String _id;
    public String ID;
    public String Email;
    public String Password;
    public Map<String, String> Options;
    public Integer CreatedDate;
    public String Avatar;
    public Integer Permission;
    public Integer UpdatedDate;    

    public Account() {
        Options = new HashMap<>();
    }

    public Account(String Name_, String Password_, String ID_, String Email_) {
        setName(Name_);
        setPassword(Password_);
        setID(ID_);
        setEmail(Email_);
        setCreatedDate(CommonUtil.getCurrentUnixTimestampAsInt());
        Options = new HashMap<>();
    }

    public Account(String Email_, String Password_) {
        setPassword(Password_);
        setEmail(Email_);
    }

    public Account(Account oldAccount) {
        setName(oldAccount.Name);
        setPassword(oldAccount.Password);
        setID(oldAccount.ID);
        setEmail(oldAccount.Email);
        setCreatedDate(oldAccount.CreatedDate);
        setPermission(oldAccount.Permission);
        Options = oldAccount.Options;
    }

    public String getName() {
        return Name;
    }

    public String getAvatar() {
        return Avatar;
    }

    public String getID() {
        return ID;
    }

    public String getEmail() {
        return Email;
    }

    public void setName(String Name_) {
        Name = Name_;
    }

    public void setCreatedDate(int CreatedDate_) {
        CreatedDate = CreatedDate_;
        UpdatedDate = CreatedDate_;
    }

    public void setPassword(String Password_) {
        Password = Password_;
    }

    public void setID(String ID_) {
        ID = ID_;
        _id = ID_;
    }

    public void setPermission(Integer Permission_) {
        Permission = Permission_;
    }

    public void setEmail(String Email_) {
        Email = Email_;
    }

}

