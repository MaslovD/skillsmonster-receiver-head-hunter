package com.masdmtr.skillsmonster.entity.ui;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by dmaslov on 8/22/17.
 */

@Entity
@Table(name = "ui_menu_item_badge", schema = "public", catalog = "skillsmonster")
public class MenuItemBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    @Column(name = "text", nullable = true, length = 100)
    private String text;
    @Basic
    @Column(name = "color", nullable = true, length = 100)
    private String color;
    @Basic
    @Column(name = "bg", nullable = true, length = 100)
    private String bg;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItemBadge that = (MenuItemBadge) o;
        return id == that.id &&
                Objects.equals(text, that.text) &&
                Objects.equals(color, that.color) &&
                Objects.equals(bg, that.bg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, color, bg);
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
