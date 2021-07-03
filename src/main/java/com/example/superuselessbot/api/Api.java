package com.example.superuselessbot.api;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.Locale;

@Component
public class Api {
    public static String getCrypto(String message) throws IOException, URISyntaxException {
        String url = "https://min-api.cryptocompare.com/data/pricemulti?fsyms="+message+"&tsyms=USD,EUR,RUB";

        var result = "";
        var reply ="";

        var query = new URIBuilder(url);

        CloseableHttpClient client = HttpClients.createDefault();
        var request = new HttpGet(query.build());

        request.setHeader(HttpHeaders.ACCEPT, "application/json");

        CloseableHttpResponse response = client.execute(request);

        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } finally {
            response.close();
            client.close();
        }

        JSONObject jsonObject;
        JSONObject crypto;
        BigDecimal priceUsd,priceEur,priceRub;

        try {
            jsonObject = new JSONObject(result);
            crypto = jsonObject.getJSONObject(message.toUpperCase(Locale.ROOT));
            priceUsd = crypto.getBigDecimal("USD");
            priceRub = crypto.getBigDecimal("RUB");
            priceEur = crypto.getBigDecimal("EUR");
            reply = "Crypto: " + message.toUpperCase(Locale.ROOT)+"\n" +
                    "USD: " + priceUsd + "\n" +
                    "EUR: " + priceEur + "\n" +
                    "RUB: " + priceRub + "\n";
        }catch (Exception e){
            reply = "Данная криптовалюта не найдена!";
        }
        return reply;
    }
}