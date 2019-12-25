package com.hqgml.chat.pojo;


/**
 * @author Devil
 * @date 2019/12/18 20:03
 */


public class User  {

    private Long id;
    private String username;
    private String password;
    private String lasttime;
    private String lastip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getLasttime() {
        return lasttime;
    }

    public void setLasttime(String lasttime) {
        this.lasttime = lasttime;
    }

    public String getLastip() {
        return lastip;
    }

    public void setLastip(String lastip) {
        this.lastip = lastip;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", lasttime='" + lasttime + '\'' +
                ", lastip='" + lastip + '\'' +
                '}';
    }
}
