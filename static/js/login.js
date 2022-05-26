$(document).ready(function (){

    var userStr = localStorage.getItem("user")
    try{
        var user = JSON.parse(userStr)
        var loginDate = new Date(user.loginDate)
        loginDate.setDate(loginDate.getDate()+7)
        var nowDate = new Date()
        if(nowDate<loginDate){
            jump(user)
        }
    }catch (e){

    }

    $(".loginbutton").click(function (){
        var email  = $(".email").val();
        var password  = $(".password").val();
        var param = "email="+email+"&password="+password;
        $.ajax({
            url:'http://localhost:8080/SunshineAirlines/login',
            type:'post',
            data:param,
            success:function (msg){
                if(msg.flag=="success"){
                    var user = msg.data[0];
                    if($(".is7day").is(":checked")){
                        user.loginDate = new Date();
                        localStorage.setItem("user",JSON.stringify(user))
                    }
                    jump(user);
                }else{
                    $(".alertInfo").html(msg.data);
                }
            }
        })
    })
})

function jump(user){
    if(user.RoleId==1){
        location.href = "ModifyPassword.html"
    }else if(user.RoleId==2){
        location.href="UserManagement.html";
    }
}