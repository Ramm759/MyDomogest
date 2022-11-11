package com.myjava.mydomogest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TrameInterfaceServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UdpService.class);

    TrameInterfaceService trameInterfaceService = new TrameInterfaceService();

    @Test
    public void testGeneratePcid() {
        // Le Pcid est le dernier octet de l'Ip locale

        // Récupération adresse locale
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        String hostAddress = ip.getHostAddress();
        LOGGER.info("Ip locale : {}", hostAddress);


        String pcid = trameInterfaceService.generatePcid();
        LOGGER.info("Pcid (dernier octet de l'Ip locale) : {}", pcid);


        // Assert.assertEquals(result, "replaceMeWithExpectedResult");
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme