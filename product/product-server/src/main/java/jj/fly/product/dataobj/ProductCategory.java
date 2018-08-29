package jj.fly.product.dataobj;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Author: jiangjun
 * Date: 2018/7/31
 * Time: 下午4:11
 * Description:
 */
@Data
@Entity
public class ProductCategory {

    @Id
    @GeneratedValue
    private Integer categoryId;


    private String categoryName;

    /**
     * 类名编号
     */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;
}
