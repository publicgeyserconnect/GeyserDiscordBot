type: text

---

You may be seeing this because you asked about whether we have another regional DNS server.

At the moment, we only have one DNS, and two service regions (U.S. and E.U). The DNS server directs to the primary server in the U.S.

However, after connecting, you may go to "Geyser Servers" and select "EU Geyserconnect Node" to transfer to the EU server.
This will completely transfer you from the U.S. server to the E.U. server.

Please note that this only effects connecting to Java servers, and is not necessary for Bedrock or Geyser-enabled servers.

*The technical jargon:*
Geyser(Connect) takes advantage of a Bedrock feature called a "transfer packet." This essentially tells a player's game to disconnect from the current server and to connet to a different server. This means there is no double-hopping across multiple servers once the transfer has happened. You are removed from the first server and are comepletely on the second server. This is why the region of the GeyserConnect server does not matter if connecting to a Bedrock or Geyser-enabled server.