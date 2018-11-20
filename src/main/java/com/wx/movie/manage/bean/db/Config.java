package com.wx.movie.manage.bean.db;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_CONFIG")
public class Config {
    @Id
    @PrimaryKeyJoinColumn
    @Column(updatable = false, nullable = false)
    @Expose
    private String id;

    @Column
    @Expose
    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
