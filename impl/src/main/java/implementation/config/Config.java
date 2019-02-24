package implementation.config;

import implementation.impl.ClientServiceImpl;
import interfaces.model.Client;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class Config {
	@Value("${solrjUrl}")
	private String solrjUrl;

	@Bean
	public ClientServiceImpl clientService() {
		return new ClientServiceImpl(solrClient());
	}

	@Bean
	public HttpSolrClient solrClient() {
		HttpSolrClient solrClient = new HttpSolrClient.Builder(solrjUrl).build();
		solrClient.setParser(new XMLResponseParser());

		initSolrJWithData(solrClient);

		return solrClient;
	}

	private void initSolrJWithData(HttpSolrClient solrClient) {
		try {
			solrClient.deleteByQuery("*:*");
			solrClient.addBean( new Client("123456789", "Oryan") );
			solrClient.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
