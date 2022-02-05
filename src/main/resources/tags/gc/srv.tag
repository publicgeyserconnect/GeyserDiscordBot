type: text

---

`UnknowHostException` is an issue with what is known as an SRV record. An SRV record redirects the game to aspecific IP address and port that are not the same as the ones you specify.
"Please wait until you are logged in" followed by a time out *may* also be a result of this same issue.

The only current way around this is to retrieve the direct IP address and Port numbers of the server. This may be achieved as follows:
`1` Goto https://mcsrvstat.us
`2` Enter the server address you are using in the box near the top.
`3` Click on the "Get Status" button.
`4` Find the Bold text that reads "Debug Info" and click "show debug info."
`5` Locate "IP address" and use that instead of the server name.
`6` Use the "Port" number that is listed underneath it instead of 25565.
`7` connect to your server with that info instead.
