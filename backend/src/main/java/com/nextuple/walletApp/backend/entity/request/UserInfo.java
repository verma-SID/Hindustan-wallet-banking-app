package com.nextuple.walletApp.backend.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "usersInfo")
public class UserInfo {
    @Id
    @Email
    @NotBlank
    private String email;
    @NotBlank @NotNull
    private String firstName;
    @NotBlank @NotNull
    private String lastName;
    @NotBlank @NotNull
    private String password;
}
