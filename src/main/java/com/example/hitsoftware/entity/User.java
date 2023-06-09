package com.example.hitsoftware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {

    @TableId
    private String userName;
    private String keyWord;
    private String userCharacter;
    private String userContact;
    private String userAddress;
    private String addPermission;
    private String deletePermission;

    public User(String userName, String keyWord, String userCharacter, String userContact, String userAddress, String addPermission, String deletePermission) {
        this.userName = userName;
        this.keyWord = keyWord;
        this.userCharacter = userCharacter;
        this.userContact = userContact;
        this.userAddress = userAddress;
        this.addPermission = addPermission;
        this.deletePermission = deletePermission;
    }
}
