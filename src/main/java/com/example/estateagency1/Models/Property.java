package com.example.estateagency1.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Propertys")
@Entity
public class Property implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Name", length = 50)
    private String name;

    @Column(name = "Location", length = 50)
    private String location;

    @Column(name = "Status", length = 50)
    private String status;

    @Column(name = "Area")
    private Double area;

    @Column(name = "Beds")
    private Integer beds;

    @Column(name = "Baths")
    private Integer baths;

    @Column(name = "Main_Image", length = 250)
    private String main_image;

    @Column(name = "Garage", length = 50)
    private String garage;

    @Column(name = "Amount", precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(name = "CreateDate")
    @Temporal(TemporalType.DATE)
    private Date createDate;


    @Column(name = "Description")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "Id_User")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "property",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PropertyImage> propertyImageList = new ArrayList<>();

    @Transient
    public void addMutipleImage(PropertyImage propertyImage){
        propertyImageList.add(propertyImage);
        propertyImage.setProperty(this);
    }
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = new Date();
    }

}