package com.masdmtr.skillsmonster.entity.ui;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by dmaslov on 8/21/17.
 */
@Entity
@Table(name = "ui_menu", schema = "public", catalog = "skillsmonster")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "title", nullable = true, length = 500)
    private String title;

    @Basic
    @Column(name = "color", nullable = true, length = 500)
    private String color;

    @Basic
    @Column(name = "active", nullable = true, length = 500)
    private boolean active;

    @Basic
    @Column(name = "disabled", nullable = true, length = 500)
    private boolean disabled;

    @Basic
    @Column(name = "group_title", nullable = true, length = 500)
    private boolean groupTitle;

    @Basic
    @Column(name = "routing", nullable = true, length = 500)
    private String routing;

    @Basic
    @Column(name = "external_link", nullable = true, length = 500)
    private String externalLink;

    @ManyToOne
    @JoinColumn(name = "icon", nullable = true)
    private MenuItemIcon icon; //IMenuItemIcon

    @ManyToOne
    @JoinColumn(name = "badge", nullable = true)
    private MenuItemBadge badge; //IMenuItemBadge

    @OneToMany (mappedBy="parent")
    private Set<Menu> sub; //IMenuItemSub

    @ManyToOne
    @JoinColumn(name="parent")
    private Menu parent;


    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(boolean groupTitle) {
        this.groupTitle = groupTitle;
    }

    public String getRouting() {
        return routing;
    }

    public void setRouting(String routing) {
        this.routing = routing;
    }

    public String getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
    }

    public MenuItemIcon getIcon() {
        return icon;
    }

    public void setIcon(MenuItemIcon icon) {
        this.icon = icon;
    }

    public Set<Menu> getsub() {
        return sub;
    }

    public MenuItemBadge getBadge() {
        return badge;
    }

    public void setBadge(MenuItemBadge badge) {
        this.badge = badge;
    }
}

