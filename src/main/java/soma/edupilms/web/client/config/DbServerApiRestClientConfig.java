package soma.edupilms.web.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import soma.edupilms.web.client.DbServerApiClient;

@Configuration
public class DbServerApiRestClientConfig {

    @Bean
    public DbServerApiClient dbServerApiRestClient(
        @Value("${server-url.db-server}") String dbUrl
    ) {
        RestClient restClient = RestClient.builder()
            .baseUrl(dbUrl)
            .build();

        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
            .builderFor(adapter)
            .build();

        return factory.createClient(DbServerApiClient.class);
    }
}
