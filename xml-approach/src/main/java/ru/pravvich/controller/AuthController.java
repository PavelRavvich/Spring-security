package ru.pravvich.controller;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Author : Pavel Ravvich.
 * Created : 17.08.17.
 */
@Component
public class AdminController {

    /**
     * Controller successes auth base auth URL.
     */
    @RequestMapping("/admin/menu")
    public String getMainPage() {
        return "menu";
    }

    /**
     * Login page controller.
     *
     * @param error  flag for rendering error entered login & password.
     * @param logout flag for rendering massage about successful logout.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(Model model,

                               @RequestParam(value = "error",
                                       required = false) String error,

                               @RequestParam(value = "logout",
                                       required = false) String logout) {

        model.addAttribute("error", error);
        model.addAttribute("logout", logout);

        return "login";
    }

//    @RequestMapping(value = "/")
//    public String adminPanel(ModelMap model) {
//
//        String targetPage = "redirect:admin/admin_panel";
//
//        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//        final String login = auth.getName();
//
//        if (login.equals("")) targetPage = "/login?error";
//
//        return targetPage;
//    }

}
