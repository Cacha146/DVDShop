package com.example.DVDShop.domain;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class DVDisk {

   // @OneToMany(cascade = {CascadeType.REMOVE})
   // @Transient
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

     @Length(max = 255, message = "Message too long (more than 255)")
    private String name;

    @NotBlank(message = "Please fill the message")
    @Length(max = 2048, message = "Message too long (more than 2kB)")
    private String description;


    private Boolean is_available=Boolean.TRUE;


    private String filename;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


    public DVDisk (){

    }

    public DVDisk(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public boolean isIs_available() {
        return is_available;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }



}
