(function($, Coral){
    "use strict";
    console.log("=======CLIENTLIBS LOADED===============");

    var registry = $(window).adaptTo("foundation-registry");

    //Validator
    registry.register("foundation.validation.validator", {
        selector: "[data-validation=aemsite-multifield]",
        validate: function(element){
            var el= $(element);

			let max = el.data("max-items");
            let min = el.data("min-items");

            let items=el.children("coral-multifield-item").length;

            console.log("{} : {} :{} ",max,min,items);

            if(items>max){
                return "You can add maximum of "+max+" items. You are adding "+items+" items.";
            }

            if(items<min){
                return "Please add minimum "+min+" items.";
            }

        }
    });


    //Validator for First Name caps onlyy
    registry.register("foundation.validation.validator",{
        selector:"[data-validation=aemsite-firstname]",
        validate:function(element){
			var el = $(element);

            let pattern=/[0-9a-z]/;
            let val = el.val();

            if(pattern.test(val)){
                return "Pease add capital letters only";
            }
        }
    });

})(jQuery, Coral);






