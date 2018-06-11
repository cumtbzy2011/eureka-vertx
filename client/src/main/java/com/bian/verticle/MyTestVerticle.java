package com.bian.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpClient;
import io.vertx.core.json.JsonObject;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.ServiceReference;

public class MyTestVerticle extends AbstractVerticle{

    ServiceDiscovery discovery;

    @Override
    public void start() throws Exception {
        discovery = ServiceDiscovery.create(vertx);
        discovery.registerServiceImporter(new EurekaBridge(), new JsonObject());

        vertx.createHttpServer()
          .requestHandler(req -> {
              discovery.getRecord(r -> true, ar -> {
                  if (ar.succeeded() && ar.result() != null) {
                      ServiceReference reference = discovery.getReference(ar.result());
                      HttpClient client = reference.getAs(HttpClient.class);
                      client.getNow("/test", httpClientResponse -> {
                          httpClientResponse.handler(buffer -> {
                              System.out.println(buffer.toString());
                          });
                          reference.release();
                      });
                  } else {
                      System.out.println("未找到服务");
                  }
              });
              req.response()
                .putHeader("content-type", "text/plain")
                .end("hello world!");
          }).listen(8081);
    }
}
