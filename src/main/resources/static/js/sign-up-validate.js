$("#someButton").click(function(){
    //Checking username if is already used
    //Checking correctness of username regexp
    //Checking correctness of password regexp
    //Checking whether both passwords match

    if(checkEmail() === false){
        //Add alert
        //$("#email-alert").append('<div class="alert alert-danger" role="alert">\n Incorrect email format. Try again + </div>');
        //return false;
    }


    return true;
});


function checkEmail(){
    var regex = new RegExp("^.+\@.+\\..+$");
    console.log(regex.test($("#e-mail").val()));
    return regex.test($("#e-mail").val());
}

