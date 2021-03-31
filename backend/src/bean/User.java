package com.eepractice.webcrawller.bean;


import com.eepractice.webcrawller.utils.CommonUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User模型类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String username;

    @Column
    private String password;

    @ManyToMany(cascade = CascadeType.MERGE,fetch=FetchType.EAGER)
    @JoinTable(name = "userCollections", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
            @JoinColumn(name = "paper_id", referencedColumnName = "id") })
    private List<ResultItem> collections = new ArrayList<>();


    // 保存前对密码进行加密
    @PrePersist
    private void convertPassword(){
        password = CommonUtils.MD5Encode(password);
    }

}
