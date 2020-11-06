package service;

import model.Customer;

import java.util.Collection;
import java.util.LinkedList;


public class CustomerService {

    Collection<Customer> customers = new LinkedList<>();  // our customer container

    private static final CustomerService customerService = new CustomerService();

    public static CustomerService getCustomerService(){
        return customerService;
    }

    /** Add customer to our list of customers */
    public void addCustomer(String email, String firstName, String lastName) throws IllegalArgumentException{
        Customer customer = new Customer(firstName, lastName, email);
        customers.add(customer);
    }

    /** Get customer who has the provided email */
    public Customer getCustomer(String customerEmail){
        for (Customer customer : customers) {
            if (customer.getEmail().equals(customerEmail)) {
                return customer;
            }
        }
        return null;
    }

    /** Get all customers who has the provided email */
    public Collection<Customer> getCustomers(String customerEmail){
        Collection<Customer> filteredCustomers = new LinkedList<>();
        for (Customer customer : customers) {
            if (customer.getEmail().equals(customerEmail)) {
                filteredCustomers.add(customer);
            }
        }
        return filteredCustomers;
    }

    /** Return all customers we have */
    public Collection<Customer> getAllCustomers(){
        return customers;
    }
}
