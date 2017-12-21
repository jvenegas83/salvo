$(document).ready(function () {

    $.getJSON('http://localhost:8080/api/games', function (data) {
        console.log(data);
        console.log(data[0].gamePlayers[0].player.email);
        var ol=document.getElementById("gamelist")
        for(var i=0;i<data.length; i++){ //games
            var li = document.createElement("li");
            //players
            var emails="";
            var gamedate=formatDate(data[i].created)
            var num_players=data[i].gamePlayers.length;

            for(var x=0;x < num_players; x++){

                emails += data[i].gamePlayers[x].player.email+ " ";
            }
            li.innerHTML=gamedate+" , "+ emails;
            ol.appendChild(li);
        }
    });
});

function formatDate(data){
    var date= new Date(data);
    let month =  date.getMonth() +1 ;
    let day = date.getDate();
    const year = date.getFullYear();
    let hour = date.getHours();
    let minutes=date.getMinutes();
    let seconds=date.getSeconds();
    finaldate=day+"/"+month+"/"+year + " "+hour+":"+minutes+":"+seconds;

    return finaldate;
}