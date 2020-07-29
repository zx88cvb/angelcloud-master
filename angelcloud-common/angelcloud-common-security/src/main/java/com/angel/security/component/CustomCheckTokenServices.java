package com.angel.security.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @Author angel
 * @Date 2020/7/29
 */
@Slf4j
public class CustomCheckTokenServices implements ResourceServerTokenServices {
    private RestTemplate restTemplate;

    private String checkTokenEndpointUrl;

    private String clientId;

    private String clientSecret;

    private String tokenName = "token";

    private AccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();

    public CustomCheckTokenServices(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != 400) {
                    super.handleError(response);
                }
            }
        });
    }

    public void setCheckTokenEndpointUrl(String checkTokenEndpointUrl) {
        this.checkTokenEndpointUrl = checkTokenEndpointUrl;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setAccessTokenConverter(AccessTokenConverter accessTokenConverter) {
        this.accessTokenConverter = accessTokenConverter;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add(tokenName, accessToken);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, getAuthorizationHeader(clientId, clientSecret));
        Map<String, Object> map = postForMap(checkTokenEndpointUrl, formData, headers);
        log.info("check token result {}", map);
        if (map.containsKey("error")) {
            log.debug("check_token returned error: " + map.get("error"));
            throw new InvalidTokenException(accessToken);
        }

        Assert.state(map.containsKey("client_id"), "Client id must be present in response from auth server");
        return accessTokenConverter.extractAuthentication(map);
    }

    @Override
    public OAuth2AccessToken readAccessToken(String accessToken) {
        throw new UnsupportedOperationException("Not supported: read access token.");
    }

    private String getAuthorizationHeader(String clientId, String clientSecret) {
        String creds = String.format("%s:%s", clientId, clientSecret);
        return "Basic " + new String(Base64.encodeBase64(creds.getBytes(StandardCharsets.UTF_8)));
    }

    private Map<String, Object> postForMap(String path, MultiValueMap<String, String> formData, HttpHeaders headers) {
        if (headers.getContentType() == null) {
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        }
        @SuppressWarnings("rawtypes")
        Map map = restTemplate.postForObject(path, new HttpEntity<>(formData, headers), Map.class);
        @SuppressWarnings("unckecked")
        Map<String, Object> result = map;
        return result;
    }
}
