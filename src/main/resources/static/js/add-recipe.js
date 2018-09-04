$("#addIngredient").click(function () {
    var textBox = '<div class="form-inline fiel">\n' +
        '            <div class="form-group mx-sm-3 mb-2">\n' +
        '                <input class="form-control ing" placeholder="name" type="text"/>\n' +
        '            </div>\n' +
        '            <div class="form-group mx-sm-3 mb-2">\n' +
        '                <input class="form-control val" placeholder="amount"  type="text"/>\n' +
        '            </div>\n' +
        '        </div>';
    $("#ingredientGroup").append(textBox);
});

$("#addRecipeForm").submit(function () {
    var arr = [];
    var num = 0;
    $("div.fiel").each(function () {
        num++;
        var name = $(this).find(".ing").val();
        var value = $(this).find(".val").val();
        if(name !== "" && value !== "")
            arr.push({number:num, name:name, value:value});
    });
    $("#jsonIngredients").val(JSON.stringify(arr));

})