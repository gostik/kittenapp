package com.media.aip.testwork;
/**
 * Created by user_sca on 01.12.2014.
 * Common module for app
 */
final class Modules {
  static Object[] list(TestApplication app) {
    return new Object[] {
        new ApplicationModule(app)
    };
  }

  private Modules() {
    // No instances.
  }
}
