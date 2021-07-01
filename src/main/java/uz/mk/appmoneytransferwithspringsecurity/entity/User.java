//package uz.mk.appmoneytransferwithspringsecurity.entity;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import uz.mk.appmoneytransferwithspringsecurity.entity.template.AbsEntity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import java.util.Collection;

//@EqualsAndHashCode(callSuper = true)
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity(name = "users")
//public class User extends AbsEntity implements UserDetails {
//public class User extends AbsEntity {
//    @Column(nullable = false)
//    private String firstname;
//
//    @Column(nullable = false)
//    private String lastname;
//
//    @Column(nullable = false, unique = true)
//    private String username;
//
//    @Column(nullable = false)
//    private String password;
//
//    private boolean accountNonExpired = true;
//    private boolean accountNonLocked = true;
//    private boolean credentialsNonExpired = true;
//    private boolean enabled = true;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return accountNonExpired;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return accountNonLocked;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return credentialsNonExpired;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return enabled;
//    }
//}
