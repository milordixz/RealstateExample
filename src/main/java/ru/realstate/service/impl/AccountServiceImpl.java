package ru.realstate.service.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;
import ru.realstate.account.Account;
import ru.realstate.service.JPA.AccountRepository;
import ru.realstate.service.AccountSevice;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * Created by MiLoRD on 01-Oct-16.
 */

@Service
public class AccountServiceImpl implements AccountSevice {
    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private AccountRepository appealRepository;

    @Override
    @Transactional
    public List<Account> findAccountByEmail(int start, int length, String filter) {
        Session session = entityManager.unwrap(Session.class);
        Criteria crit = session.createCriteria(Account.class);
        crit.setFirstResult(start);
        crit.setMaxResults(length);
        if (!StringUtils.isEmptyOrWhitespace(filter)) {
            crit.add(Restrictions.like("email", "%" + filter + "%").ignoreCase());
        }
        List<Account> accounts = crit.list();
        return accounts;
    }
}
