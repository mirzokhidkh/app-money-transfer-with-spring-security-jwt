package uz.mk.appmoneytransferwithspringsecurity.payload;

import lombok.Data;

import java.sql.Date;

@Data
public class CardDto {
    private boolean active;

    private String username;

    private String number;

    private Date expiredDate;

//    private Integer userId;


}
