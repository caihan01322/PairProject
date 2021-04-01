package com.eepractice.webcrawller.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * 数据库保存的论文数据类型
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ResultItem{
    @Id
    @Column(length = 20)
    String id;
    @Column
    String title;

    @Column
    String authors;

    @Column(length = 100)
    String link;

    @Lob
    @Column(columnDefinition = "TEXT")
    String keywords;

    @Lob
    @Column(columnDefinition = "TEXT")
    public String note;

    @Column(length = 30)
    public String conferenceDate;

    @Column(length = 10)
    public String publisher;

    @ManyToMany(mappedBy = "collections")
    @JsonIgnore
    List<User> users;

    @Override
    public boolean equals(Object item){
        return this.id.trim().equals(((ResultItem) item).getId().trim());
    }

    @Override
    public int hashCode(){
        return id.hashCode();
    }


}
