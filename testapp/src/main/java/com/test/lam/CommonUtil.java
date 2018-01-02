package com.test.lam;



public class CommonUtil {
  public static long getCurrentUnixTimestampInMs() {
    return System.currentTimeMillis();
  }

  public static long getCurrentUnixTimestamp() {
    return System.currentTimeMillis() / 1000L;
  }

  public static Integer getCurrentUnixTimestampAsInt() {
    return Integer.valueOf(Math.toIntExact(CommonUtil.getCurrentUnixTimestamp()));
  }
}