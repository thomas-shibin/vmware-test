2.1 Explain the concepts of Default Gateway in IP

	A default gateway is the node in a computer network using the internet protocol suite that serves as the forwarding host (router) to other networks 
	when no other route specification matches the destination IP address of a packet.
	
2.2 Explain the concepts of SNAT and DNAT

	SNAT is an abbreviation for Source Network Address Translation. It is typically used when an internal/private host needs to initiate a connection to 
	an external/public host. The device performing NAT changes the private IP address of the source host to public IP address.

	DNAT stands for Destination Network Address Translation. Destination NAT changes the destination address in the IP header of a packet.
	It may also change the destination port in the TCP/UDP headers. The typical usage of this is to redirect incoming packets with a destination of a 
	public address/port to a private IP address/port inside your network.
	
2.3 What network elements are need to arrive at the above network architecture ; explain their
	configurations in terms at L3/L2
            Details of the IP assignments to be given to each node
	
	Subnet - 255.255.0.0
	/24 means 32 bit address range so the ip can vary from X.X.255.255
	This network can be configured with 2 subnet's also 255.255.255.0 / 255.255.255.0 (192.168.101.X / 102.168.102.X)
	in the above case there will be 2 routers and 2 nodes each connected inside for which the ip address can vary X.X.X.255 times

2.4 Explain ARP
	
	The address resolution protocol (arp) is a protocol used by the Internet Protocol (IP) [RFC826], specifically IPv4, to map IP network addresses to 
	the hardware addresses used by a data link protocol. The protocol operates below the network layer as a part of the interface between the OSI network 
	and OSI link layer.