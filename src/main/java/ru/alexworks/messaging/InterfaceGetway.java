package ru.alexworks.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import java.util.Collection;

@MessagingGateway
public interface InterfaceGetway {

  @Gateway(requestChannel = "caterpillarChannel", replyChannel = "butterflyChannel")
  Collection<Butterfly> process(Collection<Caterpillar> orderItem);
}
