package com.example.mydomogest.travail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class UdpTest {
    private final String destination = "192.168.1.53";
    private final int port = 1470;
    private final int tailleTrame = 16;


    UdpService udpService;

    @Before
    public void setup() throws SocketException, UnknownHostException {
        // new EchoServer().start();
        udpService = new UdpService(destination, port, tailleTrame);
    }

    @Test
    public void whenCanSendAndReceivePacket_thenCorrect() throws IOException {
        udpService.send("60,03,05,60,18,00,0B,26,00,00,00,00,00,00,00,11");
        // assertEquals("hello server", echo);
        // echo = udpService.send("server is working");
        // assertFalse(echo.equals("hello server"));
    }

    /*@After
    public void tearDown() throws IOException {
        udpService.send("end");
        udpService.close();
    }*/
}
