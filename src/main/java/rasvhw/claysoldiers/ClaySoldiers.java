package rasvhw.claysoldiers;

import  java.util.function.Supplier;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.LivingRenderer;
import net.minecraft.client.render.item.model.ItemModelStandard;
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
import turniplabs.halplibe.helper.ItemBuilder;
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
	public static final Item greyDoll = new ItemBuilder(MOD_ID)
		.setItemModel(item -> new ItemModelStandard(item, null).setRotateWhenRendering().setFull3D())
		.setIcon(MOD_ID + ":item/doll_grey")
		.build(new ItemClayMan("greySoldier", getNextId(), -1));

	public static final Item blackDoll = new ItemBuilder(MOD_ID)
		.setItemModel(item -> new ItemModelStandard(item, null).setRotateWhenRendering().setFull3D())
		.setIcon(MOD_ID + ":item/doll_black")
		.build(new ItemClayMan("blackSoldier", getNextId(), 0));  // Black

	public static final Item redDoll = new ItemBuilder(MOD_ID)
		.setItemModel(item -> new ItemModelStandard(item, null).setRotateWhenRendering().setFull3D())
		.setIcon(MOD_ID + ":item/doll_red")
		.build(new ItemClayMan("redSoldier", getNextId(), 1));  // Red

	public static final Item greenDoll = new ItemBuilder(MOD_ID)
		.setItemModel(item -> new ItemModelStandard(item, null).setRotateWhenRendering().setFull3D())
		.setIcon(MOD_ID + ":item/doll_green")
		.build(new ItemClayMan("greenSoldier", getNextId(), 2));  // Green

	public static final Item brownDoll = new ItemBuilder(MOD_ID)
		.setItemModel(item -> new ItemModelStandard(item, null).setRotateWhenRendering().setFull3D())
		.setIcon(MOD_ID + ":item/doll_brown")
		.build(new ItemClayMan("brownSoldier", getNextId(), 3));  // Brown

	public static final Item blueDoll = new ItemBuilder(MOD_ID)
		.setItemModel(item -> new ItemModelStandard(item, null).setRotateWhenRendering().setFull3D())
		.setIcon(MOD_ID + ":item/doll_blue")
		.build(new ItemClayMan("blueSoldier", getNextId(), 4));  // Blue

	public static final Item purpleDoll = new ItemBuilder(MOD_ID)
		.setItemModel(item -> new ItemModelStandard(item, null).setRotateWhenRendering().setFull3D())
		.setIcon(MOD_ID + ":item/doll_purple")
		.build(new ItemClayMan("purpleSoldier", getNextId(), 5));  // Purple

	public static final Item cyanDoll = new ItemBuilder(MOD_ID)
		.setItemModel(item -> new ItemModelStandard(item, null).setRotateWhenRendering().setFull3D())
		.setIcon(MOD_ID + ":item/doll_cyan")
		.build(new ItemClayMan("cyanSoldier", getNextId(), 6));  // Cyan

	public static final Item lightGrayDoll = new ItemBuilder(MOD_ID)
		.setItemModel(item -> new ItemModelStandard(item, null).setRotateWhenRendering().setFull3D())
		.setIcon(MOD_ID + ":item/doll_lightGray")
		.build(new ItemClayMan("lightGraySoldier", getNextId(), 7));  // Light Gray

	public static final Item grayDoll = new ItemBuilder(MOD_ID)
		.setItemModel(item -> new ItemModelStandard(item, null).setRotateWhenRendering().setFull3D())
		.setIcon(MOD_ID + ":item/doll_gray")
		.build(new ItemClayMan("graySoldier", getNextId(), 8));  // Gray

	public static final Item pinkDoll = new ItemBuilder(MOD_ID)
		.setItemModel(item -> new ItemModelStandard(item, null).setRotateWhenRendering().setFull3D())
		.setIcon(MOD_ID + ":item/doll_pink")
		.build(new ItemClayMan("pinkSoldier", getNextId(), 9));  // Pink

	public static final Item limeDoll = new ItemBuilder(MOD_ID)
		.setItemModel(item -> new ItemModelStandard(item, null).setRotateWhenRendering().setFull3D())
		.setIcon(MOD_ID + ":item/doll_lime")
		.build(new ItemClayMan("limeSoldier", getNextId(), 10));  // Lime

	public static final Item yellowDoll = new ItemBuilder(MOD_ID)
		.setItemModel(item -> new ItemModelStandard(item, null).setRotateWhenRendering().setFull3D())
		.setIcon(MOD_ID + ":item/doll_yellow")
		.build(new ItemClayMan("yellowSoldier", getNextId(), 11));  // Yellow

	public static final Item lightBlueDoll = new ItemBuilder(MOD_ID)
		.setItemModel(item -> new ItemModelStandard(item, null).setRotateWhenRendering().setFull3D())
		.setIcon(MOD_ID + ":item/doll_lightBlue")
		.build(new ItemClayMan("lightBlueSoldier", getNextId(), 12));  // Light Blue

	public static final Item magentaDoll = new ItemBuilder(MOD_ID)
		.setItemModel(item -> new ItemModelStandard(item, null).setRotateWhenRendering().setFull3D())
		.setIcon(MOD_ID + ":item/doll_magenta")
		.build(new ItemClayMan("magentaSoldier", getNextId(), 13));  // Magenta

	public static final Item orangeDoll = new ItemBuilder(MOD_ID)
		.setItemModel(item -> new ItemModelStandard(item, null).setRotateWhenRendering().setFull3D())
		.setIcon(MOD_ID + ":item/doll_orange")
		.build(new ItemClayMan("orangeSoldier", getNextId(), 14));  // Orange

	public static final Item whiteDoll = new ItemBuilder(MOD_ID)
		.setItemModel(item -> new ItemModelStandard(item, null).setRotateWhenRendering().setFull3D())
		.setIcon(MOD_ID + ":item/doll_white")
		.build(new ItemClayMan("whiteSoldier", getNextId(), 15));  // White

	//other
	public static final Item dirtHorse = new ItemBuilder(MOD_ID)
		.setItemModel(item -> new ItemModelStandard(item, null).setRotateWhenRendering().setFull3D())
		.setIcon(MOD_ID + ":item/doll_horse")
		.build(new ItemDirtHorse("dirtHorse", getNextId()));
	public static final Item clayDisruptor = new ItemBuilder(MOD_ID)
		.setItemModel(item -> new ItemModelStandard(item, null).setRotateWhenRendering().setFull3D())
		.setIcon(MOD_ID + ":item/disruptor")
		.build(new ItemClayDisruptor("clayDisruptor",getNextId()));

	//public static final Item dirtHorse = ItemHelper.createItem(MOD_ID, new ItemDirtHorse("dirtHorse",getNextId()));
	//public static final Item clayDisruptor = ItemHelper.createItem(MOD_ID, );

	/*
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
	*/
	@Override
	public void onInitialize() {
		Supplier<EntityRenderer<?>> clayManSupplier = () -> new RenderClayMan(new ModelClayMan(0.0F,13.0F),.125F);
		EntityHelper.createEntity(EntityClayMan.class, 200,"ClaySoldier", clayManSupplier);
		//EntityHelper.Assignment.queueEntityRenderer(EntityClayMan.class, new RenderClayMan(new ModelClayMan(0.0F,13.0F),.125F));
		//EntityHelper.createEntity(EntityClayMan.class, new RenderClayMan(new ModelClayMan(0.0F, 13.0F), 0.125F), 200, "ClaySoldier");
		Supplier<EntityRenderer<?>> dirtHorseSupplier = () -> new RenderDirtHorse(new ModelDirtHorse(0.0F, 12.75F), 0.15F);
		EntityHelper.createEntity(EntityDirtHorse.class,201,"DirtHorse", dirtHorseSupplier);
		//EntityHelper.Assignment.queueEntityRenderer(EntityDirtHorse.class, new RenderDirtHorse(new ModelDirtHorse(0.0F,12.75F),.15F));
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

	@Override
	public void initNamespaces() {
		//unused
	}
}
