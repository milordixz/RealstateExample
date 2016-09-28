package ru.realstate.account;

import java.security.Principal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
class AccountController {

    private AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @RequestMapping(value = "account/current", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public Account currentAccount(Principal principal) {
        Assert.notNull(principal);
        return accountRepository.findOneByEmail(principal.getName());
    }

    @RequestMapping(value = "account/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    @Secured("ROLE_ADMIN")
    public Account account(@PathVariable("id") Long id) {
        return accountRepository.findOne(id);
    }

    @RequestMapping(value = "account/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    @Secured("ROLE_ADMIN")
    public Map<String,Object>  getAllAccounts(HttpServletRequest request) {
        Integer start = 0;
        Integer pageDisplayLength = 10;
        Integer draw = 0;
        if (null != request.getParameter("start")) start = (Integer.valueOf(request.getParameter("start"))/10)+1;
        if (null != request.getParameter("length")) pageDisplayLength = Integer.valueOf(request.getParameter("length"));
        if (null != request.getParameter("draw")) draw = Integer.valueOf(request.getParameter("draw"));
        Page<Account> personsList = accountRepository.getAllAccounts(new PageRequest(start-1,pageDisplayLength));
        Map<String,Object> data = new HashMap<>();
        data.put("data",personsList.getContent());
        data.put("draw",draw);
        data.put("recordsTotal",personsList.getTotalElements());
        data.put("recordsFiltered",personsList.getTotalElements());
        return data ;
    }

}
