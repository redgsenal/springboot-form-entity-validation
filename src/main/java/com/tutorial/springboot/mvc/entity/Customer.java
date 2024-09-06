package com.tutorial.springboot.mvc.entity;

import com.tutorial.springboot.mvc.validation.CourseCode;
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

    @NotNull(message = "{customer.firstname.required}")
    private String firstName;

    @NotNull(message = "{customer.lastname.required}")
    private String lastName;

    @NotNull(message = "{customer.dob.required}")
    @Past(message = "{customer.dob.invalid}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @NotNull(message = "{customer.gender.required}")
    private String gender;

    @NotNull(message = "{customer.email.required}")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "{customer.dob.invalid}")
    private String email;

    @NotNull(message = "{customer.postal.required}")
    @Pattern(regexp = "^\\d{4}$", message = "{customer.postal.invalid}")
    private String postalCode;

    @NotNull(message = "{customer.freepasses.required}")
    @Min(value = 0, message = "{customer.freepasses.min}")
    @Max(value = 10, message = "{customer.freepasses.max}")
    private Integer freePasses;

    @CourseCode(value = "LUV", message = "{customer.coursecode.invalid}")
    private String courseCode;

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
