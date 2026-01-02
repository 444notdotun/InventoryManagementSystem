package IMS.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@Document
public class Store {
    @Id
    private String id;
    private String name;
    private HashMap<String,Product> products = new HashMap<>();

    public Store(String name){
        this.name = name;
    }

}
