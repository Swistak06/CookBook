$(function () {
    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});
var rateButtonArray = ["#rate1Btn","#rate2Btn","#rate3Btn","#rate4Btn","#rate5Btn"];
var rateButtonsModes = [];
for(var i = 0; i<5; i++){
    if($(rateButtonArray[i]).attr("src") == "/icons/star1.png")
        rateButtonsModes.push("/icons/star1.png");
    else
        rateButtonsModes.push("/icons/star2.png");
}

function hoverIn(button){
    for(var i = 0; i<rateButtonArray.indexOf(button)+1; i++){
        $(rateButtonArray[i]).attr("src","/icons/star2.png");
    }
    for(var j = rateButtonArray.indexOf(button)+1; j <rateButtonArray.length;j++){
        $(rateButtonArray[j]).attr("src","/icons/star1.png");
    }
}
function hoverOut(button){
    for(var i = 0; i<rateButtonArray.indexOf(button)+1; i++){
        $(rateButtonArray[i]).attr("src",rateButtonsModes[i]);
    }
    for(var j = rateButtonArray.indexOf(button)+1; j <rateButtonArray.length;j++){
        $(rateButtonArray[j]).attr("src",rateButtonsModes[j]);
    }
}
function rateButtonClick(button){
    for(var i = 0; i<rateButtonArray.indexOf(button)+1; i++){
        rateButtonsModes[i] = "/icons/star2.png";
    }
    for(var j = rateButtonArray.indexOf(button)+1; j <rateButtonArray.length;j++){
        rateButtonsModes[j] = "/icons/star1.png";
        $(rateButtonArray[j]).attr("src",rateButtonsModes[j]);
    }
}
function rateRecipeAjaxCall(button){
    var id = $(button).parent().parent().parent().parent().attr('name');
    var urlString = '/api/rateRecipe/recipe'+id+'?rateVal=';
    var rateVal = rateButtonArray.indexOf(button)+1;
    $.ajax({
        type: "post",
        url: urlString+rateVal,
        success:function (data){
            $("#recipeRateLabel").text("Rate: " + data);
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
                $(rateButtonArray[i]).attr("src","/icons/star2.png");
                rateButtonsModes[i] = "/icons/star2.png";
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

$("#saveRecipeButton").click(function () {
    var id = $(this).parent().parent().parent().attr('name');
    var urlString = '/api/saveRecipe/recipe' + id;
    $.ajax({
        type: "GET",
        url: urlString,
        success: function (data) {
            if(data)
                alert("Recipe removed from cook book");
            else
                alert("Recipe added to cook book");
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

