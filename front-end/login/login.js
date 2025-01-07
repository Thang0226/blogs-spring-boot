function login(){
    let username = $("#username").val();
    let password = $("#password").val();
    let user = {
        "username": username,
        "password": password,
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        url: "http://localhost:8080/api/blogs/login",
        type: "POST",
        data: JSON.stringify(user),
        success: function(jwtResponse){
            // console.log(jwtResponse);
            localStorage.setItem("token", jwtResponse.token);
            window.location.href = "../blog/list.html";
        }
    })
}