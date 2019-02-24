package implementation.impl;

import implementation.exception.ClientNotFoundException;
import implementation.interfaces.ClientService;
import interfaces.model.Client;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrDocument;

import java.io.IOException;

public class ClientServiceImpl implements ClientService {
    private HttpSolrClient solrClient;

    public ClientServiceImpl(HttpSolrClient solrClient) {
        this.solrClient = solrClient;
    }

    @Override
    public Client getClientByClientId(String clientId) {
        Client client = null;

        try {
            SolrDocument doc = solrClient.getById(clientId);

            if (doc == null) {
                throw new ClientNotFoundException(clientId);
            }

            String id = (String) doc.getFieldValue("id");
            String name = (String) doc.getFieldValue("name");

            client = new Client(id, name);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return client;
    }
}