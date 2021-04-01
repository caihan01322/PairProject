package com.eepractice.webcrawller.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUser {
    private String username;
    private String password1;
    private String password2;


    public boolean hasEmptyInput(){
        return username.trim().equals("")||
                password1.trim().equals("")||
                password2.trim().equals("");
    }

    public boolean isP1EqualsP2(){
        return password1.trim().equals(password2.trim());
    }
}
