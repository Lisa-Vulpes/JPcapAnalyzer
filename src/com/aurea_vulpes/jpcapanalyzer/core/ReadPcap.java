package com.aurea_vulpes.jpcapanalyzer.core;

import org.jnetpcap.Pcap;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;

public class ReadPcap {
	private String pcapFile;

	public ReadPcap(String pcapFile) {
		this.pcapFile = pcapFile;
	}

	public void execute() {
		StringBuilder errorBuffer = new StringBuilder();

		Pcap pcap = Pcap.openOffline(this.pcapFile, errorBuffer);
		if (pcap == null) {
			System.err.println("Error while opening pcap file.");
			System.err.println(errorBuffer.toString());
			return;
		}

		PcapPacketHandler<String> pcapPacketHandler = new PcapPacketHandler<String>() {
			public void nextPacket(PcapPacket pcapPacket, String user) {
				// Load packets.
			}
		};

		try {
			pcap.loop(Pcap.LOOP_INFINITE, pcapPacketHandler, "");
		} finally {
			pcap.close();
		}
	}
}
