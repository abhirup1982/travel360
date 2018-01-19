package com.easy.travel.auth.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import java.util.Locale;

@Configuration
public class LoginConfigurations extends WebMvcConfigurerAdapter {

    @Value("{travel360.internalization.url}")
    private String internalization_url;

    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("https://travel360-lang.apps.cumuluslabs.io/lang/fetch/login?locale=" + LocaleContextHolder.getLocale().getLanguage());
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(3600*3);
        return messageSource;
    }

    @Bean(name="localeResolver")
    public LocaleResolver getLocaleResolver(){
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        localeResolver.setCookieName("myAppLocaleCookie");
        localeResolver.setCookieMaxAge(3600);
        return localeResolver;
        /*SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.ENGLISH);
        return slr;*/
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor()).addPathPatterns("/doChangeLocale*");
    }

    /*private String fetchFortuneServiceUrl() {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("FORTUNE-SERVICE", false);
        logger.debug("instanceID: {}", instance.getId());

        String fortuneServiceUrl = instance.getHomePageUrl();
        logger.debug("fortune service homePageUrl: {}", fortuneServiceUrl);

        return fortuneServiceUrl;
    }*/

}
