package com.masdmtr.skillsmonster.entity.ui;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by dmaslov on 8/23/17.
 */
public class MenuItemSub {

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
    @Column(name = "routing", nullable = true, length = 500)
    private String routing;

    @Basic
    @Column(name = "external_link", nullable = true, length = 500)
    private String externalLink;

    @ManyToOne
    @JoinColumn(name = "icon", nullable = true)
    private MenuItemIcon icon; //IMenuItemIcon

    @OneToMany
    @JoinColumn(name = "sub", nullable = true)
    private Set<Menu> sub; //IMenuItemSub

    @ManyToOne
    @JoinColumn(name = "badge", nullable = true)
    private MenuItemBadge badge; //IMenuItemBadge

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
}
