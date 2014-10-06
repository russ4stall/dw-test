package com.github.russ4stall.dw.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Russ Forstall
 */

public class Name {
    private int id;
    private String name;

    public Name() {
    }

    public Name(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonProperty
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
