package com.nextuple.walletApp.backend.entity.response;

import com.nextuple.walletApp.backend.entity.request.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCards {
    private List<Card> cardList;
}
