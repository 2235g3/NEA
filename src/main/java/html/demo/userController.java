package html.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
public class userController {

    private userService userService = new userService();

    @GetMapping("/breakfastMenu")
    public void breakfastMenu() {
    }
    @GetMapping("/kidsMenu")
    public void kidsMenu() {
    }
    @GetMapping("/mainMenu")
    public void mainMenu() {
    }
    @GetMapping("/sundayMenu")
    public void sundayMenu() {
    }
    @GetMapping("/signUp")
    public void signUp(@RequestParam(value="accountDetails", name="accountDetails") String[] accountDetails, Model model){
        if (accountDetails.length > 0) {
            tempCustomer customer = new tempCustomer(accountDetails[0], accountDetails[1], accountDetails[2], accountDetails[3], accountDetails[4]);
            model.addAttribute("returns",userService.signUp(customer));
        }
        else {
            model.addAttribute("returns", new String[]{"", "", "", ""});
        }
    }
    @GetMapping("/logIn")
    public String logIn(@RequestParam(value="logInDetails", name="logInDetails") String[] logInDetails, Model model, final HttpServletResponse response){
        if (logInDetails.length > 0) {
            tempCustomer logInObj = new tempCustomer("", "", logInDetails[0], logInDetails[1], "");
            model.addAttribute("returns", userService.logIn(logInObj));
        }
        else {
            model.addAttribute("returns", new String[]{"","False",""});
        }
        return "logIn";
    }
    @GetMapping("/custAccount")
    public String custAccount(Model model) {
        customer customer = userService.custObj;
        System.out.println(customer.getList());
        model.addAttribute("accountDetails", customer);
        return "custAccount";
    }
    @GetMapping("/custBookings")
    public void custBookings() {
    }
    @GetMapping("/custOrders")
    public void custOrders() {
    }
    @GetMapping("/custInfoMenu")
    public void custInfoMenu() {
    }
}
