package com.myjava.mydomogest.service;

import org.junit.jupiter.api.Assertions;
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

    @Test
    void testConvertOctetEnCoursByte() {
        String octet = "6F";
        byte result = TrameInterfaceService.convertOctetEnCoursByte(octet);
        LOGGER.info("Conversion de {} hexa = {} décimal", octet, result);
        Assertions.assertEquals((byte) 111, result);
    }

    //byte[] trame = new byte[]{0x60, 0x63, 0x05, 0x60, 0x18, 0x00, 0x0B, 0x26, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x71};
    @Test
    void testCchecksun(){
        String cdeInterface = "0x60";
        String pcid = "0x63";
        String trameCanSize = "0x05";
        String[] trameCan = new  String[] {"0x60", "0x18", "0x00", "0x0B", "0x26"};
        String[] dummy = new String[]  {"0x00", "0x00", "0x00", "0x00", "0x00", "0x00", "0x00"};
        String Checksum = "0x71";

        String checksum = trameInterfaceService.checksun(cdeInterface, pcid, trameCanSize, trameCan, dummy);
        LOGGER.info("Checksum = {}", checksum);

    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme