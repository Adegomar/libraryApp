package com.adegomar.springbootlibrary.config;

import com.adegomar.springbootlibrary.entity.Book;
import com.adegomar.springbootlibrary.entity.Review;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private String theAllowedOrigins = "http://localhost:3000";



    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config,
                                                     CorsRegistry cors){
        HttpMethod[] theUnsupportedActions = {
                HttpMethod.POST,
                HttpMethod.PATCH,
                HttpMethod.DELETE,
                HttpMethod.PUT};

        config.exposeIdsFor(Book.class);
        config.exposeIdsFor(Review.class);

        disableHttpMethods(Book.class, config, theUnsupportedActions);
        disableHttpMethods(Review.class, config, theUnsupportedActions);

        /*Configure CORS */
        cors.addMapping(config.getBasePath() + "/**")
                .allowedOrigins(theAllowedOrigins);
    }

    private void disableHttpMethods(Class theClass,
                                    RepositoryRestConfiguration config,
                                    HttpMethod[] theUnsupportedActions){
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure(((metdata, httpMethods) ->
                        httpMethods.disable(theUnsupportedActions)))
                .withCollectionExposure((metdata, httpMethods) ->
                        httpMethods.disable(theUnsupportedActions));
    }
}



// chcemy wyłączyć możliwość dodawania, edytowania i kasowania danych w książce
// w tym celu musimy określić to w metodzie, a następnie zaaplikować w innej metodzie
/*
ustawiamy theAllowedOrigins, które pozwoli nam połączyć się z frontem i wsadzimy zaraz

tworzymy metodę do konfiguracji
Wpisujemy do niej HttpMethodArray[], która jest po to żeby oreślić jakie akcje nas nie interesują

config.exposeIdsFor jest po to aby z naszej bazy danych zaczerpnąć id z przedmiotów klasy Book
(jest to widoczne potem w kodzie na stronie localhosta) robi się to po co, żeby
używać primary key dla funkcjonalności tu i na froncie. Tym sposobem wiemy dokładnie
jaką książkę będziemy używać

disableHttpMethods to metoda, która zdefiniowana jest poniżej

disableHttpMethods(theClass do jakiej klasy mamy zaimplementować; config jako parametr potrzebny do
konfiguracji bazy danych???; HttpMethod[] ma w sobie to czego nie chcemy){

     ?????
.komenda na uzyskanie ekspozycji do konfiguracji
.dla określonej klasy
why (metdata, HttpMethods) w lambdzie?
czym jest Item a czym collection?

withItemExposure
ExposureConfigurer withItemExposure(ExposureConfigurer.AggregateResourceHttpMethodsFilter filter)
Registers the given ExposureConfigurer.AggregateResourceHttpMethodsFilter to be used for item resources.

withCollectionExposure
ExposureConfigurer withCollectionExposure(ExposureConfigurer.AggregateResourceHttpMethodsFilter filter)
Registers the given ExposureConfigurer.AggregateResourceHttpMethodsFilter to be used for collection resources.
}



 */