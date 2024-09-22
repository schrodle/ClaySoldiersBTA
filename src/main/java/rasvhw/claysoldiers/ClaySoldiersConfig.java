//based on the implementation from https://github.com/FatherCheese/farlanders/blob/7.2/src/main/java/cookie/farlanders/FarlandersConfig.java
package rasvhw.claysoldiers;

import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

public class ClaySoldiersConfig {
	private static final Toml properties = new Toml("Clay Soldiers Config");
	public static TomlConfigHandler cfg;

	static {
		properties.addCategory("Recipes")
			.addEntry("ClayManCount", "The amount of claymen crafted from a single recipe. Defaults to 4.", 4)
			.addEntry("DirtHorseCount", "The amount of dirt horses crafted from a single recipe. Defaults to 4.", 4);
		properties.addCategory("");
	}

}
