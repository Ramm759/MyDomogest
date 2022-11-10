package com.myjava.mydomogest.service;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InterfaceTrameService {

    public byte generatePcid() { // Le Pcid est le dernier octet de l'Ip locale
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        String hostAddress = ip.getHostAddress();
        byte pcid = (byte) Integer.parseInt(hostAddress.substring(hostAddress.length() - 2));
        return pcid;
    }
}
