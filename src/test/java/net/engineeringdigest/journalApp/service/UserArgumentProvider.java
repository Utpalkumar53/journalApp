package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User; // ✅ YOUR entity, not Spring Security's User
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserArgumentProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(createUser("babu", "shyam123")),
                Arguments.of(createUser("rao", "suraj123"))
        );
    }

    private User createUser(String username, String password) {
        User user = new User();
        user.setUserName(username);  // use your entity's setter
        user.setPassword(password);
        return user;
    }
}