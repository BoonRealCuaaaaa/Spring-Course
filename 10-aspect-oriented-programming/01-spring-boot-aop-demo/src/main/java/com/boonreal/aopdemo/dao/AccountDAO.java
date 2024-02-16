package com.boonreal.aopdemo.dao;

import com.boonreal.aopdemo.Account;

public interface AccountDAO {

    void addAccount(Account account, boolean vipFlag);

    void addSillyMember();
}
