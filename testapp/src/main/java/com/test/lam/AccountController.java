package com.test.lam; 

class AccountController {
    private Configs oConfigs = Configs.getInstance();
    private static String currentAccountEmail = null;

    private static class AccountControllerHolder {
        private static final AccountController INSTANCE = new AccountController();
    }

    public static AccountController getInstance() {
        return AccountControllerHolder.INSTANCE;
    }

    public void setCurrentAccount(String email) {
        currentAccountEmail = email;
    }

    public String getCurrenAccount() {
        return currentAccountEmail;
    }
}