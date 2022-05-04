package ru.job4j.accident.model;

import java.util.Objects;

public class Accident {
    private int id;
    private String name;
    private String text;
    private String address;

    public Accident() {
    }

    public Accident(int id, String name, String text, String address) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public Accident setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Accident setName(String name) {
        this.name = name;
        return this;
    }

    public String getText() {
        return text;
    }

    public Accident setText(String text) {
        this.text = text;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Accident setAddress(String address) {
        this.address = address;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Accident)) {
            return false;
        }
        Accident accident = (Accident) o;
        return id == accident.id &&
                Objects.equals(name, accident.name) &&
                Objects.equals(text, accident.text) &&
                Objects.equals(address, accident.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, text, address);
    }
}