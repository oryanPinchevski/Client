package implementation.rest;

import implementation.interfaces.ClientService;
import interfaces.model.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("clientService")
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(path = "/{clientId}", produces = "application/json")
    public Client getClientNameByClientId(@PathVariable String clientId) {
        return clientService.getClientByClientId(clientId);
    }

}
