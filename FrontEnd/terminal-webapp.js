$(document).ready(function() {
  console.log("JS loaded");

  var URL = "http://localhost:8080/";

  $('button').hover(function() {
    $(this).fadeTo(0, 0.75);
  }, function() {
    $(this).fadeTo(0, 1);
  });

  $('#buttonLogin').click(function() {
    console.log("Validating user... ");
    $.ajax({
      type: "GET",
      url: URL + "login?id=" + $('#username').val() + "&pwd=" + $('#password').val(),
      success: function(result) {
        console.log(result);
      },
      error: function(e) {
        console.log("ERROR: " + e);
      }
    });
  });
});
