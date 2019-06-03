import json 

#create recipe dictionary based on json file
recipe_list = json.load(open("hackscrecipes.json"))


def match_ingredients(availible_foods: list) -> set:

	possible_foods = {}

	for recipe in recipe_list:
		num_matches = 0
		for food in availible_foods:
			found = False
			for ingredient in recipe["ingredients"]:
				if food in ingredient:
					found = True
			if(found): 
				num_matches += 1

		if num_matches / len(recipe["ingredients"]) >= 0.66:
			possible_foods[recipe["id"]] = (recipe["ingredients"])

	return possible_foods
