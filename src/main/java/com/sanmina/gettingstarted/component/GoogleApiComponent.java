/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanmina.gettingstarted.component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.client.googleapis.GoogleUtils;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;

import org.springframework.stereotype.Component;

/**
 *
 * @author jorge_covarrubias
 */
@Component
public class GoogleApiComponent {

    private GoogleCredential initCredential() {
        try {
            List<String> scopes = new ArrayList<>();
            scopes.add("https://www.googleapis.com/auth/userinfo.email");
            scopes.add("https://www.googleapis.com/auth/userinfo.profile");
            scopes.add("https://www.googleapis.com/auth/drive");
            scopes.add("https://www.googleapis.com/auth/drive.readonly");
            scopes.add("https://www.googleapis.com/auth/drive.file");
            scopes.add("https://www.googleapis.com/auth/spreadsheets");
            scopes.add("https://www.googleapis.com/auth/spreadsheets.readonly");
            String path = "";
            String currentSericceAccount = "gdl8share@plant8-dashboard.iam.gserviceaccount.com.json";
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                path = "google\\" + currentSericceAccount;
            } else {
                path = "google/" + currentSericceAccount;
            }
            return GoogleCredential.fromStream(getClass().getClassLoader().getResourceAsStream(path))
                    .createScoped(scopes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Use Services Accounts
    public Sheets createSheetsService() throws IOException, GeneralSecurityException {
        HttpTransport httpTransport = newProxyTransport();
        JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        GoogleCredential credential = initCredential();
        return new Sheets.Builder(httpTransport, jsonFactory, credential).setApplicationName("Google-Sheets/0.1")
                .build();
    }

    // Use Oauth2 Token
    public Sheets createSheetsService(String token) throws IOException, GeneralSecurityException {
        HttpTransport httpTransport = newProxyTransport();
        JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        GoogleCredential credential = new GoogleCredential().setAccessToken(token);
        return new Sheets.Builder(httpTransport, jsonFactory, credential).setApplicationName("Google-Sheets/0.1")
                .build();
    }

    private HttpTransport newProxyTransport() throws GeneralSecurityException, IOException {
        NetHttpTransport.Builder builder = new NetHttpTransport.Builder();
        builder.trustCertificates(GoogleUtils.getCertificateTrustStore());
        builder.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("gateway.zscalertwo.net", 9480)));
        return builder.build();
    }
}
