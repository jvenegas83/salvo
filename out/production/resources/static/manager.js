$(function() {

  // display text in the output area
  function showOutput(text) {
    $("#output").text(text);
  }

  // load and display JSON sent by server for /players

  function loadData() {
    $.get("/players")
    .done(function(data) {
      showOutput(JSON.stringify(data, null, 2));
    })
    .fail(function( jqXHR, textStatus ) {
      showOutput( "Failed: " + textStatus );
    });
  }

  // handler for when user clicks add person

  function addPlayer() {
    var name = $("#email").val();
    var fname= $("#firstname").val();
    var lname= $("#lastname").val();
    if (name) {
      postPlayer(name,fname,lname);
    }
  }

  // code to post a new player using AJAX
  // on success, reload and display the updated data from the server

  function postPlayer(userName, firstname, lastname) {
    $.post({
      headers: {
          'Content-Type': 'application/json'
      },
      dataType: "text",
      url: "/players",
      data: JSON.stringify({ "userName": userName, "firstName": firstname, "lastName": lastname })
    })
    .done(function( ) {
      showOutput( "Saved -- reloading");
      loadData();
    })
    .fail(function( jqXHR, textStatus ) {
      showOutput( "Failed: " + textStatus );
    });
  }

  $("#add_player").on("click", addPlayer);

  loadData();
});