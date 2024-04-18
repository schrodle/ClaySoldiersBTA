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
import turniplabs.halplibe.helper.EntityHelper;
import turniplabs.halplibe.helper.ItemHelper;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;


public class ClaySoldiers implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
	public static final String MOD_ID = "claysoldiers";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static int waveTime;

	//Items
	public static final Item greyDoll = ItemHelper.createItem(MOD_ID, new ItemClayMan("greySoldier", 20000, 0),"doll_grey.png");
	public static final Item redDoll = ItemHelper.createItem(MOD_ID, new ItemClayMan("redSoldier",20001, 1), "doll_red.png");
	public static final Item yellowDoll = ItemHelper.createItem(MOD_ID, new ItemClayMan("yellowSoldier",20002, 2), "doll_yellow.png");
	public static final Item greenDoll = ItemHelper.createItem(MOD_ID, new ItemClayMan("greenSoldier",20003, 3), "doll_green.png");
	public static final Item blueDoll = ItemHelper.createItem(MOD_ID, new ItemClayMan("blueSoldier",20004, 4), "doll_blue.png");
	public static final Item orangeDoll = ItemHelper.createItem(MOD_ID, new ItemClayMan("orangeSoldier",20005, 5), "doll_orange.png");
	public static final Item purpleDoll = ItemHelper.createItem(MOD_ID, new ItemClayMan("purpleSoldier",20006, 6), "doll_purple.png");
	public static final Item dirtHorse = ItemHelper.createItem(MOD_ID, new ItemDirtHorse("dirtHorse",20007), "doll_horse.png");
	public static final Item clayDisruptor = ItemHelper.createItem(MOD_ID, new ItemClayDisruptor("clayDisruptor",20008), "disruptor.png");

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
		//Grey Doll
		RecipeBuilder.Shaped(MOD_ID)
			.setShape("1", "2")
			.addInput('1', Block.blockClay)
			.addInput('2', Block.sand)
			.create("grey_soldier", new ItemStack(greyDoll, 4));
		//Dirt Horse
		RecipeBuilder.Shaped(MOD_ID)
			.setShape("121","1 1")
			.addInput('1',Block.dirt)
			.addInput('2',Block.sand)
			.create("dirt_horse", new ItemStack(dirtHorse,4));
		//Clay Disruptor
		RecipeBuilder.Shaped(MOD_ID)
			.setShape("121","131")
			.addInput('1',Block.blockClay)
			.addInput('2',Item.stick)
			.addInput('3',Item.dustRedstone)
			.create("clay_disruptor",new ItemStack(clayDisruptor,4));

		//Clay Soldiers
		//RecipeHelper.Crafting.createShapelessRecipe(redDoll, 1, new Object[]{new ItemStack(greyDoll, 1), new ItemStack(Item.dye, 1, 1)});
		//Red
		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(greyDoll,1))
			.addInput(new ItemStack(Item.dye,1,1))
			.create("red_soldier",new ItemStack(redDoll,1));
		//RecipeHelper.Crafting.createShapelessRecipe(yellowDoll, 1, new Object[]{new ItemStack(greyDoll, 1), new ItemStack(Item.dye, 1, 11)});
		//Yellow
		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(greyDoll,1))
			.addInput(new ItemStack(Item.dye,1,11))
			.create("yellow_soldier",new ItemStack(yellowDoll,1));
		//RecipeHelper.Crafting.createShapelessRecipe(greenDoll, 1, new Object[]{new ItemStack(greyDoll, 1), new ItemStack(Item.dye, 1, 2)});
		//Green
		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(greyDoll,1))
			.addInput(new ItemStack(Item.dye,1,2))
			.create("green_soldier",new ItemStack(greenDoll,1));
		//RecipeHelper.Crafting.createShapelessRecipe(blueDoll, 1, new Object[]{new ItemStack(greyDoll, 1), new ItemStack(Item.dye, 1, 4)});
		//Blue
		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(greyDoll,1))
			.addInput(new ItemStack(Item.dye,1,4))
			.create("blue_soldier",new ItemStack(blueDoll,1));
		//RecipeHelper.Crafting.createShapelessRecipe(orangeDoll, 1, new Object[]{new ItemStack(greyDoll, 1), new ItemStack(Item.dye, 1, 14)});
		//Orange
		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(greyDoll,1))
			.addInput(new ItemStack(Item.dye,1,14))
			.create("orange_soldier",new ItemStack(orangeDoll,1));
		//RecipeHelper.Crafting.createShapelessRecipe(purpleDoll, 1, new Object[]{new ItemStack(greyDoll, 1), new ItemStack(Item.dye, 1, 5)});
		//Purple
		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(greyDoll,1))
			.addInput(new ItemStack(Item.dye,1,5))
			.create("purple_soldier",new ItemStack(purpleDoll,1));
	}
}
