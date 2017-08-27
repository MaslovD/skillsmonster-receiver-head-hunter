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
    @Column(name = "url", nullable = true, length = 500)
    private String url;

    @Basic
    @Column(name = "router_link", nullable = true, length = 500)
    private String routerLink;

    @Basic
    @Column(name = "icon", nullable = true)
    private String icon; //IMenuItemIcon

    @Basic
    @Column(name = "selected", nullable = true)
    private Boolean selected;

    @Basic
    @Column(name = "expanded", nullable = true)
    private Boolean expanded;

    @Basic
    @Column(name = "item_order", nullable = true)
    private String order;

    @Basic
    @Column(name = "target", nullable = true)
    private String target;


    @OneToMany (mappedBy="parent")
    private Set<Menu> subMenu; //IMenuItemSub

    @ManyToOne
    @JoinColumn(name="parent")
    private Menu parent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRouterLink() {
        return routerLink;
    }

    public void setRouterLink(String routerLink) {
        this.routerLink = routerLink;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Boolean getExpanded() {
        return expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Set<Menu> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(Set<Menu> subMenu) {
        this.subMenu = subMenu;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }
}

