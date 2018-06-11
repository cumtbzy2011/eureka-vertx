package com.bian;

import com.bian.verticle.MyTestVerticle;
import io.vertx.core.Vertx;

public class MainLancher {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(MyTestVerticle.class.getName());
    }
}
