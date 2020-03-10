package pl.bank.app.infrastructure.transfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.bank.app.api.transfer.TransferRequest;
import pl.bank.app.domain.transfer.CreateTransferCommand;
import pl.bank.app.domain.transfer.CreateTransferCommandMapper;
import pl.bank.app.domain.transfer.TransferRetrievalClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
class TransferRetrievalRestClient implements TransferRetrievalClient {

    private final RestTemplate restTemplate;
    private final String pendingTransfersUrl;

    @Autowired
    public TransferRetrievalRestClient(RestTemplate restTemplate, @Value("${pending.transfers.retrieval.auctionapp.url}") String pendingTransferUrl) {
        this.restTemplate = restTemplate;
        this.pendingTransfersUrl = pendingTransferUrl;
    }

    @Override  //do dodania metoda pobierająca listę
    public List<CreateTransferCommand> getTransferRequests() {
        List<TransferRequest> result = restTemplate.exchange(
                pendingTransfersUrl,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<List<TransferRequest>>() {
                })
                .getBody();
        return result.stream()
                .map(CreateTransferCommandMapper::map)
                .collect(Collectors.toList());

    }
}


