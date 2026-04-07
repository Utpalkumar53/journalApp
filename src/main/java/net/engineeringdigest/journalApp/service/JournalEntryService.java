package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

//@Service //-----> better to use this due to package specific
@Component //----> ye ab iska object null nahi rahega
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;


    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now(ZoneId.of("UTC")));
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
          /*  user.setUserName(null); ---> ye commit hi nahi hone dega because hamlog transaction use kar rahe hai
          so isko use nahi karna hai isisliye isko remobe kiya hai ham ne but hgere it is for understanding the things only
           */
            userService.saveUser(user);
        }catch(Exception e) {
            //log.error("Exception" , e);
            System.out.println(e);
            throw new RuntimeException("Error while saving entry",e);
        }
    }

    public void saveEntry(JournalEntry journalEntry) {
        try {
            journalEntryRepository.save(journalEntry);
        }catch(Exception e) {
            log.error("Exception" , e);
        }
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll(); // all these types of methods are under the MongoRepository
    }

    public Optional<JournalEntry> findById(@PathVariable ObjectId id) {

        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(j -> j.getId().equals(id));/*here we uses the "->"
        lamda expression  and very important is that it will also remove from the user tooo*/
            if (removed) {
                userService.saveUser(user);   // yaha par id and username save ho jayega aur fir usko delete kkarwa lenge
                journalEntryRepository.deleteById(id);
            }

        }catch(Exception e) {
            log.error("Error while deleting entry",e);
            throw new RuntimeException("An Error Occured while deleting entry.",e);
        }
        return removed;
    }

}


// controller calls the----> service calls ------> repository