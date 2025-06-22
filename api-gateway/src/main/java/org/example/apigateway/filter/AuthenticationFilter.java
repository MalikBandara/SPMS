package org.example.apigateway.filter;


import org.example.apigateway.advisor.NotFoundException;
import org.example.apigateway.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {


    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RouteValidator validator ;

    @Autowired
    private JwtUtils jwtUtils;

    public AuthenticationFilter() {
        super(Config.class);
    }
    @Override
    public GatewayFilter apply(Config config) {
       return((exchange , chain) -> {
            if(validator.isSecured.test(exchange.getRequest())){
                //header contains token or not
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new NotFoundException("Missing authorization header");
                }
                String authHeader= exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if(authHeader != null && authHeader.startsWith("Bearer ")){
                    authHeader = authHeader.substring(7);
                }
                try{
                    //Rest call to auth-service
//                    restTemplate.getForObject("")
                    jwtUtils.validateJwtToken(authHeader);
                }catch (Exception e){
                    throw new NotFoundException("Invalid authorization header");
                }
            }
           return chain.filter(exchange);
       });
    }

    public static class Config {


    }}
