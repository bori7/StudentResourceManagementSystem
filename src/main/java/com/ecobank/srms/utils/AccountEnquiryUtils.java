package com.ecobank.srms.utils;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@Configuration
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class AccountEnquiryUtils implements  EnvironmentAware{

    private static Environment environment;

    private static Logger logger  = LoggerFactory.getLogger(Utils.class);



    public static  String getAffiliateAccountEnquiryUrl(String affiliate) {

        String accountEnquiryUrl = "";



        logger.info("affiliate" + affiliate);



        try {

            logger.info(affiliate + "clustername");



            String clusterName = environment.getProperty(affiliate.toUpperCase());

//            System.out.println(clusterName + "clustername");
            logger.info(affiliate + "clusternamedfemfb.mb lv");

            logger.info(clusterName);



//                    String accountEnquiryUrlClusterPrefix = env.getRequiredProperty(clusterName);

            String accountEnquiryUrlClusterPropertyName = clusterName.concat(".CLUSTER");

            System.out.println(accountEnquiryUrlClusterPropertyName + "      accountEnquiryUrlClusterPropertyName");

            logger.info(accountEnquiryUrlClusterPropertyName + "      accountEnquiryUrlClusterPropertyName");


            accountEnquiryUrl = environment.getProperty(accountEnquiryUrlClusterPropertyName);

            logger.info("accout URL:" + accountEnquiryUrl);

            return accountEnquiryUrl;

        } catch (Exception e) {

            logger.error(e.getMessage());

            e.printStackTrace();

        }

        return accountEnquiryUrl;

    }






        @Override public void setEnvironment(Environment environment) {
            AccountEnquiryUtils.environment = environment;
        }
}
