//Implementing a Java Program to Convert Between Domain Name and IP Address.

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SocketProgram_Q3 {
    public static void main(String args[]) throws UnknownHostException {
        if (args.length > 0) {
            String domainName = args[0];

            // Get all IP addresses associated with the specified domain name
            InetAddress[] inetAddresses = InetAddress.getAllByName(domainName);

            // Print each IP address
            for (InetAddress address : inetAddresses) {
                System.out.println(address);
            }
        } else {
            // If no command-line argument is provided, print the local host's IP address
            System.out.println(InetAddress.getLocalHost());
        }
    }
}


// It checks if any command-line arguments are provided (args.length > 0). If arguments are present, it assumes that the first argument is the domain name for which you want to obtain IP addresses.

// If a domain name is provided, it uses InetAddress.getAllByName(domainName) to retrieve an array of InetAddress objects, each representing an IP address associated with the specified domain name.

// It then prints each IP address obtained from the DNS resolution.

// If no command-line argument is provided, it prints the IP address of the local host using InetAddress.getLocalHost().