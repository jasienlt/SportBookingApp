package com.developer.sportbooking.CustomerTest;

import static org.assertj.core.api.Assertions.assertThat;

import com.developer.sportbooking.entity.Customer;
import com.developer.sportbooking.repository.CustomerRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CustomerRepoTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepo repo;

    // test methods go below
    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode("dung_nguyen");
        customer.setEmail("dung.nguyen1@yahoo.com");
        customer.setPassword(encodedPassword);
        customer.setFirstName("Dung");
        customer.setLastName("Nguyen");
        customer.setPhone("0404123456");

        Customer savedCustomer = repo.save(customer);

        Customer existUser = entityManager.find(Customer.class, savedCustomer.getId());

        assertThat(customer.getEmail()).isEqualTo(existUser.getEmail());

    }
}
