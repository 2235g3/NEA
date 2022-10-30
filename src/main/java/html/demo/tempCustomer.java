package html.demo;

public class tempCustomer {
    private String FName;
    private String LName;
    private String email;
    private String password;
    private String promoEmails;

    public tempCustomer(String FName, String LName, String email, String password, String promoEmails) {
        this.FName = FName;
        this.LName = LName;
        this.email = email;
        this.password = password;
        this.promoEmails = promoEmails;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPromoEmails() {
        return promoEmails;
    }

    public void setPromoEmails(String promoEmails) {
        this.promoEmails = promoEmails;
    }

    public String[] getList() {
        return new String[] {FName,LName,email,password,promoEmails};
    }
}
