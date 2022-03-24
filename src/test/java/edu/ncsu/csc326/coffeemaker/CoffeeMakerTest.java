package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

public class CoffeeMakerTest2 {
	
	private Recipe recipe;
	private Recipe[] recipeArray;
	private CoffeeMaker coffeeMaker;
	
	@Before
	public void setUp() throws Exception {
		this.recipeArray = new Recipe[4];
		this.recipe = new Recipe();
		this.recipe.setName("Test Recipe");
		this.recipe.setPrice("4");
		this.recipe.setAmtCoffee("5");
		this.recipe.setAmtMilk("5");
		this.recipe.setAmtSugar("5");
		this.recipeArray[0] = this.recipe;
		this.coffeeMaker = new CoffeeMaker();
		this.coffeeMaker.addRecipe(recipe);
	}

	@After
	public void tearDown() throws Exception {
		this.recipeArray = null;
		this.recipe = null;
		this.coffeeMaker = null;
	}

	@Test
	public void testCoffeeMaker() {
		Recipe[] expectedRecipeArray = this.recipeArray;
		Recipe[] actualRecipeArray = this.coffeeMaker.getRecipes();
		String expectedInventoryString = "Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n";
		String actualInventoryString = this.coffeeMaker.checkInventory();
		assertArrayEquals(expectedRecipeArray, actualRecipeArray);
		assertEquals(expectedInventoryString, actualInventoryString);
	}

	@Test
	public void testAddRecipe() {
		this.coffeeMaker.addRecipe(recipe);
		Recipe[] expectedRecipeArray = this.recipeArray;
		Recipe[] actualRecipeArray = this.coffeeMaker.getRecipes();
		assertArrayEquals(expectedRecipeArray, actualRecipeArray);
	}

	@Test
	public void testDeleteRecipe() {
		String expectedRecipeName = this.recipeArray[0].getName();
		String actualRecipeName = this.coffeeMaker.deleteRecipe(0);
		this.recipeArray[0] = null;
		Recipe[] expectedRecipeArray = this.recipeArray;
		Recipe[] actualRecipeArray = this.coffeeMaker.getRecipes();
		assertEquals(expectedRecipeName, actualRecipeName);
		assertArrayEquals(expectedRecipeArray, actualRecipeArray);
	}

	@Test
	public void testEditRecipe() {
		Recipe newRecipe = new Recipe();
		newRecipe.setName("Test New Recipe");
		String expectedOldRecipeName = this.recipeArray[0].getName();
		String actualOldRecipeName = this.coffeeMaker.editRecipe(0, newRecipe);
		this.recipeArray[0] = newRecipe;
		Recipe[] expectedRecipeArray = this.recipeArray;
		Recipe[] actualRecipeArray = this.coffeeMaker.getRecipes();
		assertEquals(expectedOldRecipeName, actualOldRecipeName);
		assertArrayEquals(expectedRecipeArray, actualRecipeArray);
	}

	@Test
	public void testAddInventory() {
		try {
			this.coffeeMaker.addInventory("15", "15", "15", "15");
		} catch (InventoryException e) {
			e.printStackTrace();
			fail();
		}
		String expectedInventoryString = "Coffee: 30\nMilk: 30\nSugar: 30\nChocolate: 30\n";
		String actualInventoryString = this.coffeeMaker.checkInventory();
		assertEquals(expectedInventoryString, actualInventoryString);
	}

	@Test
	public void testCheckInventory() {
		String expectedInventoryString = "Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n";
		String actualInventoryString = this.coffeeMaker.checkInventory();
		assertEquals(expectedInventoryString, actualInventoryString);
	}

	@Test
	public void testMakeCoffee() {
		int expectedInt = 1;
		int actualInt = this.coffeeMaker.makeCoffee(0, 5);
		String expectedInventoryString = "Coffee: 10\nMilk: 10\nSugar: 10\nChocolate: 15\n";
		String actualInventoryString = this.coffeeMaker.checkInventory();
		assertEquals(expectedInt, actualInt);
		assertEquals(expectedInventoryString, actualInventoryString);
	}
	
	@Test
	public void testMakeCoffeeInsufficientInventory() {
		Recipe badRecipe = new Recipe();
		badRecipe.setName("Test Bad Recipe");
		try {
			badRecipe.setPrice("4");
			badRecipe.setAmtCoffee("20");
		} catch (RecipeException e) {
			fail();
		}
		this.coffeeMaker.addRecipe(badRecipe);
		int expectedInt = 5;
		int actualInt = this.coffeeMaker.makeCoffee(1, 5);
		String expectedInventoryString = "Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n";
		String actualInventoryString = this.coffeeMaker.checkInventory();
		assertEquals(expectedInt, actualInt);
		assertEquals(expectedInventoryString, actualInventoryString);
	}
	
	@Test
	public void testMakeCoffeeInvalidIndex() {
		int expectedInt = 4;
		int actualInt = this.coffeeMaker.makeCoffee(1, 4);
		String expectedInventoryString = "Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n";
		String actualInventoryString = this.coffeeMaker.checkInventory();
		assertEquals(expectedInt, actualInt);
		assertEquals(expectedInventoryString, actualInventoryString);
	}

	@Test
	public void testMakeCoffeeInvalidAmtPaid() {
		int expectedInt = 2;
		int actualInt = this.coffeeMaker.makeCoffee(0, 2);
		String expectedInventoryString = "Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n";
		String actualInventoryString = this.coffeeMaker.checkInventory();
		assertEquals(expectedInt, actualInt);
		assertEquals(expectedInventoryString, actualInventoryString);
	}
	
	@Test
	public void testGetRecipes() {
		Recipe[] expected = this.recipeArray;
		Recipe[] actual = this.coffeeMaker.getRecipes();
		assertArrayEquals(expected, actual);
	}

}