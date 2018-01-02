package com.test.lam;

public class Path {
    public static class Web {
        public static final String INDEX = "/";
        public static final String SIGNUP = "/sign-up";
        public static final String POST_ACCOUNT = "/post-account";
    }

    public static class Template {
        public final static String INDEX = "velocity/template/index.vm";
        public final static String SIGNUP = "velocity/template/sign-up.vm";
    }
}