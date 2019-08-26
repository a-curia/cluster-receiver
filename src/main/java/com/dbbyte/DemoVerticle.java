package com.dbbyte;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import lombok.extern.slf4j.Slf4j;

public class DemoVerticle extends AbstractVerticle {


    @Override
    public void start(Future<Void> future){
        deployHelper(ReceiverClusterVerticle.class.getName()).setHandler(result -> {
            if(result.succeeded()){
                future.complete();
            } else {
                future.fail(result.cause());
            }
        });
    }


    private Future<Void> deployHelper(String name){

        final Future<Void> future = Future.future();
        vertx.deployVerticle(name, res -> {
            if(res.failed()){
                System.out.println("Failed to deploy verticle " + name);
                future.fail(res.cause());
            } else {
                System.out.println("Deployed verticle " + name);
                future.complete();
            }
        });

        return future;
    }

}