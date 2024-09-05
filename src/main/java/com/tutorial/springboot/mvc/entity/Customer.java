package com.tutorial.springboot.mvc.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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
    @NotBlank(message = "first name is required")
    private String firstName;

    @NotNull(message = "last name is required")
    @NotBlank(message = "last name is required")
    private String lastName;

    @NotNull(message = "date of birth is required")
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @NotNull(message = "gender is required")
    @NotBlank(message = "gender is required")
    private String gender;

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
