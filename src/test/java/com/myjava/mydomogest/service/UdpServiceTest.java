package com.myjava.mydomogest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.UnknownHostException;

public class UdpServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UdpService.class);
    UdpService udpService;

    @org.testng.annotations.BeforeMethod
    public void setUp() throws UnknownHostException {
        String destination = "192.168.1.53";
        int port = 1470;
        int tailleTrame = 16;
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
        byte[] trame = new byte[]{0x60, 0x03, 0x05, 0x60, 0x18, 0x00, 0x0B, 0x26, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x11};
        udpService.send(trame);
        // assertEquals("hello server", echo);
        // echo = udpService.send("server is working");
        // assertFalse(echo.equals("hello server"));
        udpService.ecoute();
    }

    @Test
    public void testSendTrameInterface() throws IOException {
        udpService.send();
    }

    @Test
    public void testEcoute() {
        udpService.ecoute();
    }

    @Test
    public void testCloseSocket() {
    }
}