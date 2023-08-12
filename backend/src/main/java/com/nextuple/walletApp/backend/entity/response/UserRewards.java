package com.nextuple.walletApp.backend.entity.response;

import com.nextuple.walletApp.backend.entity.utils.Reward;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRewards {
    private double amount;
    private List<Reward> rewardList;
}
