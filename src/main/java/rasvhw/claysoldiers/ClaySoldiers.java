package rasvhw.claysoldiers;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.LivingRenderer;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rasvhw.claysoldiers.entities.EntityClayMan;
import rasvhw.claysoldiers.entities.EntityDirtHorse;
import rasvhw.claysoldiers.items.ItemClayDisruptor;
import rasvhw.claysoldiers.items.ItemClayMan;
import rasvhw.claysoldiers.items.ItemDirtHorse;
import rasvhw.claysoldiers.model.ModelClayMan;
import rasvhw.claysoldiers.model.ModelDirtHorse;
import rasvhw.claysoldiers.model.RenderClayMan;
import rasvhw.claysoldiers.model.RenderDirtHorse;
import rasvhw.claysoldiers.recipes.ClaySoldiersRecipes;
import turniplabs.halplibe.helper.EntityHelper;
import turniplabs.halplibe.helper.ItemHelper;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;


public class ClaySoldiers implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
	public static final String MOD_ID = "claysoldiers";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static int waveTime;
	public static int nextId=26999;

	public static int getNextId(){
		nextId++;
		return nextId;
	}

	//Items
	//Dolls
	/*
		0-Black, 1-Red, 2-Green, 3-Brown, 4-Blue, 5-Purple, 6-Cyan, 7-Light Gray, 8-Gray, 9-Pink, 10-Lime, 11-Yellow, 12-Light Blue, 13-Magenta, 14-Orange, 15-White
	*/
	public static final Item greyDoll = ItemHelper.createItem(MOD_ID, new ItemClayMan("greySoldier", getNextId(), -1),"doll_grey.png");
	public static final Item blackDoll=ItemHelper.createItem(MOD_ID,new ItemClayMan("blackSoldier",getNextId(),0),"doll_black.png");
	public static final Item redDoll = ItemHelper.createItem(MOD_ID, new ItemClayMan("redSoldier",getNextId(), 1), "doll_red.png");
	public static final Item greenDoll = ItemHelper.createItem(MOD_ID, new ItemClayMan("greenSoldier",getNextId(), 2), "doll_green.png");
	public static final Item brownDoll=ItemHelper.createItem(MOD_ID,new ItemClayMan("brownSoldier",getNextId(),3),"doll_brown.png");
	public static final Item blueDoll = ItemHelper.createItem(MOD_ID, new ItemClayMan("blueSoldier",getNextId(), 4), "doll_blue.png");
	public static final Item purpleDoll = ItemHelper.createItem(MOD_ID, new ItemClayMan("purpleSoldier",getNextId(), 5), "doll_purple.png");
	public static final Item cyanDoll=ItemHelper.createItem(MOD_ID,new ItemClayMan("cyanSoldier",getNextId(),6),"doll_cyan.png");
	public static final Item lightGrayDoll=ItemHelper.createItem(MOD_ID,new ItemClayMan("lightGraySoldier",getNextId(),7),"doll_lightGray.png");
	public static final Item grayDoll=ItemHelper.createItem(MOD_ID,new ItemClayMan("graySoldier",getNextId(),8),"doll_gray.png");
	public static final Item pinkDoll=ItemHelper.createItem(MOD_ID,new ItemClayMan("pinkSoldier",getNextId(),9),"doll_pink.png");
	public static final Item limeDoll=ItemHelper.createItem(MOD_ID,new ItemClayMan("limeSoldier",getNextId(),10),"doll_lime.png");
	public static final Item yellowDoll = ItemHelper.createItem(MOD_ID, new ItemClayMan("yellowSoldier",getNextId(), 11), "doll_yellow.png");
	public static final Item lightBlueDoll=ItemHelper.createItem(MOD_ID,new ItemClayMan("lightBlueSoldier",getNextId(),12),"doll_lightBlue.png");
	public static final Item magentaDoll=ItemHelper.createItem(MOD_ID,new ItemClayMan("magentaSoldier",getNextId(),13),"doll_magenta.png");
	public static final Item orangeDoll = ItemHelper.createItem(MOD_ID, new ItemClayMan("orangeSoldier",getNextId(), 14), "doll_orange.png");
	public static final Item whiteDoll=ItemHelper.createItem(MOD_ID,new ItemClayMan("whiteSoldier",getNextId(),15),"doll_white.png");
	//Other
	public static final Item dirtHorse = ItemHelper.createItem(MOD_ID, new ItemDirtHorse("dirtHorse",getNextId()), "doll_horse.png");
	public static final Item clayDisruptor = ItemHelper.createItem(MOD_ID, new ItemClayDisruptor("clayDisruptor",getNextId()), "disruptor.png");

	@Override
	public void onInitialize() {
		EntityHelper.Core.createEntity(EntityClayMan.class, 200,"ClaySoldier");
		EntityHelper.Client.assignEntityRenderer(EntityClayMan.class, new RenderClayMan(new ModelClayMan(0.0F,13.0F),.125F));
		//EntityHelper.createEntity(EntityClayMan.class, new RenderClayMan(new ModelClayMan(0.0F, 13.0F), 0.125F), 200, "ClaySoldier");
		EntityHelper.Core.createEntity(EntityDirtHorse.class,201,"DirtHorse");
		EntityHelper.Client.assignEntityRenderer(EntityDirtHorse.class, new RenderDirtHorse(new ModelDirtHorse(0.0F,12.75F),.15F));
		//EntityHelper.createEntity(EntityDirtHorse.class, new RenderDirtHorse(new ModelDirtHorse(0.0F, 12.75F), 0.15F), 201, "DirtHorse");

		LOGGER.info("Initialized!");
		LOGGER.info("This is a port of Clay Soldiers to Better than Adventure!");
		LOGGER.info("Get the original mod here: https://mcarchive.net/mods/claysoldiers?gvsn=b1.7.3");
	}

	public static void updateWaveTime() {
		if (waveTime > 0) {
			--waveTime;
		}
	}

	@Override
	public void beforeGameStart() {

	}

	@Override
	public void afterGameStart() {

	}

	@Override
	public void onRecipesReady() {
		new ClaySoldiersRecipes().initializeRecipes();
	}
}
