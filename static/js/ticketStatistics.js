$(document).ready(function (){
    var nowDate = new Date();
    var year = nowDate.getFullYear();
    var month = nowDate.getMonth()+1;
    if(month<10){
        month = "0"+month;
    }
    var maxDate = year+"-"+month
    $(".startDate").prop("max",maxDate)
    $(".endDate").prop("max",maxDate)
    $(".endDate").change(function (){
        var startDate = $(".startDate").val()
        var endDate = $(".endDate").val()
        if(new Date(startDate)>new Date(endDate)){
            alert("开始时间必须小于结束时间")
            $(".endDate").val(" ")
        }
    })
    $(".stat").click(function (){
        var startDate = $(".startDate").val()
        var endDate = $(".endDate").val()
        var param = "startDate="+startDate+"&endDate="+endDate
        $.ajax({
            url:'http://localhost:8080/SunshineAirlines/getTicketStatistics',
            type:'post',
            data:param,
            success:function (msg){
                if(msg.flag=="success"){
                    var ticketMsg = msg.data
                    var html =""
                    for (var i =0;i<ticketMsg.length;i++){
                        html+= "<tr class='tdcolor'>"+
                            "<td>"+ticketMsg[i].Month+"</td>"+
                            "<td>"+ticketMsg[i].FlightsAmount+"</td>"+
                            "<td>"+ticketMsg[i].TicketsAmount+"</td>"+
                            "<td>"+ticketMsg[i].TicketsRevenue+"</td>"+
                        "</tr>"
                    }
                    $(".formclass tbody").html(html)
                    $(".formclass tbody tr:odd").addClass("tdcolor1")
                }
            }
        })
    })
})