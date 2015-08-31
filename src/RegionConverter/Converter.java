package RegionConverter;

import PluginReference.MC_EventInfo;
import PluginReference.MC_Player;
import PluginReference.MC_Server;
import PluginReference.PluginBase;
import RainbowZones.ZoneFlags;
import RainbowZones.ZoneInfo;
import RainbowZones.ZoneManager;
import RainbowZones._SerializableLocation;
import gyurix.konfigfajl.KFA;
import gyurix.protectioncore.Area;
import gyurix.protectioncore.ProtectionCore.FlagType;
import gyurix.protectioncore.Region;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Converter extends PluginBase
{
  public void onPlayerInput(MC_Player plr, String msg, MC_EventInfo ei)
  {
    if (msg.startsWith("/rgc"))
      if (plr.hasPermission("regionconverter.use")) {
        String[] args = msg.split("\\ ");
        boolean force = KFA.search(args, "-f") != -1;
        Region templ = args.length > 1 ? Region.getTemplate(args[1]) : null;
        for (ZoneInfo z : ZoneManager.mapZoneData.values()) {
          int id = ((List)Region.regnames.get(z.eloc.dimension + 1)).indexOf(z.name);
          if ((!force) && (id != -1)) {
            plr.sendMessage("§cError on converting region §e" + z.name + "§c!\n" + 
              "Region already exists! " + 
              "If you want to override it, use the command §e/rgc [templatename] -f");
          }
          else {
            if (id != -1) {
              ((List)Region.regions.get(z.eloc.dimension + 1)).remove(id);
              ((List)Region.regnames.get(z.eloc.dimension + 1)).remove(id);
            }
            Region r = new Region(new Area(
              (int)Math.floor(z.sloc.x), 
              (int)Math.floor(z.sloc.y), 
              (int)Math.floor(z.sloc.z), 
              (int)Math.floor(z.eloc.x), 
              (int)Math.floor(z.eloc.y), 
              (int)Math.floor(z.eloc.z), 
              z.eloc.dimension), z.name, 0, templ);
            r.players.set(0, new ArrayList(KFA.srv.getPlayerNamesFromUUID(z.ownerUUID)));
            List pls = new ArrayList();
            for (String id1 : z.memberUUIDs.keySet()) {
              pls.addAll(KFA.srv.getPlayerNamesFromUUID(id1));
            }
            BitSet flags = (BitSet)KFA.getField(z, "flags");
            boolean flagBreak = flags.get(ZoneFlags.ALLOW_BREAK.ordinal());
            boolean flagFlow = flags.get(ZoneFlags.ALLOW_FLOW.ordinal());
            boolean flagMobSpawn = flags.get(ZoneFlags.ALLOW_MOB_SPAWN.ordinal());
            boolean flagPvp = flags.get(ZoneFlags.ALLOW_PVP.ordinal());
            Map f = (Map)r.flags.get(0);
            f.put(ProtectionCore.FlagType.BlockBreak, flagBreak ? "+" : "-");
            f.put(ProtectionCore.FlagType.BlockInterract, flagBreak ? "+" : "-");
            f.put(ProtectionCore.FlagType.BlockFlow, flagFlow ? "+" : "-");
            f.put(ProtectionCore.FlagType.EntitySpawn, flagMobSpawn ? "+" : "-");
            f.put(ProtectionCore.FlagType.PVP, flagPvp ? "+" : "-");
          }
        }
        plr.sendMessage("§aConversion complete.");
      }
      else {
        plr.sendMessage("§cYou don't have permission for converting regions");
      }
  }
}

/* Location:           D:\GitHub\RegionConverter.jar
 * Qualified Name:     RegionConverter.Converter
 * JD-Core Version:    0.6.2
 */