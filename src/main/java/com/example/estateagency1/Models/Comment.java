package com.example.estateagency1.Models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Comments")
@Entity
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Id_Blog")
    private Blog blog;

    @ManyToOne
    @JoinColumn(name = "Id_User")
    private User user;


    @Column(name = "Contents")
    private String contents;

    @Column(name = "CreateDate")
    @Temporal(TemporalType.DATE)
    private Date createDate;

}