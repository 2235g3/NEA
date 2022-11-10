package html.demo;

import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class userService {

    public user adminObj;
    public customer custObj;

    public String[] signUp(tempCustomer customer){
        cryptMethods crypt = new cryptMethods();
        String[] returns = new String[4];
        Pattern emailRegExPattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Pattern passRegExPattern = Pattern.compile("(?=^.{8,20}$)((?!.*\\s)(?=.*[A-Z])(?=.*[a-z]))((?=(.*\\d){1,})(?=(.*\\W){1,}))^.*$");
        Matcher emailMatcher = emailRegExPattern.matcher(customer.getEmail());
        Matcher passMatcher = passRegExPattern.matcher(customer.getPassword());
        if (!emailMatcher.matches()) {
            returns[0] = "Email";
        }
        if (!passMatcher.matches()) {
            returns[1] = "Password";
        }
        String hashedEmail = crypt.hashing(customer.getEmail());
        customer.setPassword(crypt.hashing(customer.getPassword()));
        String[][] duplicateEmails = databaseMethods.getData("SELECT email FROM users WHERE hashedEmails = '" + hashedEmail + "'");
        if (duplicateEmails.length > 0) {
            returns[2] = "Exists";
        }
        if (returns[0] != null || returns[1] != null || returns[2] != null) {
            returns[3] = "True";
            return returns;
        }

        String[] encryptedData = new String[6];
        String[] normalData = customer.getList();

        crypt.initKeys();
        crypt.generateIV();
        for (int i = 0; i < encryptedData.length; i++) {
            if (i == 4) {
                encryptedData[i] = "1";
            }
            else if (i == 5) {
                encryptedData[i] = hashedEmail;
            }
            else {
                encryptedData[i] = crypt.encrypt(normalData[i], hashedEmail, true, crypt.IV);
            }
        }
        String iv = java.util.Base64.getEncoder().encodeToString(crypt.iv);
        databaseMethods.insertData("INSERT INTO users(FName, LName, email, password, accountType, hashedEmails, IV) VALUES('" + encryptedData[0] + "','" + encryptedData[1] + "','" + encryptedData[2] + "','" + encryptedData[3] + "'," + Integer.parseInt(encryptedData[4]) + ",'" + encryptedData[5] + "','"+  iv + "')");
        String[][] id = databaseMethods.getData("SELECT userID FROM users WHERE hashedEmails = '" + hashedEmail + "'");
        databaseMethods.insertData("INSERT INTO customers(userID, promoEmails, memberPoints, totalAmountSpent) VALUES(" + Integer.parseInt(id[0][0]) + ",'" + customer.getPromoEmails() + "'," + 0 + "," + 0 + ")");
        returns[3] = "False";
        return returns;
    }
    public String[] logIn(tempCustomer logInObj) {
        String[] returns = new String[3];
        cryptMethods crypt = new cryptMethods();
        databaseMethods dbm = new databaseMethods();
        logInObj.setEmail(crypt.hashing(logInObj.getEmail()));
        logInObj.setPassword(crypt.hashing(logInObj.getPassword()));
        String[][] accountType = dbm.getData("SELECT accountType FROM users WHERE hashedEmails = '" + logInObj.getEmail() + "'");
        String accountInfo[][];
        if (accountType[0][0].equals("1")) {
            accountInfo = dbm.getData("SELECT users.userID, users.FName, users.LName, users.email, users.password, users.accountType, users.hashedEmails, users.IV, customers.custID, customers.promoEmails, customers.memberPoints, customers.totalAmountSpent FROM users, customers WHERE users.hashedEmails = '" + logInObj.getEmail() + "' LIMIT 1");
        }
        else {
            accountInfo = dbm.getData("SELECT * FROM users WHERE hashedEmails = '" + logInObj.getEmail() + "'");
        }
        for (int i = 0; i < accountInfo.length; i++) {
            for (int j = 1; j < 5; j++) {
                accountInfo[i][j] = crypt.decrypt(accountInfo[i][j], logInObj.getEmail(), Base64.getDecoder().decode(accountInfo[0][7]));
            }
        }
        if ((!accountInfo[0][6].equals(logInObj.getEmail())) || (!accountInfo[0][4].equals(logInObj.getPassword()))) {
            returns[0] = "Incorrect";
            returns[1] = "False";
            returns[2] = "";
            return returns;
        }
        if (accountType[0][0].equals("1")) {
            custObj = new customer(Integer.parseInt(accountInfo[0][0]),accountInfo[0][1],accountInfo[0][2],accountInfo[0][3],accountInfo[0][4],Integer.parseInt(accountInfo[0][5]),accountInfo[0][6],Integer.parseInt(accountInfo[0][8]),accountInfo[0][9],Integer.parseInt(accountInfo[0][10]),Float.parseFloat(accountInfo[0][11]));
            return new String[] {"","True","1"};
        }
        else {
            adminObj = new user(Integer.parseInt(accountInfo[0][0]),accountInfo[0][1],accountInfo[0][2],accountInfo[0][3],accountInfo[0][4],Integer.parseInt(accountInfo[0][5]),accountInfo[0][6]);
            return new String[] {"","True","2"};
        }
    }
}
