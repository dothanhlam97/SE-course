package com.test.lam; 

class AccountController {
    private Configs oConfigs = Configs.getInstance();
    private static Account currentAccount = null;

    private static class AccountControllerHolder {
        private static final AccountController INSTANCE = new AccountController();
    }

    public static AccountController getInstance() {
        return AccountControllerHolder.INSTANCE;
    }

    public void setCurrentAccount(Account current) {
        currentAccount = current;
    }

    public Account getCurrenAccount() {
        return currentAccount;
    }
}