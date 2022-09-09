package com.qa.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {
    String email, password, confirmpassword, firstName, middleName, lastName, birthday;
}
