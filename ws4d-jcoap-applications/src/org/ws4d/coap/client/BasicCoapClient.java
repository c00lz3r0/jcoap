package org.ws4d.coap.client;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.ws4d.coap.connection.DefaultCoapChannelManager;
import org.ws4d.coap.interfaces.CoapChannel;
import org.ws4d.coap.interfaces.CoapChannelManager;
import org.ws4d.coap.interfaces.CoapClient;
import org.ws4d.coap.interfaces.CoapMessage;
import org.ws4d.coap.messages.CoapRequest.CoapRequestCode;
import org.ws4d.coap.messages.CoapResponse;

public class BasicCoapClient implements CoapClient {
    private static final String SERVER_ADDRESS = "139.30.201.221";
    private static final int PORT = 61616;
    static int counter = 0;
    CoapChannelManager channelManager = null;
    CoapChannel clientChannel = null;

    public static void main(String[] args) {
        System.out.println("Start CoAP Client");
        BasicCoapClient client = new BasicCoapClient();
        client.channelManager = DefaultCoapChannelManager.getInstance();
        client.runTestClient();
    }
    
    public void runTestClient(){
    	try {
			clientChannel = channelManager.connect(this, InetAddress.getByName(SERVER_ADDRESS), PORT);
			CoapMessage coapRequest = clientChannel.createRequest(true, CoapRequestCode.GET);
			clientChannel.sendMessage(coapRequest);
			System.out.println("Sent Request");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public void onConnectionFailed(CoapChannel channel, boolean notReachable, boolean resetByServer) {
		System.out.println("Connection Failed");
	}

	@Override
	public void onResponse(CoapChannel channel, CoapResponse response) {
		System.out.println("Received response");
	}
}
