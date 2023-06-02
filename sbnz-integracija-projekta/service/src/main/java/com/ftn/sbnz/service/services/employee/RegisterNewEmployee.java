package com.ftn.sbnz.service.services.employee;

import com.ftn.sbnz.model.models.Restaurant;
import com.ftn.sbnz.model.models.RestaurantEmployee;
import com.ftn.sbnz.model.models.User;
import com.ftn.sbnz.service.dto.request.employee.EmployeeRegistrationRequest;
import com.ftn.sbnz.service.services.restaurant.AddEmployeeToRestaurant;
import com.ftn.sbnz.service.services.restaurant.GetRestaurantById;
import com.ftn.sbnz.service.services.role.GetRoleByName;
import com.ftn.sbnz.service.services.user.RegisterUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterNewEmployee {
    private final RegisterUser registerUser;
    private final PasswordEncoder passwordEncoder;
    private final GetRoleByName getRoleByName;
    private final GetRestaurantById getRestaurantById;
    private final AddEmployeeToRestaurant addEmployeeToRestaurant;

    public User execute(EmployeeRegistrationRequest employeeRegistrationRequest) {
        Restaurant restaurant = getRestaurantById.execute(employeeRegistrationRequest.getRestaurantId());

        RestaurantEmployee employee = RestaurantEmployee.builder()
                .email(employeeRegistrationRequest.getEmail())
                .name(employeeRegistrationRequest.getName())
                .surname(employeeRegistrationRequest.getSurname())
                .passwordHash(passwordEncoder.encode(employeeRegistrationRequest.getPassword()))
                .phoneNumber(employeeRegistrationRequest.getPhoneNumber())
                .profilePicture(employeeRegistrationRequest.getProfilePicture())
                .role(getRoleByName.execute("EMPLOYEE"))
                .blocked(false)
                .active(false)
                .restaurant(restaurant)
                .build();

        employee = (RestaurantEmployee) registerUser.execute(employee);
        addEmployeeToRestaurant.execute(restaurant.getId(), employee);

        return employee;
    }

}
