package com.example.estateagency1.Models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Blogs")
@Entity
public class Blog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "CreateDate")
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(name = "Category")
    private String category;

    @Column(name = "Title")
    private String title;


    @Column(name = "Contents")
    private String contents;

    @ManyToOne
    @JoinColumn(name = "Id_User")
    private User user;

    @Column(name = "Image")
    private String imagePath;
}