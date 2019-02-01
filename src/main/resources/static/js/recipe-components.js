$(function () {
    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});
var rateButtonArray = ["#rate1Btn","#rate2Btn","#rate3Btn","#rate4Btn","#rate5Btn"];
var rateButtonsColors = [];
for(var i = 0; i<5; i++){
    rateButtonsColors.push($(rateButtonArray[i]).css("background-color"));
}


function hoverIn(button){
    for(var i = 0; i<rateButtonArray.indexOf(button)+1; i++){
        $(rateButtonArray[i]).css("background-color","red");
    }
    for(var j = rateButtonArray.indexOf(button)+1; j <rateButtonArray.length;j++){
        $(rateButtonArray[j]).css("background-color","#dddddd");
    }
}
function hoverOut(button){
    for(var i = 0; i<rateButtonArray.indexOf(button)+1; i++){
        $(rateButtonArray[i]).css("background-color",rateButtonsColors[i]);
    }
    for(var j = rateButtonArray.indexOf(button)+1; j <rateButtonArray.length;j++){
        $(rateButtonArray[j]).css("background-color",rateButtonsColors[j]);
    }
}
function rateButtonClick(button){
    for(var i = 0; i<rateButtonArray.indexOf(button)+1; i++){
        rateButtonsColors[i] = "red";
    }
    for(var j = rateButtonArray.indexOf(button)+1; j <rateButtonArray.length;j++){
        rateButtonsColors[j] = "#dddddd";
        $(rateButtonArray[j]).css("background-color",rateButtonsColors[j]);
    }
}
function rateRecipeAjaxCall(button){
    var id = $(button).parent().parent().parent().parent().attr('name');
    var urlString = '/api/rateRecipe/recipe'+id+'?rateVal=';
    var rateVal = rateButtonArray.indexOf(button)+1;
    console.log("dziala?");
    $.ajax({
        type: "post",
        url: urlString+rateVal,
        success:function (){
            console.log("elo, elo");
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert(xhr.status);
            alert(thrownError);
        }
    });
}


$(document).ready(function(){
    var urlString = '/api/getRecipeRateFromUser/recipe' + $("#rowWithId").attr('name');
    $.ajax({
        type:'get',
        url:urlString,
        success:function(data){
            for(var i = 0;i<data;i++){
                $(rateButtonArray[i]).css("background-color","red");
                rateButtonsColors[i] = "red";
            }
        }
    })
});
$("#madeItButton").click(function(){
    var id = $(this).parent().parent().parent().attr('name');
    var urlString = '/api/prepareRecipe/recipe' + id;
    $.ajax({
        type: "GET",
        url: urlString,
        success: function (data) {
            $("#recipePreparationNumberLabel").text('Made it: ' + data)
        }
    });
});

$("#rate1Btn").hover(function(){hoverIn(rateButtonArray[0])},function(){hoverOut(rateButtonArray[0])});
$("#rate2Btn").hover(function(){hoverIn(rateButtonArray[1])},function(){hoverOut(rateButtonArray[1])});
$("#rate3Btn").hover(function(){hoverIn(rateButtonArray[2])},function(){hoverOut(rateButtonArray[2])});
$("#rate4Btn").hover(function(){hoverIn(rateButtonArray[3])},function(){hoverOut(rateButtonArray[3])});
$("#rate5Btn").hover(function(){hoverIn(rateButtonArray[4])},function(){hoverOut(rateButtonArray[4])});

$("#rate1Btn").click(function(){
    rateButtonClick(rateButtonArray[0]);
    rateRecipeAjaxCall(rateButtonArray[0]);
});
$("#rate2Btn").click(function(){
    rateButtonClick(rateButtonArray[1]);
    rateRecipeAjaxCall(rateButtonArray[1]);
});
$("#rate3Btn").click(function(){
    rateButtonClick(rateButtonArray[2]);
    rateRecipeAjaxCall(rateButtonArray[2]);
});
$("#rate4Btn").click(function(){
    rateButtonClick(rateButtonArray[3]);
    rateRecipeAjaxCall(rateButtonArray[3]);
});
$("#rate5Btn").click(function(){
    rateButtonClick(rateButtonArray[4]);
    rateRecipeAjaxCall(rateButtonArray[4]);
});

