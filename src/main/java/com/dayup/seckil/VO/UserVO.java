package com.dayup.seckil.VO;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author domn
 * @version 1.0
 * @date 2020/1/24 16:59
 */
public class UserVO implements Serializable {

    private static final long serialVersionUID = 4802452002810531790L;

    private Integer id;

    private String username;

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

    private String password;

    private String repassword;

    private String dbflag;
}
