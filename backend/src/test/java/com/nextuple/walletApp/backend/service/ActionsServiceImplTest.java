package com.nextuple.walletApp.backend.service;

import com.nextuple.walletApp.backend.entity.request.*;
import com.nextuple.walletApp.backend.entity.response.UserCurrentBalance;
import com.nextuple.walletApp.backend.entity.response.UserDetails;
import com.nextuple.walletApp.backend.entity.response.UserRewards;
import com.nextuple.walletApp.backend.entity.response.UserTransactions;
import com.nextuple.walletApp.backend.exceptions.CardAlreadyExists;
import com.nextuple.walletApp.backend.exceptions.InsufficientBalance;
import com.nextuple.walletApp.backend.exceptions.UserNotFound;
import com.nextuple.walletApp.backend.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
class ActionsServiceImplTest {
    @Mock
    private UsersInfoRepository usersInfoRepository;
    @Mock
    private CurrentBalanceRepository currentBalanceRepository;
    @Mock
    private TransactionHistoryRepository transactionHistoryRepository;
    @Mock
    private RewardsRepository rewardsRepository;
    @Mock
    private LinkedCardsRepository linkedCardsRepository;
    @InjectMocks
    private ActionsServiceImpl actionsService;

    //User 1 with email=tushartg600@gmail.com
    private UserInfo user=new UserInfo("tushartg600@gmail.com","Tushar", "Gupta","12345678");
    private CurrentBalance currentBalance=new CurrentBalance("tushartg600@gmail.com",0);
    private TransactionHistory transactionHistory=new TransactionHistory("tushartg600@gmail.com",new ArrayList<>());
    private RewardHistory rewardHistory=new RewardHistory("tushartg600@gmail.com",0,new ArrayList<>());
    private LinkedCards linkedCards=new LinkedCards("tushartg600@gmail.com",new ArrayList<>());

    //User 2 with email=mohitmg700@gmail.com
    private UserInfo user1=new UserInfo("mohitmg700@gmail.com","Mohit", "Gupta","123456");
    private CurrentBalance currentBalance1=new CurrentBalance("mohitmg700@gmail.com",0);
    private TransactionHistory transactionHistory1=new TransactionHistory("mohitmg700@gmail.com",new ArrayList<>());

    //Objects for walletRecharge() and moneyTransfer() test
    private WalletRecharge walletRecharge=new WalletRecharge();
    private MoneyTransfer moneyTransfer=new MoneyTransfer();

    //Object for addCard() test
    private Card card=new Card("1231 3212 3211 2121","HDFC Bank", "Tushar Gupta");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getUserDetails() {
        when(usersInfoRepository.findById(user.getEmail())).thenReturn(Optional.of(user));
        //Expected userDetails
        UserDetails userDetails=new UserDetails("Tushar", "Gupta"); //On changing test is failing
        assertEquals(userDetails, actionsService.getUserDetails(user.getEmail()));
    }

    @Test
    void getCurrentBalance() {
        when(currentBalanceRepository.findById(currentBalance.getEmail())).thenReturn(Optional.of(currentBalance));
        //Expected current balance
        UserCurrentBalance userCurrentBalance=new UserCurrentBalance(0);
        assertEquals(userCurrentBalance,actionsService.getCurrentBalance(currentBalance.getEmail()));
    }

    @Test
    void getTransactions() {
        when(transactionHistoryRepository.findById(transactionHistory.getEmail())).thenReturn(Optional.of(transactionHistory));
        //Expected
        int size=0;
        assertEquals(size,actionsService.getTransactions(transactionHistory.getEmail()).getTransactionList().size());
    }

    @Test
    void getRewards() {
        when(rewardsRepository.findById(rewardHistory.getEmail())).thenReturn(Optional.of(rewardHistory));
        //Expected
        int amount=0;
        int size=0;
        assertEquals(amount,rewardHistory.getAmount());
        assertEquals(size,actionsService.getRewards(rewardHistory.getEmail()).getRewardList().size());
    }

