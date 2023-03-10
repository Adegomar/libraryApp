package com.adegomar.springbootlibrary.config;

import com.adegomar.springbootlibrary.entity.Book;
import com.adegomar.springbootlibrary.entity.Message;
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
        config.exposeIdsFor(Message.class);

        disableHttpMethods(Book.class, config, theUnsupportedActions);
        disableHttpMethods(Review.class, config, theUnsupportedActions);
        disableHttpMethods(Message.class, config, theUnsupportedActions);

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



// chcemy wy????czy?? mo??liwo???? dodawania, edytowania i kasowania danych w ksi????ce
// w tym celu musimy okre??li?? to w metodzie, a nast??pnie zaaplikowa?? w innej metodzie
/*
ustawiamy theAllowedOrigins, kt??re pozwoli nam po????czy?? si?? z frontem i wsadzimy zaraz

tworzymy metod?? do konfiguracji
Wpisujemy do niej HttpMethodArray[], kt??ra jest po to ??eby ore??li?? jakie akcje nas nie interesuj??

config.exposeIdsFor jest po to aby z naszej bazy danych zaczerpn???? id z przedmiot??w klasy Book
(jest to widoczne potem w kodzie na stronie localhosta) robi si?? to po co, ??eby
u??ywa?? primary key dla funkcjonalno??ci tu i na froncie. Tym sposobem wiemy dok??adnie
jak?? ksi????k?? b??dziemy u??ywa??

disableHttpMethods to metoda, kt??ra zdefiniowana jest poni??ej

disableHttpMethods(theClass do jakiej klasy mamy zaimplementowa??; config jako parametr potrzebny do
konfiguracji bazy danych???; HttpMethod[] ma w sobie to czego nie chcemy){

     ?????
.komenda na uzyskanie ekspozycji do konfiguracji
.dla okre??lonej klasy
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