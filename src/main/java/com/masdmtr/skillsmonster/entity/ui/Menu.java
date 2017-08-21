package com.masdmtr.skillsmonster.entity.ui;

import javax.persistence.*;

/**
 * Created by dmaslov on 8/21/17.
 */
@Entity
@Table(name = "ui_menu", schema = "public", catalog = "skillsmonster")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String title;
    private String icon; //IMenuItemIcon
    private String color;
    private boolean active;
    private boolean disabled;
    private boolean groupTitle;
    private String routing;
    private String externalLink;
    private String sub; //IMenuItemSub
    private String badge; //IMenuItemBadge

    @Basic
    @Column(name = "title", nullable = false, length = 50)

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}

