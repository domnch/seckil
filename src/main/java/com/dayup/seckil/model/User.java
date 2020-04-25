package com.dayup.seckil.model;

import org.apache.kafka.common.protocol.types.Field;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author domn
 * @version 1.0
 * @date 2020/1/12 1:27
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 5898834552599693092L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "username")
    @NotBlank(message = "username不可以为空")
    private String username;

    @Column(name = "password")
    @NotBlank(message = "password长度应为4到6之间")
    private String password;

    private String repassword;

    @Column(name="dbflag")
    private String dbflag;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getDbflag() {
        return dbflag;
    }

    public void setDbflag(String dbflag) {
        this.dbflag = dbflag;
    }
}
