package ru.realstate.admin;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by MiLoRD on 9/18/2016.
 */

@Controller
public class AdminController {
    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "admin")
    public String admin() {
        return "admin/dashboard";
    }
}
