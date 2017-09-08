package me.anna.demo.controllers;


import me.anna.demo.models.Role;
import me.anna.demo.models.Userz;
import me.anna.demo.repositories.RoleRepo;
import me.anna.demo.repositories.UserzRepo;
import me.anna.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class HomeController {

    @Autowired
    private UserzRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private UserService userService;


//    @Autowired
//    SSUserDetailsService ssUserDetailsService;


    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
//    public String login(Principal p){
    public String login() {
        return "login";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }


    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String showRegistrationPage (Model model){

        model.addAttribute("user", new Userz());

        return "registration";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String pricessRegistrationPage (@Valid @ModelAttribute("user") Userz user, BindingResult result, Model model) {

        if (result.hasErrors()){
            return "registration";
        } else{
            userService.saveUserz(user);
            model.addAttribute("message", "User Account Successfully Created");
        }

        return "index";
    }


    @RequestMapping("/testRoles")
    public @ResponseBody
    String showRoles() {
        Iterable<Role> r = roleRepo.findAll();
        String x = "<h2>ROLE DETAILS</h2>";
        for (Role item : r) {
            x += "Role details:" + item.getRole() + " has an ID of " + item.getId() + "<br/>";
        }

        Role findR = roleRepo.findByRole("ADMIN");
        x += findR.getRole() + " was found with an ID of " + findR.getId();
        return x;

    }


    @RequestMapping("/adduser")
    public @ResponseBody
    String addUser() {

        Userz u = new Userz();
        u.setEmail("someone@somewhere.com");
        u.setEnabled(true);
        u.setUsername("newuser");
        u.setPassword("password");
        u.addRole(roleRepo.findByRole("ADMIN"));
        userRepo.save(u);
        return "User added";
    }


    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping("/secure")
    public String secure() {
        return "secure";
    }

}







    // Modifications to Controller: ("Roles and Users" Exercise):
//    @RequestMapping("/admin")
//    public String admin(){
//        return "admin";
//    }





    // ============= Allow user to Sign Up =============
//    @GetMapping("/signup")
//    public String createUser(Model model)
//    {
//        model.addAttribute("newUser", new User());
//        return "signup";
//    }

    // Validate entered information and if it is valid display the result
    // Person must have first name, last name, and email address
//    @PostMapping("/signup")
//    public String postPerson(@ModelAttribute("newUser") User newUser)
//    public String postPerson(@ModelAttribute("newUser") User newUser, Model model)
//    {
//
//        user.setEnabled(true);
//
//
//
//        user.setRoles(USER);
//        this.roles = roles;
//    }
//
//        userRepo.save(user);
//
////        long userId = user.getId();
////        User =  userRepo.findOne( userId);
////        model.addAttribute("userObject", u);



//        newUser.setEnabled(true);
//        Role newRole = roleRepo.findOne(new Long(2));
//
//        newRole.setRole(newRole.getRole());
//
////        Collection<Role> newUserRoles = new ArrayList<Role>();
//
//        Set<Role> newUserRoles = new HashSet<>();
//        newUserRoles.add(newRole);
//        newUser.setRoles(newUserRoles);
//
//
//        userRepo.save(newUser);


//    ssUserDetailsService.save(user);

//        ssUserDetailsService.loadUserByUsername(newUser.getUsername());

//        return "redirect:/welcome";



//
//        return "login";
//
//
//
//
//
//
//
//
//    }
//







