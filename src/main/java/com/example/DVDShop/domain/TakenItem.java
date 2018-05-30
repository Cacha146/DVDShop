package com.example.DVDShop.domain;


import javax.persistence.*;
import java.util.Date;

@Entity
public class TakenItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //@Transient
    @OneToOne(fetch = FetchType.EAGER) // , cascade = {CascadeType.REMOVE})
    @JoinColumn(name="dvDisk_id")
    private DVDisk disk;

    //@Transient
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    private Date date_taken;

    private Date date_returned;

    public TakenItem() {

    }

    public TakenItem(DVDisk disk, User user, Date date_teken) {
        this.disk = disk;
        this.user = user;
        this.date_taken = date_teken;
    }

    public DVDisk getDisk() {
        return disk;
    }

    public void setDisk(DVDisk disk) {
        this.disk = disk;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate_taken() {
        return date_taken;
    }

    public void setDate_taken(Date date_taken) {
        this.date_taken = date_taken;
    }

    public Date getDate_returned() {
        return date_returned;
    }

    public void setDate_returned(Date date_returned) {
        this.date_returned = date_returned;
    }
}
