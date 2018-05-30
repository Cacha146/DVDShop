package com.example.DVDShop.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@StaticMetamodel(TakenItem.class)
public class TakenItem_ {

    public static volatile SingularAttribute<TakenItem, Long> id;
    public static volatile SingularAttribute<TakenItem, DVDisk> disk;
    public static volatile SingularAttribute<TakenItem, User> user;
    public static volatile SingularAttribute<TakenItem, Date> date_taken;
    public static volatile SingularAttribute<TakenItem, Date> date_returned;



}
