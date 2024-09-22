package rasvhw.claysoldiers.recipes;

import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import rasvhw.claysoldiers.items.ItemClayMan;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.util.RecipeEntrypoint;

import static rasvhw.claysoldiers.ClaySoldiers.MOD_ID;
import static rasvhw.claysoldiers.ClaySoldiers.*;

public class ClaySoldiersRecipes implements RecipeEntrypoint {

	@Override
	public void onRecipesReady() {
		//Grey Doll
		RecipeBuilder.Shaped(MOD_ID)
			.setShape("1", "2")
			.addInput('1', Block.blockClay)
			.addInput('2', Block.soulsand)
			.create("grey_soldier", new ItemStack(greyDoll, 4));
		//Dirt Horse
		RecipeBuilder.Shaped(MOD_ID)
			.setShape("121","1 1")
			.addInput('1',Block.dirt)
			.addInput('2',Block.soulsand)
			.create("dirt_horse", new ItemStack(dirtHorse,4));
		//Clay Disruptor
		RecipeBuilder.Shaped(MOD_ID)
			.setShape("121","131")
			.addInput('1',Block.blockClay)
			.addInput('2', Item.stick)
			.addInput('3',Item.dustRedstone)
			.create("clay_disruptor",new ItemStack(clayDisruptor,4));

		//Clay Soldiers
		/*
		0-Black
		1-Red
		2-Green
		3-Brown
		4-Blue
		5-Purple
		6-Cyan
		7-Light Gray
		8-Gray
		9-Pink
		10-Lime
		11-Yellow
		12-Light Blue
		13-Magenta
		14-Orange
		15-White
		 */
		//Black
		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(greyDoll,1))
			.addInput(new ItemStack(Item.dye,1,0))
			.create("black_soldier",new ItemStack(blackDoll,1));
		//Red
		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(greyDoll,1))
			.addInput(new ItemStack(Item.dye,1,1))
			.create("red_soldier",new ItemStack(redDoll,1));
		//Green
		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(greyDoll,1))
			.addInput(new ItemStack(Item.dye,1,2))
			.create("green_soldier",new ItemStack(greenDoll,1));
		//Brown
		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(greyDoll,1))
			.addInput(new ItemStack(Item.dye,1,3))
			.create("brown_soldier",new ItemStack(brownDoll,1));
		//Blue
		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(greyDoll,1))
			.addInput(new ItemStack(Item.dye,1,4))
			.create("blue_soldier",new ItemStack(blueDoll,1));
		//Purple
		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(greyDoll,1))
			.addInput(new ItemStack(Item.dye,1,5))
			.create("purple_soldier",new ItemStack(purpleDoll,1));
		//Cyan
		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(greyDoll,1))
			.addInput(new ItemStack(Item.dye,1,6))
			.create("cyan_soldier",new ItemStack(cyanDoll,1));
		//Light Gray
		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(greyDoll,1))
			.addInput(new ItemStack(Item.dye,1,7))
			.create("lightGray_soldier",new ItemStack(lightGrayDoll,1));
		//Gray
		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(greyDoll,1))
			.addInput(new ItemStack(Item.dye,1,8))
			.create("gray_soldier",new ItemStack(grayDoll,1));
		//Pink
		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(greyDoll,1))
			.addInput(new ItemStack(Item.dye,1,9))
			.create("pink_soldier",new ItemStack(pinkDoll,1));
		//Lime
		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(greyDoll,1))
			.addInput(new ItemStack(Item.dye,1,10))
			.create("lime_soldier",new ItemStack(limeDoll,1));
		//Yellow
		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(greyDoll,1))
			.addInput(new ItemStack(Item.dye,1,11))
			.create("yellow_soldier",new ItemStack(yellowDoll,1));
		//Light Blue
		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(greyDoll,1))
			.addInput(new ItemStack(Item.dye,1,12))
			.create("lightBlue_soldier",new ItemStack(lightBlueDoll,1));
		//Magenta
		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(greyDoll,1))
			.addInput(new ItemStack(Item.dye,1,13))
			.create("magenta_soldier",new ItemStack(magentaDoll,1));
		//Orange
		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(greyDoll,1))
			.addInput(new ItemStack(Item.dye,1,14))
			.create("orange_soldier",new ItemStack(orangeDoll,1));
		//White
		RecipeBuilder.Shapeless(MOD_ID)
			.addInput(new ItemStack(greyDoll,1))
			.addInput(new ItemStack(Item.dye,1,15))
			.create("white_soldier",new ItemStack(whiteDoll,1));

	}


	@Override
	public void initNamespaces() {
		//RecipeEntrypoint.super.initNamespaces();
		RecipeBuilder.initNameSpace(MOD_ID);
	}

	public void initializeRecipes(){
		onRecipesReady();
		//initNamespaces();
	}
}
