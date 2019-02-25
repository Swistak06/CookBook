$("#signUpForm").submit(function(){
    if(checkIfUsernameIsUsed($("#username").val())){
        alert("Username is already used. Choose other.");
        return false;
    }

    if(!checkUsername($("#username").val())){
        alert("Username does not met the requirements");
        return false
    }
    if(!checkEmail($("#e-mail").val())){
        alert("Wrong email format.");
        $("#email-alert").append('<div class="alert alert-danger" role="alert">\n Incorrect email format. Try again + </div>');
        return false;
    }
    if(!checkPassword($("#password").val())){
        alert("Password does not meet the requirements.");
        return false
    }
    if(!checkIfPasswordsMatches($("#password").val(),$("#confirmPassword").val())){
        alert("Passwords are not the same!");
        return false
    }
    return true;
});

function checkUsername(username){
    var regex = new RegExp("[a-zA-Z0-9]{4,50}$");
    return regex.test(username)
}

function checkEmail(email){
    var regex = new RegExp("^.+\@.+\\..+$");
    return regex.test(email);
}

function checkPassword(password){
    var regex = new RegExp("[a-zA-Z0-9]{6,50}$");
    return regex.test(password)
}

function checkIfPasswordsMatches(password, confirmPassword){
    if(password === confirmPassword)
        return true;
    return false;
}

function checkIfUsernameIsUsed(username){
    var urlAddress = "/api/checkIfUserExist/" + username;
    var flag = false;
    $.ajax({
        type:'get',
        url:urlAddress,
        async:false,
        success:function(data){
            console.log("Data: " + data);
            flag = data;
        }
    });
    return flag;
}


