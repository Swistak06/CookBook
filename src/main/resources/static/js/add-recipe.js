$("#addIngredient").click(function () {
    var textBox = '<div class="form-inline ingredientbar">\n' +
        '            <div class="form-group mx-sm-3 mb-2">\n' +
        '                <input class="form-control ing" placeholder="name" type="text"/>\n' +
        '            </div>\n' +
        '            <div class="form-group mx-sm-3 mb-2">\n' +
        '                <input class="form-control val" placeholder="amount"  type="text"/>\n' +
        '            </div>\n' +
        '        </div>';
    $("#ingredientGroup").append(textBox);
});

$("#addSteps").click(function () {
    var textBox = '<div class="form-inline offset-2 col-6 stepbar">\n' +
        '            <div class="form-group mx-sm-3 mb-2">\n' +
        '                <textarea class="form-control descr" rows="4" placeholder="Step"></textarea>\n' +
        '            </div>\n' +
        '        </div>';
    $("#stepGroup").append(textBox);
});

$("#addRecipeForm").submit(function () {
    var arr = [];
    var num = 0;
    $("div.ingredientbar").each(function () {
        num++;
        var name = $(this).find(".ing").val();
        var value = $(this).find(".val").val();
        if(name !== "" && value !== "")
            arr.push({number:num, name:name, value:value});
    });
    $("#jsonIngredients").val(JSON.stringify(arr));

    arr = [];
    num = 0;

    $("div.stepbar").each(function () {
        num++;
        var value = $(this).find(".descr").val();
        if(value !== "")
            arr.push({number:num, value:value});
    });
    $("#jsonSteps").val(JSON.stringify(arr));

    arr = [];
    num = 0;
    $("div.image-group").each(function () {
        var value = $(this).find(".recipe-image").val();
        if (value !== ""){
            num++;
            arr.push({number:num, value:value});
        }
    });
    if(num > 0)
        $("#jsonImages").val(JSON.stringify(arr));

})