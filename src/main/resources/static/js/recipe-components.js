
$("#likeItButton").click(function(){
        var id = /*[[${recipe.getId()}]]*/ 'default';
        var urlString = '/api/likeRecipe/recipe' + id;
        console.log(urlString)
        $.ajax({
            type: "GET",
            url: urlString,
            success: function (data) {
                $("#recipeLikesLabel").text('Likes: ' + data)
            }
        });
});

$("#madeItButton").click(function(){
    var id = $(this).parent().attr('name');
    var urlString = '/api/prepareRecipe/recipe' + id;
    $("#recipePreparationNumberLabel").text('Made it: juhu')
    $.ajax({
        type: "GET",
        url: urlString,
        success: function (data) {
            $("#recipePreparationNumberLabel").text('Made it: ' + data)
        }

    });
});
