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
		cryptMethods.loadKeyStore();
		/*try {
			cryptMethods aes = new cryptMethods();
			aes.initKeys();
			String encryptedText = aes.encrypt("Text");
			String decryptedText = aes.decrypt(encryptedText);
			System.out.println(encryptedText);
			System.out.println(decryptedText);
		} catch (Exception e) {
			System.out.println("Error occurred");
			System.out.println(e);
		}*/
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
