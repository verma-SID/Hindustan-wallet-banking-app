package com.nextuple.walletApp.backend.service;
import com.nextuple.walletApp.backend.entity.request.LinkedCards;
import com.nextuple.walletApp.backend.entity.request.UserInfo;
import com.nextuple.walletApp.backend.exceptions.UserNotFound;
import com.nextuple.walletApp.backend.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@SpringBootTest
class AdminServiceImplTest {
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
    private String id="tushartg600@gmail.com";
    @InjectMocks
    private AdminServiceImpl adminService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void deleteUser1() {
        when(usersInfoRepository.existsById(id)).thenReturn(false);
        assertThrows(UserNotFound.class,()->adminService.deleteUser(id));
    }
    @Test
    void deleteUser2() {
        when(usersInfoRepository.existsById(id)).thenReturn(true);
        adminService.deleteUser(id);
        verify(usersInfoRepository).deleteById(id);
    }
}