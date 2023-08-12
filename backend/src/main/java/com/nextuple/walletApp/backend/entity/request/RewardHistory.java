package com.nextuple.walletApp.backend.entity.request;

import com.nextuple.walletApp.backend.entity.utils.Reward;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "rewards")
public class RewardHistory {
    @Id
    @Email
    @NotBlank
    private String email;

    private double amount;
    private List<Reward> rewardList;
}
