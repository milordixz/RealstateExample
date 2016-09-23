package ru.realstate.admin;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.realstate.account.Account;
import ru.realstate.account.AccountService;
import ru.realstate.config.WebAppConfigurationAware;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class AdminDashboard extends WebAppConfigurationAware {

    @Mock
    private AccountService accountService = new AccountService();

    @Test
    public void loginAdminPanel() throws Exception {
        //подготовка
        Account demoUser = new Account("admin", "admin", "ROLE_ADMIN");
        accountService.signin(demoUser);
        //действие
        mockMvc.perform(get("/admin"))
                .andExpect(view().name("admin/dashboard"))
                .andExpect(content().string(
                        allOf(
                                containsString("<title>Admin Panel</title>")
                        ))
                );
    }

    @Test
    public void loginUserPanel() throws Exception {
        //подготовка
        Account demoUser = new Account("user@example.com", "demo", "ROLE_USER");
        accountService.signin(demoUser);
        //действие
        mockMvc.perform(get("/admin"))
                .andExpect(view().name("error/general"))
                .andExpect(content().string(
                        allOf(
                                containsString("<title>Error page</title>")
                        ))
                );
    }


}
