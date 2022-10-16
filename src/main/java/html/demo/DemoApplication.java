package html.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/HTMLTest") // USE LISTS
	public String HTMLTest(@RequestParam(value = "details", name = "details", required = false) String[] details, Model model) {
		model.addAttribute("details", details);
		return "HTMLTest";
	}

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
	public void signUp(){
	}
	@GetMapping("/logIn")
	public void logIn(){
	}
	@GetMapping("/custAccount")
	public String custAccount(@RequestParam(value="accountDetails", name="accountDetails") String[] accountDetails, Model model) {
		model.addAttribute("accountDetails", accountDetails);
		return "custAccount";
	}
	@GetMapping("/custBookings")
	public void custBookings() {
	}
	@GetMapping("/custOrders")
	public void custOrders() {
	}
}
