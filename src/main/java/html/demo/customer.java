package html.demo;

public class customer extends user {

    private int customerID;
    private String promoEmails;
    private int memberPoints;
    private float amountSpent;

    public customer(int userID, String FName, String LName, String email, String password, int accountType, String hashedEmail, int customerID, String promoEmails, int memberPoints, float amountSpent) {
        super(userID, FName, LName, email, password, accountType, hashedEmail);
        this.customerID = customerID;
        this.promoEmails = promoEmails;
        this.memberPoints = memberPoints;
        this.amountSpent = amountSpent;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getPromoEmails() {
        return promoEmails;
    }

    public void setPromoEmails(String promoEmails) {
        this.promoEmails = promoEmails;
    }

    public int getMemberPoints() {
        return memberPoints;
    }

    public void setMemberPoints(int memberPoints) {
        this.memberPoints = memberPoints;
    }

    public float getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(float amountSpent) {
        this.amountSpent = amountSpent;
    }

    public String[] getList() {
        return new String[] {Integer.toString(this.getUserID()),this.getFName(),this.getLName(), this.getEmail(), this.getPassword(), Integer.toString(this.getAccountType()), this.getHashedEmail(),Integer.toString(customerID), promoEmails, Integer.toString(memberPoints), Float.toString(amountSpent)};
    }
}
