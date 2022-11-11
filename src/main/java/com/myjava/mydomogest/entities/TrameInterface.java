package com.myjava.mydomogest.entities;

        /*
        - Octet 0 : Commande d’interface (à ne pas confondre avec la commande CAN).
            * 0x60, 0x50, 0x70, 0x41, 0x51, 0x42, 0x43, 0x52, 0x44, 0x45, 0x54

        - Octet 1 : PCID : Identifie le PC à qui on répond
            En dérogation à ce qui précède, la réception d’une trame Can venant du bus Domocan sera
            envoyée côté PC avec un PCID = 0xFF. En effet, la trame doit parvenir à tous les PC, et
            aucun PC ne peut avoir un IP terminé par 0xFF, car il s’agit de l’adresse broadcast (envoi vers
            tous) du réseau.

        - Octet 2 : Nombre d’octets de Data d’interface de la trame, de 0 à 12
        - Octet 3 à x : Data d’interface = informations utiles de la trame, nombre = octet 2
        - Octets x+1 à 14 : octets inutiles (dummy bytes), toujours à 0x00
        - Octet 15 : Checksum = reste de la division par 256 de la somme des octets 0 à 14



 La trame sera dès lors de la forme suivante :
 Octet 0 : 0x60 = Demande d’envoi d’une trame CAN sur le bus
 Octet 1 : PCID
 Octet 2 : Longueur de la trame CAN, de 4 (ID sans data) à 12 (ID + data)
 Octet 3 : Identificateur CAN SIDH = destinataire Domocan (obligatoire)
 Octet 4 : Identificateur CAN SIDL = commande Domocan (obligatoire)
 Octet 5 : Identificateur CAN EIDH = cible Domocan (obligatoire)
 Octet 6 : Identificateur CAN EIDL = paramètre Domocan (obligatoire)
 Octet 7 : D0 CAN, ou 0x00 si pas d’octet de data
 Octet 8 : D1 CAN, ou 0x00 si moins de 2 octets de data
 Octet 9 : D2 CAN, ou 0x00 si moins de 3 octets de data
 Octet 10 : D3 CAN, ou 0x00 si moins de 4 octets de data
 Octet 11 : D4 CAN, ou 0x00 si moins de 5 octets de data
 Octet 12 : D5 CAN, ou 0x00 si moins de 6 octets de data
 Octet 13 : D6 CAN, ou 0x00 si moins de 7 octets de data
 Octet 14 : D7 CAN, ou 0x00 si moins de 8 octets de data
 Octet 15 : Checksum
 La réponse à cette trame sera une commande 0x50
*/

public class TrameInterface {
    // TODO TAILLE... ???
    private final Integer TAILLE_TRAME_INTERFACE = 16;

    private String cdeInterface;
    private String pcid;
    private String trameCanSize;
    private String[] trameCan;
    private String[] dummy;
    private  String Checksum;

    // TODO final ?


    public TrameInterface(String cdeInterface, String pcid, String trameCanSize, String[] trameCan, String[] dummy, String checksum) {
        this.cdeInterface = cdeInterface;
        this.pcid = pcid;
        this.trameCanSize = trameCanSize;
        this.trameCan = trameCan;
        this.dummy = dummy;
        Checksum = checksum;
    }

    public Integer getTAILLE_TRAME_INTERFACE() {
        return TAILLE_TRAME_INTERFACE;
    }

    public String getCdeInterface() {
        return cdeInterface;
    }

    public void setCdeInterface(String cdeInterface) {
        this.cdeInterface = cdeInterface;
    }

    public String getPcid() {
        return pcid;
    }

    public void setPcid(String pcid) {
        this.pcid = pcid;
    }

    public String getTrameCanSize() {
        return trameCanSize;
    }

    public void setTrameCanSize(String trameCanSize) {
        this.trameCanSize = trameCanSize;
    }

    public String[] getTrameCan() {
        return trameCan;
    }

    public void setTrameCan(String[] trameCan) {
        this.trameCan = trameCan;
    }

    public String[] getDummy() {
        return dummy;
    }

    public void setDummy(String[] dummy) {
        this.dummy = dummy;
    }

    public String getChecksum() {
        return Checksum;
    }

    public void setChecksum(String checksum) {
        Checksum = checksum;
    }
}


