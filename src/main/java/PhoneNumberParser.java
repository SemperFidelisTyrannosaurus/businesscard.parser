public class PhoneNumberParser {
    private String phoneNumber = "";

    public void consider(String line) {
        if (phoneNumber.length() > 0)
            return;

        //make sure we don;t list a fax number
        boolean fax = false;
        if(line.toLowerCase().contains("f"))
            fax = true;
        //Strip all non digits out of phone number
        String digits = line.replaceAll("[^\\d]", "");
        //assume we wont have to deal with extensions since the api asked for a string of digits
        if (digits.length() == 0 || (digits.length() != 10 && digits.length() != 11) || fax)
            return;

        this.phoneNumber = digits;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }
}
