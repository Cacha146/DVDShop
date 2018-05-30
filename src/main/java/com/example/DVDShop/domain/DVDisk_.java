package com.example.DVDShop.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DVDisk.class)
public class DVDisk_ {
    public static volatile SingularAttribute<DVDisk, Long> id;
    public static volatile SingularAttribute<DVDisk, String> name;
    public static volatile SingularAttribute<DVDisk, String> description;
    public static volatile SingularAttribute<DVDisk, Boolean> is_available;
    public static volatile SingularAttribute<DVDisk, String> filename;
}
