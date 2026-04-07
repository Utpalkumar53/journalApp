package net.engineeringdigest.journalApp.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data               // ✅ generates getUserName(), getPassword(), setRoles() etc.
@NoArgsConstructor  // ✅
@AllArgsConstructor // ✅
@Builder            // ✅
public class User {

   @Id
   private ObjectId id;

   @Indexed(unique = true)
   private String userName;

   private String password;

   private List<String> roles;

   @DBRef
   private List<JournalEntry> journalEntries = new ArrayList<>();
}