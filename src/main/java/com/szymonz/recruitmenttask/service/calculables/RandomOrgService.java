package com.szymonz.recruitmenttask.service.calculables;

import com.szymonz.recruitmenttask.exceptions.NumberNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Arrays;

import static org.springframework.http.HttpStatus.Series.SUCCESSFUL;
import static org.springframework.http.HttpMethod.GET;

@Service
public class RandomOrgService implements Calculable<BigDecimal> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RandomOrgService.class);
    private static final String ENDPOINT_ADDRESS = "https://www.random.org/integers/?num=1&min=1&max=100000&col=1&base=10&format=plain&rnd=new";

    private final ClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

    @Override
    @Nullable
    public BigDecimal getValue() {
        try {
            ClientHttpRequest request = requestFactory.createRequest(URI.create(ENDPOINT_ADDRESS), GET);
            ClientHttpResponse response = request.execute();
            if (response.getStatusCode().series() == SUCCESSFUL) {
                byte [] responseBody = StreamUtils.copyToByteArray(response.getBody());
                String formattedResponse = new String(Arrays.copyOfRange(responseBody, 0, responseBody.length - 1)); // Cut \n sign
                LOGGER.info("Get " + formattedResponse + " from Random.org");
                return new BigDecimal(formattedResponse);
            } else throw new ResponseStatusException(response.getStatusCode());
        } catch (IOException ioException) {
            throw new NumberNotFoundException("Could not get the random number from random.org");
        }
    }
}
