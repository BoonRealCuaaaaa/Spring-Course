package com.boonreal.aopdemo.dao;

import com.boonreal.aopdemo.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO{
    @Override
    public void addAccount(Account account, boolean vipFlag) {
        System.out.println(getClass()+ ": Doing my DB work: adding an account");
    }

    @Override
    public void addSillyMember() {
        System.out.println(getClass()+ ": Doing my DB work: adding a silly account");

    }
}
