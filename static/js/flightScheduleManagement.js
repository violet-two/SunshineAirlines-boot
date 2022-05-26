$(document).ready(function (){
    getCityName()
    $(".searchBtn").click(function (){
        getSchedule()
    })
    $(".changeicon").click(function (){
        var fromCity = $(".fromCity").val();
        var toCity =  $(".toCity").val();
        $(".fromCity").val(toCity)
        $(".toCity").val(fromCity)
    })
})
function getSchedule(){
    var fromCity = $(".fromCity").val();
    var toCity =  $(".toCity").val();
    var startDate = $(".startDate").val();
    var endDate =  $(".endDate").val();
    var param = "fromCity="+fromCity+"&toCity="+toCity+"&startDate="+startDate+"&endDate="+endDate
    $.ajax({
        url:'http://localhost:8080/SunshineAirlines/getSchedule',
        type:'post',
        data:param,
        success:function (msg) {
            if (msg.flag == "success") {
                var scheduleMsg = msg.data;
                var html = ""
                for (var i = 0;i<scheduleMsg.length;i++){
                    var statusBtn = "Cancel";
                    var statusNum = 0;
                    if(scheduleMsg[i].Status=="Canceled"){
                        statusBtn = "Confirm"
                        statusNum=1
                    }
                    html+= "<tr class='tdcolor'>"+
                    "<td>"+scheduleMsg[i].Date.substring(0,10)+"</td>"+
                    "<td>"+scheduleMsg[i].Time.substring(0,5)+"</td>"+
                    "<td>"+scheduleMsg[i].DepartureAirportIATA+"/"+scheduleMsg[i].DepartCityName+"</td>"+
                    "<td>"+scheduleMsg[i].ArrivalAirportIATA+"/"+scheduleMsg[i].ArriveCityName+"</td>"+
                    "<td>"+scheduleMsg[i].Name+"</td>"+
                    "<td>"+scheduleMsg[i].EconomyPrice+"</td>"+
                    "<td>"+scheduleMsg[i].FlightNumber+"</td>"+
                    "<td>"+scheduleMsg[i].Gate+"</td>"+
                    "<td>"+scheduleMsg[i].Status+"</td>"+
                    "<td><input type='button' value='"+statusBtn+"' onclick='updateSchedule("+scheduleMsg[i].ScheduleId+","+statusNum+")'/></td>"+
                    "</tr>"
                }
                $(".formclass tbody").html(html)
                $(".formclass tbody tr:odd").addClass("tdcolor1")
            }else{
                $(".formclass tbody").html(" ")
            }
        }
    })
}
function updateSchedule(scheduleId,status){
    var statusBtn = "Canceled";
    if(status==1){
        statusBtn = "Confirmed"
    }
    var param = "scheduleId="+scheduleId+"&status="+statusBtn
    $.ajax({
        url:'http://localhost:8080/SunshineAirlines/updateSchedule',
        type:'post',
        data:param,
        success:function (msg){
            if(msg.flag=="success"){
                getSchedule();
            }
        }
    })
}

function getCityName(){
    $.ajax({
        url:'http://localhost:8080/SunshineAirlines/getCityNames',
        type:'post',
        data:'',
        success:function (msg){
            if(msg.flag=="success"){
                var cityMsg=msg.data
                var html = ""
                for (var i = 0;i<cityMsg.length;i++){
                    html +="<option>"+cityMsg[i].CityName+"</option>"
                }
                $(".fromCity").html(html);
                $(".toCity").html(html);
            }
        }
    })
}