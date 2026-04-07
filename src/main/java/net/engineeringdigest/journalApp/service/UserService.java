package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//@Service //-----> better to use this due to package specific
@Component //----> ye ab iska object null nahi rahega
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class); abb hamlog annotation use karenge
    public boolean saveNewUser(User user) {
        try{
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
        return true;
    }
        catch(Exception e){
            log.info("haHahahhhaahhaa");
            log.error("hhahshhdhdshhc");
            //log.error("Error while saving entry for {} : ",user.getUserName(),e);
            log.warn("Warningggggggg");
            log.debug("Warningggggggggg");
            log.trace("Warningggggggggggg");
            return false;
        }
    }
    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
            userRepository.save(user);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll(); // all these types of methods are under the MongoRepository
    }

    public Optional<User> findById(@PathVariable ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteById(@PathVariable ObjectId id) {
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}


// controller calls the----> service calls ------> repository