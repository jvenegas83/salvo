
$(document).ready(function () {
 var gameId=getUrlParameter();
 $.getJSON('http://localhost:8080/api/game_view/'+gameId, function (data) {
    var tableHeaders=["A","B","C","D","E","F","G","H","I","J"];
    console.log(data);
    showPlayers(data.gamePlayers, gameId);
    renderTableShips(tableHeaders);
    printShips(data.ships);
    renderTableSalvoes(tableHeaders);
    printSalvoes(data.salvo,data.ships,gameId);


  })
  .fail(function() {
        alert('Gameplay data not available, try with another one');
   });
});

function getUrlParameter(){
    let url=window.location.href
    var name=url.split('?gp=');
    return name[1];
}

function renderTableShips(headers) {
   renderHeaders(headers,"table-ships-headers");
   renderRows(headers,"ships-rows");
 }
function renderTableSalvoes(headers) {
   renderHeaders(headers, "table-salvoes-headers");
   renderRows(headers,"salvoes-rows");
}


function renderHeaders(data, type) {
  var html = getHeadersHtml(data);
  document.getElementById(type).innerHTML = html;
}

function renderRows(data,type) {
  var html;
  if (type=="ships-rows"){
   html = getColumnsHtmlShips(data);
  }
  else{
    html = getColumnsHtmlSalvoes(data)
  }
  document.getElementById(type).innerHTML = html;
}

function getHeadersHtml(data) {
   var headers="<tr><th></th>";
   for(var i=1;i<=data.length;i++){
       headers+="<th>"+i+"</th>";
    }
   headers+="</th>";
   return headers;
}

 function getColumnsHtmlShips(columns) {
   var row="<tr>";
   for(var i=0;i<columns.length;i++){
    row+="<td>"+columns[i]+"</td>";
    for(var x=0;x<columns.length;x++){
        row+="<td id="+columns[i]+(x+1)+"></td>"
    }
    row+="</tr>";

   }
   return row;
 }
 function getColumnsHtmlSalvoes(columns){
  var row="<tr>";
    for(var i=0;i<columns.length;i++){
     row+="<td>"+columns[i]+"</td>";
     for(var x=0;x<columns.length;x++){
         row+="<td id=X"+columns[i]+(x+1)+"></td>"
     }
     row+="</tr>";

    }
    return row;
 }
function printShips(ship){
    for(var i=0;i<ship.length;i++){
        printShipLocation(ship[i].shipLocations);
    }
}
function printSalvoes(salvo,ships,id){
    for(var i=0;i<salvo.length;i++){
       if(salvo[i].id!=id){
        SalvoesLocation(salvo[i].turns,ships,"oponent");
       }
       else{
        SalvoesLocation(salvo[i].turns,ships,"you");
       }
    }
}
function printShipLocation(location){
    for(var i=0;i<location.length;i++){
        cell=document.getElementById(location[i]);
        cell.setAttribute("style", "background-color: blue;");
    }
}
function SalvoesLocation(location,ships, player){
    if(player=="oponent"){
        for(var i=0; i<location.length;i++){
            printSalvoesLocation(location[i].salvoLocations, location[i].turnNumber,"");
            printSalvoesHeats(location[i].salvoLocations, location[i].turnNumber,ships);

         }
    }
    else{
        for(var i=0; i<location.length;i++){
            printSalvoesLocation(location[i].salvoLocations, location[i].turnNumber,"X");
        }
    }
}
function showPlayers(playersinfo, urlparameter){
    for(var i=0; i<playersinfo.length; i++){
        $('#player'+i).text(playersinfo[i].player.email);
        if(playersinfo[i].player.id==urlparameter){
            $('#player'+i).append(document.createTextNode(" (you)"));
        }
    }

}
function printSalvoesLocation(salvoLocations, turn, playertable){
    for(var i=0;i<salvoLocations.length;i++){
        cell=document.getElementById(playertable+salvoLocations[i]);
        cell.setAttribute("style", "background-color: yellow;");
        $('#'+playertable+salvoLocations[i]).html(document.createTextNode(turn));
    }
}
function printSalvoesHeats(salvoLocations,turn,ships){
    console.log(salvoLocations);
    for(var i=0; i<salvoLocations.length;i++){
         checkShipLocation(salvoLocations[i],ships, turn);
   }

}
function checkShipLocation(salvo, ships, turn){
    for(var i=0; i <ships.length;i++){
        for (var x=0; x<ships[i].shipLocations.length;x++){
               if(salvo==ships[i].shipLocations[x]) {
                     cell=document.getElementById(salvo);
                     cell.setAttribute("style", "background-color: red;");
               }
        }
    }
}