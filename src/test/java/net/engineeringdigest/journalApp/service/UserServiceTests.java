package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

//    @BeforeAll
//    @BeforeEach
//    @AfterEach
//    @AfterAll ---> these are thye aslso the useful annotations which is used
    //@Disabled     // means ye iss wale test ko nahi chalayega
    //@Test
    //@ParameterizedTest
    //@CsvSource --> iske jagah ye bhi use kar sakte hai
//    @ValueSource(strings = {
//            "ram",
//            "shyam",
//            "utpal"
//    })
//    public void testFindByUsername(String name) {
//        User user = userRepository.findByUserName("ram");
//        assertTrue(!user.getJournalEntries().isEmpty());
//        assertNotNull(userRepository.findByUserName(name),"failed for" + name);
//    }

    @ParameterizedTest
    @ArgumentsSource(UserArgumentProvider.class)
    public void testSaveNewUser(User user) {
        assertTrue(userService.saveNewUser(user));
    }

//    @Disabled
//    @ParameterizedTest
//    @CsvSource({
//            "1,1,2",
//            "2,10,12",
//            "3,3,9"
//    })
//    public void test(int a, int b, int expected) {
//        assertEquals(expected, a + b);
//    }
}
