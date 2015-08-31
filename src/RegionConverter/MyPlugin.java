package RegionConverter;

import PluginReference.PluginBase;
import PluginReference.PluginInfo;
import java.io.PrintStream;

public class MyPlugin extends PluginBase
{
  PluginInfo info;
  public static PluginBase pl;

  public PluginInfo getPluginInfo()
  {
    PluginInfo inf = new PluginInfo();
    inf.eventSortOrder = 1000000000000000.0D;
    inf.description = "Plugin for region converting from RainbowZones to ProtectionCore";
    pl = this; inf.ref = this;
    return this.info = inf;
  }

  public void onServerFullyLoaded() {
    try {
      this.info.ref = new Converter();
      System.out.println("[RegionConverter] Started succesfully");
    }
    catch (Throwable e) {
      System.out.println("[RegionConverter] Error on startup, you need to add ConfigFile, ProtectionCore and RainbowZones plugins for using this plugin.");
    }
  }
}

/* Location:           D:\GitHub\RegionConverter.jar
 * Qualified Name:     RegionConverter.MyPlugin
 * JD-Core Version:    0.6.2
 */