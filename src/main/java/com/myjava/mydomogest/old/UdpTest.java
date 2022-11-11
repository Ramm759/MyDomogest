package com.myjava.mydomogest.old;

import com.myjava.mydomogest.entities.TrameInterface;
import com.myjava.mydomogest.service.TrameInterfaceService;
import com.myjava.mydomogest.service.UdpService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class UdpTest {


    UdpService udpService;

    @Before
    public void setup() throws SocketException, UnknownHostException {
        String destination = "192.168.1.53";
        int port = 1470;
        int tailleTrame = 16;
        udpService = new UdpService(destination, port, tailleTrame);
    }

    @Test
    public void ouvertureVolet() throws IOException {
        // byte[] trame = new byte[]{0x60, 0x63, 0x05, 0x60, 0x18, 0x00, 0x0B, 0x26, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x71};

        // Création de la trameString à envoyer
        TrameInterfaceService trameInterfaceService = new TrameInterfaceService();
        TrameInterface trameInterface = trameInterfaceService.generateTrameInterface();

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
}
