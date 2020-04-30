package com.dwd;

import javax.xml.bind.SchemaOutputResolver;

public class TryTest {

  public static void main(String[] args) {
    try {
      try( AutoCloseable a = new Resource().getResource()) {

      }
    }catch (Exception e){

    }
  }

  static class Resource {
    AutoCloseable getResource() {
      return () -> {
        System.out.println("close");
      };
    }
  }
}

