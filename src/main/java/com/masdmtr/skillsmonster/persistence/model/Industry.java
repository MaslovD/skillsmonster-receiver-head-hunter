package com.masdmtr.skillsmonster.persistence.model;

import javax.persistence.*;

/**
 * Created by dmaslov on 7/26/17.
 */
@Entity
public class Industry {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Industry industry = (Industry) o;

        if (id != industry.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
