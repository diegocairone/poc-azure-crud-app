package com.cairone.poc.cfg;

import com.azure.core.credential.AccessToken;
import com.azure.core.credential.TokenRequestContext;
import com.azure.identity.DefaultAzureCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
//@Configuration
public class DatasourceCfg {

    //@Bean
    public DataSource getDataSource() {

        TokenRequestContext ctx = new TokenRequestContext()
                .addScopes("https://database.windows.net/.default");

        DefaultAzureCredential credential = new DefaultAzureCredentialBuilder().build();
        AccessToken accessToken = credential.getTokenSync(ctx);

        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setAccessToken(accessToken.getToken());
        ds.setDatabaseName("poc-passwordless-db");
        ds.setServerName("dcairone.database.windows.net");
        ds.setAuthentication("ActiveDirectoryMSI");

        log.info("Access token: {}", accessToken.getToken());
        return ds;
    }
}
