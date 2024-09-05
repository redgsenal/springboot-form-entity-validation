package com.tutorial.springboot.mvc.entity;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @NotNull(message = "first name is required")
    private String firstName;

    @NotNull(message = "last name is required")
    private String lastName;

    @NotNull(message = "date of birth is required")
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @NotNull(message = "gender is required")
    private String gender;

    @NotNull(message = "email is required")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "invalid email address")
    private String email;

    @NotNull(message = "postal code is required")
    @Pattern(regexp = "^\\d{4}$", message = "invalid post code")
    private String postalCode;

    @NotNull(message = "free passes is required")
    @Min(value = 0, message = "value must be equal or greater than 0")
    @Max(value = 10, message = "value must be equal or lesser than 10")
    private Integer freePasses;

    public int getAge() {
        if (this.dateOfBirth == null) {
            return 0;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateOfBirth);
        LocalDate dob = LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        return Period.between(dob, LocalDate.now()).getYears();
    }

    public String getFormattedDateOfBirth() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd, yyyy");
        return simpleDateFormat.format(dateOfBirth);
    }
}
