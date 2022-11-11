package com.myjava.mydomogest.service;

import com.myjava.mydomogest.entities.TrameInterface;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TrameInterfaceService {

    public TrameInterface generateTrameInterface(){

        // byte[] trame = new byte[]{0x60, 0x63, 0x05, 0x60, 0x18, 0x00, 0x0B, 0x26, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xE5};


        String cdeInterface = "0x60";
        String pcid = generatePcid();
        String trameCanSize = "0x05";
        String[] trameCan = new  String[] {"0x60", "0x18", "0x00", "0x0B", "0x26"};
        String[] dummy = new String[]  {"0x00", "0x00", "0x00", "0x00", "0x00", "0x00", "0x00"};
        String Checksum = "0xE5";

        return new TrameInterface(cdeInterface, pcid, trameCanSize, trameCan, dummy, Checksum);
    }


    public String generatePcid() { // Le Pcid est le dernier octet de l'Ip locale
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        String hostAddress = ip.getHostAddress();
        return "0x" + hostAddress.substring(hostAddress.length() - 2);
    }
}
