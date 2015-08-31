This plugin can convert regions from RainbowZones to ProtectionCore.

Be careful, the two plugin, RainbowZones and ProtectionCore region management works very different, so this plugin only copies the region areas, the region owners, and the true/false flags for strange group, nothing else.

I just wrote it, never tested it, so I don't know anything about it's bugs, just try it, test it and write everything to its forum.

[b][size=18pt][url=http://www.project-rainbow.org/site/plugin-releases/regionconverter]FORUM[/url][/size][/b]


[b][size=14pt]Commands and permissions:[/size][/b]
[b]/rgc [template-name] [-f][/b]
Copies all the RainbowZones regions to the ProtectionCore. You can give the ProtectionCore region creation template if you want.
-f flag means override regions, which name is already used in protectioncore region.
[b]Permission:[/b] regionconverter.use

[b][size=14pt]Dependencies:[/size][/b]
ConfigFile, ProtectionCore, RainbowZones

[b][size=14pt]Changelog:[/size][/b]
[size=12pt][b]1.1.0[/b][/size]
- Added copying members of rZones region
- Disabled copying regions which has same names as region in ProtectionCore
- Added -f flag for force copying these regions