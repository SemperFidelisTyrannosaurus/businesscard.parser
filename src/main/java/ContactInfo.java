import com.google.common.base.Joiner;

public class ContactInfo {

    private final String name;
    private final String phoneNumber;
    private final String emailAddress;

    ContactInfo(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    /**
     * returns full name of a given individual (ie. John Smith, etc.)
     * @return
     */
    public String getName() {

        return this.name;
    }

    /**
     * returns the phone number formatted as a sequence of digits
     * @return
     */
    public String getPhoneNumber() {

        return this.phoneNumber;
    }

    /**
     * returns the email address
     * @return
     */
    public String getEmailAddress() {

        return this.emailAddress;
    }

    @Override
    public String toString() {
        return Joiner.on("\n").join(new String[] {"Proper Name: " + name, "Phone Number: " + phoneNumber, "Email: " + emailAddress});
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactInfo other = (ContactInfo) o;

        if (name != null ? !name.equals(other.name) : other.name != null)
            return false;
        if (phoneNumber != null ? !phoneNumber.equals(other.phoneNumber) : other.phoneNumber != null)
            return false;
        if (emailAddress != null ? !emailAddress.equals(other.emailAddress) : other.emailAddress != null)
            return false;

        return true;
    }

}
