$(document).ready(function() {
  console.log("JS loaded");
  var URL = "http://localhost:8080/";

  // when select-table.html is loaded, fetch the tables and display status and buttons
  if($('body').attr('id') == 'bodySelectTable') {
    console.log('Fetching tables...');
    $.getJSON(URL+"floorStatus", function(result) {
      $.each(result, function(i, field) {
        $('#bodySelectTable').append("<button class=\"buttonTable\" id=\"buttonTable"+(i+1)+"\" value="+field.status+">Table "
                                      +(i+1)+"</button>\n");
        if(field.status == "Available") {
          $('#buttonTable' + (i+1)).css('background-color', '#00a61f');
        } else {
          $('#buttonTable' + (i+1)).css('background-color', '#900000');
        }
      });
    });
  }

  if($('body').attr('id') == 'bodyEditOrder') {
    console.log('Creating edit form...');
    var query = window.location.search.substring(1);
    // stupid way of finding what comes after tableID=
    var editTableID = query.substring(query.indexOf('tableID=')+('tableID=').length)
    $('#orderTitle').append(editTableID);
    // display information
    $.getJSON(URL+"tableOrder?id="+editTableID, function(result) {
      console.log(result.orderID);
      $('#bodyEditOrder').append("<p>Order ID: " + result.orderID + "</p>" +
                                 "<p>Placed on: " + result.placed + "</p>" +
                                 "<p>Total cost: $" + result.payment + "</p>");
      if(result.ready) {
        $('#bodyEditOrder').append("<p>Order ready</p>");
      } else {
        $('#bodyEditOrder').append("<p>Order NOT ready<p>");
      }
      $('#bodyEditOrder').append("<h3>Items ordered: </h3>");
      $.each(result.orderedItems, function(i, field) {
        $('#bodyEditOrder').append("<p>"+field.description + " $" + field.price + "</p>");
      });
    });
  }

  // fade effect for buttons when mouse over buttons
  $(document).on('mouseover', 'button', function() {
    $(this).fadeTo(0, 0.75);
  });
  $(document).on('mouseout', 'button', function() {
    $(this).fadeTo(0, 1);
  });

  // check user's credentials when they click login
  $('#buttonLogin').click(function() {
    console.log("Validating user... ");
    $.ajax({
      type: "GET",
      url: URL + "login?id=" + $('#username').val() + "&pwd=" + $('#password').val(),
      success: function(result) {
        console.log(result);
        if(result == "Login successful!") {
          window.location.replace("employee-home.html");
        }
      },
      error: function(e) {
        console.log("ERROR: " + e);
      }
    });
  });

  // cancel login brings user back to home page
  $('#buttonCancelLogin').click(function() {
    console.log("Cancelling login...");
    window.location.replace("home.html");
  });

  // user selects which table to edit order of
  $(document).on('click', '.buttonTable', function() {
    if($(this).val() == "Available") {
      var editTableID = $(this).attr('id').substring(11); // "buttonTable" is 11 char long
      window.location.replace("edit-order.html?tableID=" + editTableID);
    }
  });

});
