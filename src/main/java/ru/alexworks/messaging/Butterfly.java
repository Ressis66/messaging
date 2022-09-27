package ru.alexworks.messaging;

import java.io.Serializable;

public class Butterfly implements Serializable {
  String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Butterfly(String name) {
    this.name = name;
  }
  public Butterfly() {
  }

  @Override
  public String toString() {
    return "Butterfly{" +
        "name='" + name + '\'' +
        '}';
  }
}
