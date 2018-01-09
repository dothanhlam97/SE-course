package com.test.lam;

public class Path {
    public static class Web {
        public static final String INDEX = "/";
        public static final String SIGNUP = "/sign-up";
        public static final String POST_ACCOUNT = "/post-account";
        public static final String LOGIN_ACCOUNT = "/login-account";
        public static final String LOGIN = "/login";
        public static final String POST_PROJECT_PAGE = "/post-project";
        public static final String PROFILE = "/profile";
        public static final String LOGOUT = "/log-out";
        public static final String POST_PROJECT = "/post-one-project";
        public static final String SHOW_PROJECT = "/show-project";
        public static final String JOIN_PROJECT = "/join-a-job";
        public static final String CANCEL_PROJECT = "/cancel-a-job";
        public static final String JOB_APPLIED = "/list-job-applied";
        public static final String FREELANCER_APPLIED = "/list-freelancer-applied";
        public static final String OFFER_CANDIDATE = "/offer-candidate";
        public static final String REJECT_CANDIDATE = "/reject-candidate";
    }

    public static class Template {
        public final static String INDEX = "velocity/template/index.vm";
        public final static String SIGNUP = "velocity/template/sign-up.vm";
        public final static String LOGIN = "velocity/template/login.vm";
        public final static String POST_PROJECT_PAGE = "velocity/template/post-project.vm";
        public final static String PROFILE = "velocity/template/profile.vm";
        public final static String SHOW_PROJECT = "velocity/template/show-project.vm";
        public final static String FREELANCER_APPLIED = "velocity/template/list-freelancer-applied.vm";
        public final static String JOB_APPLIED = "velocity/template/list-job-applied.vm";
    }
}