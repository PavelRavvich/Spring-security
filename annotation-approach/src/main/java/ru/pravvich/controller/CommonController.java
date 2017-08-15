package ru.pravvich.controller;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author : Pavel Ravvich.
 * Created : 14.08.17.
 */
@Controller
public class CommonController {
    /**
     * Controller successes auth base auth URL.
     * Determined in:
     * {@link ru.pravvich.config.WebSecurityConfig#configure(HttpSecurity)}.
     */
    @RequestMapping("/successes_auth")
    public String getMainPage() {
        return "redirect:/menu.do";
    }

    /**
     * Menu page controller.
     */
    @RequestMapping("/menu")
    public String menu() {
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

    /**
     * Logout controller.
     * Clean session and use getLoginPage() for render answer with massage.
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(final HttpServletRequest request,
                             final HttpServletResponse response,
                             Model model) {

        final Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return getLoginPage(model, null, "logout");
    }

    /**
     * Sample menu point.
     */
    @RequestMapping("/menu/some_menu_point")
    public String getMenuPoint() {
        return "some_menu_point";
    }
}
