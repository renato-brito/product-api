package br.com.quickreader.productapi.service;

import br.com.quickreader.productapi.dto.CepResponse;
import br.com.quickreader.productapi.dto.ViaCepApiResponse;
import br.com.quickreader.productapi.exception.CepNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class CepService {

    private static final String VIA_CEP_URL = "https://viacep.com.br/ws/{cep}/json/";

    private final RestClient restClient;

    public CepService(RestClient restClient) {
        this.restClient = restClient;
    }

    public CepResponse buscarCep(String cep) {
        ViaCepApiResponse response = restClient.get()
                .uri(VIA_CEP_URL, cep)
                .retrieve()
                .body(ViaCepApiResponse.class);

        if (response == null || response.hasError()) {
            throw new CepNotFoundException(cep);
        }

        return new CepResponse(
                response.cep(),
                response.logradouro(),
                response.complemento(),
                response.bairro(),
                response.localidade(),
                response.uf(),
                response.estado(),
                response.regiao(),
                response.ibge(),
                response.gia(),
                response.ddd(),
                response.siafi()
        );
    }
}
