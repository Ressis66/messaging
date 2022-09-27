package ru.alexworks.messaging;

import org.springframework.stereotype.Service;

@Service
public class ButterflyService {
  public Butterfly grow(Caterpillar caterpillar) throws Exception {
    return new Butterfly(caterpillar.getName());
  }
}
