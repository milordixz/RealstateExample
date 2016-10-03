package ru.realstate.service;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import ru.realstate.account.Account;

import java.util.List;

/**
 * Created by MiLoRD on 01-Oct-16.
 */
public interface AccountSevice {
    public List<Account> findAccountByEmail(int start, int length, String filter);
}
