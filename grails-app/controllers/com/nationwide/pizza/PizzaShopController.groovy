package com.nationwide.pizza

class PizzaShopController {

    def makePizzaFlow = {
		
		selectCrust{
			on("next"){
				flow.crust = params.crust
			}.to "selectSauce"
			/*action{
				// call some service
			}on("success").to ""
			on(Exception)*/
			
						
		
		}
		
		selectSauce{
			onEntry(){flash.sauce=flow.sauce?:"Red"}
			on("next"){
				// persist... 
				
				flow.sauce = params.sauce				
			}.to "selectCheese"
			on("back").to "selectCrust"
		}
		
		selectCheese{
			on("back").to "selectSauce"
			on("next"){
				flow.cheese = params.cheese
			}.to "selectToppings"
		}
		
		selectToppings{
			subflow(controller:"toppings", action:"toppings", input:[topping:{flow.topping?:""}])
			on("finish"){
				
				[topping:currentEvent.attributes.topping]
				
			}.to "done"
			
			on("back").to("selectCheese")
		}
		
		
		done{
			onEntry(){
				flow.id
				// fetch data
			}
			on("back").to "selectToppings"
		}
		
		
	}
}
