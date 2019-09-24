package com.gzcstec.dataobject;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gzcstec.utils.serializer.Date2LongSerializer;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 类目
 * Created by fan on 2017/10/12.
 */
@Entity
@DynamicUpdate
@Data
public class ProductCategory {

    @Id
    @GeneratedValue
    /**类目id**/
    private Integer categoryId;

    /**
     * 类目名称
     */
    private String categoryName;

    /**
     * 类目类别
     */
    private Integer categoryType;

    /**创建时间**/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /**更新时间**/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
}
