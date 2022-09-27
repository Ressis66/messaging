package ru.alexworks.messaging;

import java.io.Serializable;

public class Caterpillar implements Serializable {
  String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Caterpillar(String name) {
    this.name = name;
  }
  public Caterpillar() {
  }

  @Override
  public String toString() {
    return "Caterpillar{" +
        "name='" + name + '\'' +
        '}';
  }

}
