package com.masdmtr.skillsmonster.entity.ui;

import javax.persistence.*;

/**
 * Created by dmaslov on 8/22/17.
 */
@Entity
@Table(name = "ui_menu_item_icon", schema = "public", catalog = "skillsmonster")
public class MenuItemIcon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    @Column(name = "icon_class", nullable = true, length = 100)
    private String icon_name;
    @Basic
    @Column(name = "color", nullable = true, length = 100)
    private String color;
    @Basic
    @Column(name = "bg", nullable = true, length = 100)
    private String bg;

    public String getIcon_name() {
        return icon_name;
    }

    public void setIcon_name(String icon_name) {
        this.icon_name = icon_name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }
}
