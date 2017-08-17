package ru.pravvich.controller;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Author : Pavel Ravvich.
 * Created : 17.08.17.
 */
@Component
public class AuthController {

    /**
     * Controller successes auth base auth URL.
     */
    @RequestMapping("/auth/menu")
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
}
