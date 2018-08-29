package jj.fly.user.dataobj;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Author: jiangjun
 * Date: 2018/8/10
 * Time: 下午3:24
 * Description:
 */
@Data
@Entity
public class UserInfo {

    @Id
    private String id;

    private  String username;

    private String password;

    private String openid;

    private Integer role;

}
