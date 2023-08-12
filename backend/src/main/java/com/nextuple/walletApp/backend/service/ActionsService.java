package com.nextuple.walletApp.backend.service;

import com.nextuple.walletApp.backend.entity.request.MoneyTransfer;
import com.nextuple.walletApp.backend.entity.request.WalletRecharge;
import com.nextuple.walletApp.backend.entity.response.*;
import com.nextuple.walletApp.backend.entity.request.Card;

public interface ActionsService {
    UserDetails getUserDetails(String id);
    UserCurrentBalance getCurrentBalance(String id);

    UserTransactions getTransactions(String id);

    UserRewards getRewards(String id);

    String walletRecharge(String id, WalletRecharge walletRecharge);

    String moneyTransfer(String id, MoneyTransfer moneyTransfer);

    String addCard(String id, Card card);

    String deleteCard(String id, Card card);

    UserCards getCards(String id);
}
//Service for Users Actions