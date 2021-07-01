package uz.mk.appmoneytransferwithspringsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import uz.mk.appmoneytransferwithspringsecurity.entity.template.AbsEntity;
import uz.mk.appmoneytransferwithspringsecurity.payload.ApiResponse;
import uz.mk.appmoneytransferwithspringsecurity.payload.CardDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card extends AbsEntity {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String number;

    private Double money = 1000.0;

    @Column(nullable = false)
    private Date expiredDate;

    @OneToOne
    private User user;
}
