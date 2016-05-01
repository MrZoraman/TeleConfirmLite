# TeleConfirmLite
TeleConfirmLite is the successor to the TeleConfirmLite that existed back in the hmod days and bukkit days for sponge.

Thanks Ementalo for letting me take the project to sponge!

Read the comments in the release notes for various up-to-date info.

## Installing
Grab the latest jar from the releases page on github and put it in the mods folder of your sponge server.

## Commands
| Command  | Permission Node | Aliases | Description |
| -------- | --------------- | ------- | ----------- |
| tpcreload  | tcl.reload  | tpareload | hot-reloads the yml config files |
| tpcclear  | tcl.tpcclear | tpaclear, tpclear  | Clears a pending teleport request  |
| tpc [PlayerName] | tcl.tpc | tpa  |  Requests to teleport to a player |
| tpchere [PlayerName] | tcl.tpchere | tpahere | Requests the specified player to teleport to you  |
| tpctoggle | tcl.tpctoggle  | tptoggle | Toggles if you are accepting teleport requests or not  |
| tpca | tcl.tpca | tpaccept, tpyes | Accepts a teleport request  |
| tpcd | tcl.tpcd | tpdeny, tpno  | Denies a teleport request |
| tpcback | tcl.tpcback | tpaback  | Returns you to your previous location |

## Permissions
The ```tcl.user``` permission grants all of the above permissions except for ```tcl.reload```.

## Configuration
| Key | Default Value | Description |
| --- | ------------- | ----------- |
| requestTimeout | 30 | The amount of time to pass until a request automatically goes away |
| preventCrossWorldTp | false | Whether or not to let the plugin teleport players across worlds |
