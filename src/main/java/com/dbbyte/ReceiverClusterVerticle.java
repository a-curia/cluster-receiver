package com.dbbyte;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

import static com.dbbyte.util.Constants.ADDRESS;

public class ReceiverClusterVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        final EventBus eventBus = vertx.eventBus();
        System.out.println("**INSTANCE**"+vertx.hashCode()+"***"+eventBus.hashCode());

        eventBus.consumer("mytopic", receivedMessage -> {
            System.out.println("Received message: " + receivedMessage.body());
//            receivedMessage.reply("message recived"); // only works for p2p
        });   
        System.out.println("Receiver ready!");
    }

}
