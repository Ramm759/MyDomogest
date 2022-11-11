package com.myjava.mydomogest.service;

import com.myjava.mydomogest.entities.TrameInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.UnknownHostException;

public class UdpServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UdpService.class);
    TrameInterfaceService trameInterfaceService;
    UdpService udpService;

    @org.testng.annotations.BeforeMethod
    public void setUp() throws UnknownHostException {
        String destination = "192.168.1.53";
        int port = 1470;
        int tailleTrame = 16;
        trameInterfaceService = new TrameInterfaceService();
        udpService = new UdpService(destination, port, tailleTrame);
    }

    @AfterMethod
    public void tearDown() {
        // udpService.send("end");
        udpService.closeSocket();
    }

    @Test
    public void testGetSingleDatagramSocket() {
    }

    @Test
    public void testSend() throws IOException {
        byte[] trame = new byte[]{0x60, 0x64, 0x05, 0x60, 0x18, 0x00, 0x0B, 0x26, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x72};
        udpService.send(trame);
        // assertEquals("hello server", echo);
        // echo = udpService.send("server is working");
        // assertFalse(echo.equals("hello server"));
        udpService.ecoute();
    }

    @Test
    // Ouverture volet parents
    public void testSendTrameInterface() throws IOException {
        // byte[] trame = new byte[]{0x60, pcid, 0x05, 0x60, 0x18, 0x00, 0x0B, 0x26, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, checksum};

        // Création de la trame à envoyer
        String cdeInterface = "0x60";
        String[] trameCan = new String[]{"0x60", "0x18", "0x00", "0x0B", "0x26"};
        TrameInterface trameInterface = trameInterfaceService.generateTrameInterface(cdeInterface, trameCan);

        udpService.send(trameInterface);
        // assertEquals("hello server", echo);
        // echo = udpService.send("server is working");
        // assertFalse(echo.equals("hello server"));
        // udpService.ecoute();
    }

    /*@After
    public void tearDown() throws IOException {
        udpService.send("end");
        udpService.close();
    }*/


    @Test
    public void testEcoute() {
        udpService.ecoute();
    }

    @Test
    public void testCloseSocket() {
    }
}