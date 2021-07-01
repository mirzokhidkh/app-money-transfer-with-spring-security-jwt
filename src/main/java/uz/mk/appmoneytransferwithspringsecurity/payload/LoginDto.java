package uz.mk.appmoneytransferwithspringsecurity.payload;

import lombok.Data;

@Data
public class LoginDto {
    private String username;
    private String password;
}
