package com.spring.blog.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices.RememberMeTokenAlgorithm;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        // Session Management Migrations
        // https://docs.spring.io/spring-security/reference/migration/servlet/session-management.html

        // Authentication Migrations
        // https://docs.spring.io/spring-security/reference/migration/servlet/authentication.html

        /**
         * securityFilterChain cria e retorna um filtro de segurança do Spring Security.
         * Esse filtro é adicionado à cadeia de filtros que processam as requisições
         * HTTP recebidas pela aplicação. O filtro é definido usando a classe
         * HttpSecurity, que permite a configuração das políticas de segurança para
         * diferentes camadas da aplicação web, como autenticação, autorização e
         * controle de sessão.
         */

        /**
         * o serviço rememberMeServices é configurado através da chamada ao método
         * rememberMe, que permite especificar as opções de configuração do serviço de
         * "lembrar-me".
         */

        /**
         * A classe TokenBasedRememberMeServices é uma classe do Spring Security que
         * implementa a interface RememberMeServices. Geralmente, ela é importada de uma
         * das dependências do Spring Security.
         */

        /**
         * A classe TokenBasedRememberMeServices é uma implementação do serviço
         * RememberMeServices que usa um token criptografado para manter o usuário
         * logado. O construtor dessa classe recebe uma chave privada e um
         * UserDetailsService como argumentos.
         */

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http, RememberMeServices rememberMeServices)
                        throws Exception {
                http
                                // ...
                                .rememberMe((remember) -> remember
                                                .rememberMeServices(rememberMeServices));
                return http.build();
        }

        /**
         * rememberMeServices cria um serviço para manter o usuário logado na aplicação
         * web, mesmo depois que ele fecha o navegador ou reinicia o computador. O
         * serviço RememberMeServices é uma interface do Spring Security que deve ser
         * implementada para gerenciar o comportamento de "lembrar-me" da aplicação.
         */

        @Bean
        RememberMeServices rememberMeServices(UserDetailsService userDetailsService) {
                RememberMeTokenAlgorithm encodingAlgorithm = RememberMeTokenAlgorithm.SHA256;
                TokenBasedRememberMeServices rememberMe = new TokenBasedRememberMeServices(Constantes.myKey,
                                userDetailsService,
                                encodingAlgorithm);
                rememberMe.setMatchingAlgorithm(RememberMeTokenAlgorithm.SHA256);
                return rememberMe;
        }

        /**
         * O BCryptPasswordEncoder é um algoritmo de codificação de senhas do Spring
         * Security que utiliza a função de hash bcrypt para proteger as senhas dos
         * usuários. Ele é recomendado porque é seguro e fornece um bom nível de
         * proteção para as senhas armazenadas.
         */

        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder() {
                return new BCryptPasswordEncoder();
        }

}
