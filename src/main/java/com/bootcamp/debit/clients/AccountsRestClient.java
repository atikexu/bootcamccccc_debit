package com.bootcamp.debit.clients;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.bootcamp.debit.clients.config.RestConfig;
import com.bootcamp.debit.dto.Account;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase de acceso al microservicio de clientes
 */
@Service
public class AccountsRestClient {
    RestConfig restConfig = new RestConfig();
	
	public Flux<Account> getAllAccountXCustomerId(String accountId) {
		WebClient webClient = WebClient.create("http://localhost:8085");
        return  webClient.get()
                .uri("/account/consult/"+accountId)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToFlux(Account.class);
	}
	
	public Mono<Account> getAccountById(String accountId) {
		WebClient webClient = WebClient.create("http://localhost:8085");
        return  webClient.get()
                .uri("/account/"+accountId)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(Account.class);
	}
	
	public Mono<Account> updateAccount(Account accountRequestDto) {
		WebClient webClient = WebClient.create("http://localhost:8085");
        return  webClient.put()
                .uri("/account")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(accountRequestDto), Account.class)
                .retrieve()
                .bodyToMono(Account.class);
	}

}
