package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable {
    //实现该接口是为了更好的存储和运输

    private int uid;
    private String uname;
    private Date ubirthday;
    private String usex;
    private String uaddress;
    //一对多
    private List<Account> accounts;

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Date getUbirthday() {
        return ubirthday;
    }

    public void setUbirthday(Date ubirthday) {
        this.ubirthday = ubirthday;
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
    }

    public String getUaddress() {
        return uaddress;
    }

    public void setUaddress(String uaddress) {
        this.uaddress = uaddress;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", ubirthday=" + ubirthday +
                ", usex='" + usex + '\'' +
                ", uaddress='" + uaddress + '\'' +
                '}';
    }
}
