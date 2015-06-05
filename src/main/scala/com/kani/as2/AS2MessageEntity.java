package com.kani.as2;

import java.net.InetAddress;

/**
 * Created by kanishka on 5/18/15.
 */
public class AS2MessageEntity {
    private InetAddress sourceIp;
    private int sourcePort;
    private InetAddress destinationIp;
    private int destinationPort;

    public InetAddress getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(InetAddress sourceIp) {
        this.sourceIp = sourceIp;
    }

    public int getSourcePort() {
        return sourcePort;
    }

    public void setSourcePort(int sourcePort) {
        this.sourcePort = sourcePort;
    }

    public InetAddress getDestinationIp() {
        return destinationIp;
    }

    public void setDestinationIp(InetAddress destinationIp) {
        this.destinationIp = destinationIp;
    }

    public int getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(int destinationPort) {
        this.destinationPort = destinationPort;
    }
}
