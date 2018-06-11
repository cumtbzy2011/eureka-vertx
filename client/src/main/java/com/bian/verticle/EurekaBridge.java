package com.bian.verticle;

import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.servicediscovery.spi.ServiceImporter;
import io.vertx.servicediscovery.spi.ServicePublisher;
import io.vertx.servicediscovery.types.HttpEndpoint;

public class EurekaBridge implements ServiceImporter {
    @Override
    public void start(Vertx vertx, ServicePublisher publisher, JsonObject configuration, Future<Void> future) {
        publisher.publish(HttpEndpoint.createRecord(
          "eureka-server",
          "localhost",
          9001,
          "/test"
        ), ar -> {
            if (ar.succeeded()) {
                System.out.println("注册成功！");
            } else {
                System.out.println("注册失败");
            }
        });
        future.complete();
    }

    @Override
    public void close(Handler<Void> closeHandler) {

    }
}
