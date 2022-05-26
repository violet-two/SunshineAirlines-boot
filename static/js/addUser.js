$(document).ready(function (){
    $(".submit").click(function (){
        var email = $(".email").val();
        var firstName = $(".firstName").val();
        var lastName = $(".lastName").val();
        var gender = 'F';
        if($(".genderMale").prop("checked")){
            gender='M'
        }
        var dateOfBirth = $(".dateOfBirth").val();
        var phone = $(".phone").val();
        var photo = $(".photo").attr("src")
        var address = $(".address").val();
        var roleId = 2;
        if($(".roleUser").prop("checked")){
            roleId=1
        }
        var param="email="+email+"&firstName="+firstName+"&lastName="+lastName
            +"&gender="+gender+"&dateOfBirth="+dateOfBirth+"&phone="+phone
            +"&photo="+photo+"&address="+address+"&roleId="+roleId
        $.ajax({
            url:'http://localhost:8080/SunshineAirlines/addUser',
            type:'post',
            data:param,
            success:function (msg){
                if(msg.flag=="success"){
                    location.href="UserManagement.html"
                }else{
                    alert(msg.data)
                }
            }
        })
    })
    $(".cancel").click(function (){
        location.href="UserManagement.html"
    })
    $(".upload-input").change(function (){
        var files = this.files[0];
        var reader = new FileReader();
        reader.onload = function (event){
            $(".photo").attr("src",event.target.result)
        }
        reader.readAsDataURL(files)
    })
})