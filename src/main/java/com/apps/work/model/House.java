package com.apps.work.model;

import javax.persistence.*;

@Entity
@Table(name = "house")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 50)
    private String name;

    @Column
    private int numOfRooms;

    public House() {
    }

    public House(int id, String name, int numOfRooms) {
        this.id = id;
        this.name = name;
        this.numOfRooms = numOfRooms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        House house = (House) o;

        if (id != house.id) return false;
        if (numOfRooms != house.numOfRooms) return false;
        return name != null ? name.equals(house.name) : house.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + numOfRooms;
        return result;
    }

    @Override
    public String toString() {
        return "com.apps.work.model.House{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numOfRooms=" + numOfRooms +
                '}';
    }

}