    @Test
    //With some card
    void walletRecharge() {
        String id= user.getEmail();
        walletRecharge.setCard("Cashback Transfer");
        walletRecharge.setAmount(100);
        when(currentBalanceRepository.findById(id)).thenReturn(Optional.of(currentBalance));
        when(currentBalanceRepository.save(currentBalance)).thenReturn(currentBalance);
        when(transactionHistoryRepository.findById(id)).thenReturn(Optional.of(transactionHistory));
        when(transactionHistoryRepository.save(transactionHistory)).thenReturn(transactionHistory);
        when(rewardsRepository.findById(id)).thenReturn(Optional.of(rewardHistory));
        when(rewardsRepository.save(rewardHistory)).thenReturn(rewardHistory);
        when(rewardsRepository.findById(id)).thenReturn(Optional.of(rewardHistory));
        when(rewardsRepository.save(rewardHistory)).thenReturn(rewardHistory);
        assertEquals("Rs 100.0 credited",actionsService.walletRecharge(id,walletRecharge));
    }
    @Test
    //When receiver not exists
    void moneyTransferTest1() {
        String id= user.getEmail();
        String receiverId= user1.getEmail();
        when(currentBalanceRepository.findById(receiverId)).thenReturn(Optional.ofNullable(null));
        assertThrows(UserNotFound.class, () -> actionsService.moneyTransfer(id, moneyTransfer));
    }
    @Test
    //When Insufficient Balance
    void moneyTransferTest2() {
        //Let Current Balance=100
        currentBalance.setAmount(100);
        moneyTransfer.setReceiver("mohitmg700@gmail.com");
        //Send balance =200
        moneyTransfer.setAmount(200);
        String id= user.getEmail();
        String receiverId= moneyTransfer.getReceiver();
        when(currentBalanceRepository.findById(receiverId)).thenReturn(Optional.of(currentBalance1));
        when(currentBalanceRepository.findById(id)).thenReturn(Optional.of(currentBalance));
        assertThrows(InsufficientBalance.class, () -> actionsService.moneyTransfer(id, moneyTransfer));
    }
    @Test
    //Successful Money Transfer
    void moneyTransferTest3() {
        //Let Current Balance=100
        currentBalance.setAmount(100);
        moneyTransfer.setReceiver("mohitmg700@gmail.com");
        //Send balance=50
        moneyTransfer.setAmount(50);
        String id= user.getEmail();
        String receiverId= user1.getEmail();
        when(currentBalanceRepository.findById(receiverId)).thenReturn(Optional.of(currentBalance1));
        when(transactionHistoryRepository.findById(receiverId)).thenReturn(Optional.of(transactionHistory1));
        when(currentBalanceRepository.save(currentBalance1)).thenReturn(currentBalance1);
        when(transactionHistoryRepository.save(transactionHistory1)).thenReturn(transactionHistory1);
        when(currentBalanceRepository.findById(id)).thenReturn(Optional.of(currentBalance));
        when(transactionHistoryRepository.findById(id)).thenReturn(Optional.of(transactionHistory));
        when(currentBalanceRepository.save(currentBalance)).thenReturn(currentBalance);
        when(transactionHistoryRepository.save(transactionHistory)).thenReturn(transactionHistory1);

        assertEquals("Rs 50.0 successfully send to mohitmg700@gmail.com",actionsService.moneyTransfer(id, moneyTransfer));
    }
    @Test
    //Successfully Card Added
    void addCard1(){
        //Adding a card for testing
        List<Card> cardList=new ArrayList<>();
        cardList.add(card);
        linkedCards.setCardList(cardList);
        String id=user.getEmail();
        //Different Card for testing
        Card testCard=new Card("1231 3212 3211 2122","State Bank of India","Mohit Gupta");
        when(linkedCardsRepository.findById(id)).thenReturn(Optional.of(linkedCards));
        when(linkedCardsRepository.save(linkedCards)).thenReturn(linkedCards);
        assertEquals("Card Added Successfully",actionsService.addCard(id,testCard));
    }
    @Test
    //If Card Already Exists
    void addCard2(){
        //Adding a card for testing
        List<Card> cardList=new ArrayList<>();
        cardList.add(card);
        linkedCards.setCardList(cardList);
        String id=user.getEmail();
        //Different Card for testing
        Card testCard=new Card("1231 3212 3211 2121","State Bank of India","Mohit Gupta");
        when(linkedCardsRepository.findById(id)).thenReturn(Optional.of(linkedCards));
        when(linkedCardsRepository.save(linkedCards)).thenReturn(linkedCards);
        assertThrows(CardAlreadyExists.class,()->actionsService.addCard(id,testCard));
    }
    @Test
    void deleteCard(){
        String id=user.getEmail();
        when(linkedCardsRepository.findById(id)).thenReturn(Optional.of(linkedCards));
        when(linkedCardsRepository.save(linkedCards)).thenReturn(linkedCards);
        assertEquals("Card Successfully Deleted",actionsService.deleteCard(id,card));
    }
    @Test
    void getCards(){
        when(linkedCardsRepository.findById(linkedCards.getEmail())).thenReturn(Optional.of(linkedCards));
        //Expected
        int size=0;
        assertEquals(size,actionsService.getCards(linkedCards.getEmail()).getCardList().size());
    }
}
