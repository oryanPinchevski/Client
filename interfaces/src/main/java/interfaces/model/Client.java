package interfaces.model;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

@Data
public class Client {
    @Field
    private String id;

    @Field
    private String name;

    public Client() {
    }

    public Client(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
