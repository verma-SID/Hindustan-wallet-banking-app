package com.nextuple.walletApp.backend.service;

import com.nextuple.walletApp.backend.entity.request.*;
import com.nextuple.walletApp.backend.entity.request.MoneyTransfer;
import com.nextuple.walletApp.backend.entity.request.WalletRecharge;
import com.nextuple.walletApp.backend.entity.response.*;
import com.nextuple.walletApp.backend.entity.request.Card;
import com.nextuple.walletApp.backend.entity.utils.Reward;
import com.nextuple.walletApp.backend.entity.utils.Transaction;
import com.nextuple.walletApp.backend.exceptions.CardAlreadyExists;
import com.nextuple.walletApp.backend.exceptions.InsufficientBalance;
import com.nextuple.walletApp.backend.exceptions.UserNotFound;
import com.nextuple.walletApp.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ActionsServiceImpl implements ActionsService{
    @Autowired
    private UsersInfoRepository usersInfoRepository;
    @Autowired
    private CurrentBalanceRepository currentBalanceRepository;
    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;
    @Autowired
    private RewardsRepository rewardsRepository;
    @Autowired
    private LinkedCardsRepository linkedCardsRepository;
    @Override
    public UserDetails getUserDetails(String id) {
        UserInfo user= usersInfoRepository.findById(id).get();
        UserDetails userDetails=new UserDetails(user.getFirstName(),user.getLastName());
        return userDetails;
    }

    @Override
    public UserCurrentBalance getCurrentBalance(String id) {
        CurrentBalance user=currentBalanceRepository.findById(id).get();
        UserCurrentBalance userCurrentBalance=new UserCurrentBalance(user.getAmount());
        return userCurrentBalance;
    }

    @Override
    public UserTransactions getTransactions(String id) {
        TransactionHistory user=transactionHistoryRepository.findById(id).get();
        UserTransactions userTransactions=new UserTransactions(user.getTransactionList());
        return userTransactions;
    }

    @Override
    public UserRewards getRewards(String id) {
        RewardHistory user=rewardsRepository.findById(id).get();
        UserRewards userRewards=new UserRewards(user.getAmount(),user.getRewardList());
        return userRewards;
    }

    @Override
    public String walletRecharge(String id, WalletRecharge walletRecharge) {
        //Update the wallet amount
        CurrentBalance user=currentBalanceRepository.findById(id).get();
        user.setAmount(user.getAmount()+walletRecharge.getAmount());
        currentBalanceRepository.save(user);

        //Create a transaction object for sender
        long transID=new Random().nextLong();
        String type="Credit";
        String card=walletRecharge.getCard();
        double amount= walletRecharge.getAmount();
        String date=String.valueOf(new java.util.Date());
        Transaction transaction=new Transaction(transID,type,card,amount,date);

        //Update the transaction history of the sender
        TransactionHistory userTrans=transactionHistoryRepository.findById(id).get();
        List<Transaction> transactionList=userTrans.getTransactionList();
        transactionList.add(transaction);
        userTrans.setTransactionList(transactionList);
        transactionHistoryRepository.save(userTrans);

        //If wallet recharge is a cashback transfer then set the reward amount to zero and delete the Reward List
        if(walletRecharge.getCard().equals("Cashback Transfer")){
            RewardHistory userRewards=rewardsRepository.findById(id).get();
            userRewards.setAmount(0);
            userRewards.setRewardList(new ArrayList<>());
            rewardsRepository.save(userRewards);
            return "Rs "+walletRecharge.getAmount()+" credited";
        }

        //Create a reward
        long rewardId=new Random().nextLong();
        double rewAmount=walletRecharge.getAmount()*0.01;
        Reward reward = new Reward(rewardId,rewAmount,date);

        //Update the reward history
        RewardHistory userRewards=rewardsRepository.findById(id).get();
        userRewards.setAmount(userRewards.getAmount()+ reward.getAmount());
        List<Reward> rewardList=userRewards.getRewardList();
        rewardList.add(reward);
        userRewards.setRewardList(rewardList);
        rewardsRepository.save(userRewards);

        return "Rs "+walletRecharge.getAmount()+" credited";
    }

    @Override
    public String moneyTransfer(String id, MoneyTransfer moneyTransfer) {
        //Getting the Receiver if exists
        String receiverUserId=moneyTransfer.getReceiver();
        CurrentBalance receiver=currentBalanceRepository.findById(receiverUserId).orElse(null); //We can check if receiver exists from any collection(we are avoiding to check from usersInfo collection because we have confidential information there)

        //if receiver does not exist
        if(receiver==null){
            throw new UserNotFound();
        }
        else{
            //if the funds are sufficient in sender wallet
            CurrentBalance sender=currentBalanceRepository.findById(id).get();
            if(sender.getAmount()>=moneyTransfer.getAmount()) {
                TransactionHistory senderTrans=transactionHistoryRepository.findById(id).get();

                //Create a transaction object for sender
                Transaction transactionSender=new Transaction(new Random().nextLong(),"Debit",moneyTransfer.getReceiver(),moneyTransfer.getAmount(),String.valueOf(new java.util.Date()));

                //Update the wallet amount
                sender.setAmount(sender.getAmount()-moneyTransfer.getAmount());
                currentBalanceRepository.save(sender);

                //Update the transaction history of the sender
                List<Transaction> transactionList=senderTrans.getTransactionList();
                transactionList.add(transactionSender);
                senderTrans.setTransactionList(transactionList);
                transactionHistoryRepository.save(senderTrans);
                TransactionHistory receiverTrans=transactionHistoryRepository.findById(moneyTransfer.getReceiver()).get();

                //Create a transaction object for receiver
                Transaction transactionReceiver=new Transaction(new Random().nextLong(),"Credit",id,moneyTransfer.getAmount(),String.valueOf(new java.util.Date()));

                //Update the wallet amount
                receiver.setAmount(receiver.getAmount()+moneyTransfer.getAmount());
                currentBalanceRepository.save(receiver);

                //Update the transaction history of the receiver
                List<Transaction> transactionList1=receiverTrans.getTransactionList();
                transactionList1.add(transactionReceiver);
                receiverTrans.setTransactionList(transactionList1);
                transactionHistoryRepository.save(receiverTrans);
                return "Rs "+moneyTransfer.getAmount()+" successfully send to "+moneyTransfer.getReceiver();

                //Note: Imp=> Use Try Catch for dealing with Failed or Pending Transactions
            }
            else {
                throw new InsufficientBalance();
            }
        }
    }

    @Override
    public String addCard(String id, Card card) {
        LinkedCards linkedCards=linkedCardsRepository.findById(id).get();
        List<Card> cardList=linkedCards.getCardList();
        if(cardList.size()!=0) {
            //If card already exist throw CardAlreadyExists error
            for (int i = 0; i < cardList.size(); i++) {
                Card card1=cardList.get(i);
                if (card.getCardNumber().equals(card1.getCardNumber())) {
                    throw new CardAlreadyExists();
                }
            }
        }
        cardList.add(card);
        linkedCards.setCardList(cardList);
        linkedCardsRepository.save(linkedCards);
        return "Card Added Successfully";
    }

    @Override
    public String deleteCard(String id, Card card) {
        LinkedCards linkedCards=linkedCardsRepository.findById(id).get();
        List<Card> cardList=linkedCards.getCardList();
        cardList.remove(card);
        linkedCards.setCardList(cardList);
        linkedCardsRepository.save(linkedCards);
        return "Card Successfully Deleted";
    }

    @Override
    public UserCards getCards(String id) {
        LinkedCards linkedCards=linkedCardsRepository.findById(id).get();
        UserCards userCards=new UserCards(linkedCards.getCardList());
        return userCards;
    }
}
