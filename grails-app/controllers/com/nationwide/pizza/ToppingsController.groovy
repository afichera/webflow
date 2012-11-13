package com.nationwide.pizza

class ToppingsController {

    def toppingsFlow = { 
		
		input{
			topping()
		}
		
		selectTopping{
			render(view:"/toppings/selectTopping")
			on("next"){flow.topping = params.topping}.to "finish"
			on("back").to "back"
			
		}

		
		finish(){
			output{
				topping {flow.topping}
			}
		}
		
		
		back()
	}
}
