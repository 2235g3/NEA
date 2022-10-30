package html.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public void logIn(@RequestParam(value="logInDetails", name="logInDetails") String[] logInDetails, Model model){
        if (logInDetails.length > 0) {
            tempCustomer logInObj = new tempCustomer("", "", logInDetails[0], logInDetails[1], "");
            model.addAttribute("returns", userService.logIn(logInObj));
        }
        else {
            model.addAttribute("returns", new String[]{"",""});
        }
    }
    @GetMapping("/custAccount")
    public String custAccount(@RequestParam(value="accountEmail", name="accountEmail") String accountEmail, Model model) {
        String[][] userDetails = new databaseMethods().getData("SELECT userID, FName, LName, email FROM users WHERE email = '" + accountEmail +"'");
        String[][] custDetails = new databaseMethods().getData("SELECT custID, totalAmountSpent, promoEmails, memberPoints FROM customers WHERE userID = '" + userDetails[0][0] + "'");
        String[][] accountDetails = {{userDetails[0][1], userDetails[0][2], custDetails[0][0], userDetails[0][3], custDetails[0][1], custDetails[0][2], custDetails[0][3]}};
        model.addAttribute("accountDetails", accountDetails);
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
