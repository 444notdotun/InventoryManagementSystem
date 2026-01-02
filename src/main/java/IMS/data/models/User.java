package IMS.data.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
    @Id
    private String id;
    private String Surname;
    private String password;
    private Status status;


    public User(String id, String surname, String password) {
        this.Surname = surname;
        this.password=password;
        this.status = Status.INACTIVE;
    }

}
