package model;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {

    private String firstName;
    private String lastName;
    private String email;

    public Customer(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;

        Pattern regex = Pattern.compile(".+@.+\\..+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = regex.matcher(email);
        boolean matchFound = matcher.find();
        if(!matchFound) {
            throw new IllegalArgumentException();
        }
        this.email = email;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }

    @Override
    public String toString() {
        return "First Name:" + firstName + " Last Name:" + lastName + " Email:" + email;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true;}
        if (obj == null ) {return false;}
        if (getClass() != obj.getClass()){return false;}
        Customer customer = (Customer) obj;
        return Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(email, customer.email);
    }
}
