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
      $('#bodyEditOrder').append("<input type='hidden' id='orderID' value=" + result.orderID + ">")
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

  // user selects add item, redirect to add-item page
  $('#buttonAddItem').click(function () {
    var urlParams = new URLSearchParams(window.location.search);
    var tableID = urlParams.get('tableID');
    var orderID = $('#orderID').val();
    window.location.replace("add-item.html?tableID=" + tableID +"&orderID=" + orderID);
  });

  // load food items
  if($('body').attr('id') == 'bodyAddItem') {
    console.log('Loading item list...');
    $.getJSON(URL+"allcategories", function(result) {
      console.log(result);
      $.each(result, function (key, category) {
        $('#itemList').append("<div class=\"panel-group\">\n" +
            "      <div class=\"panel panel-default\">\n" +
            "        <div class=\"panel-heading\">\n" +
            "          <h4 class=\"panel-title\">\n" +
            "            <a data-toggle=\"collapse\" href=\"#"+category['name']+"\">"+category['name']+"</a>\n" +
            "          </h4>\n" +
            "        </div>" +
            "        <div id=\""+category['name']+"\" class=\"panel-collapse collapse\">\n" +
            "          <ul class=\"list-group\">");
        $.each(category['items'], function (id, item) {
          $("#" + category['name'] + " ul").append('<button type="button" class="btn btn-primary food-item" value="'
              + item['description'] + '">'
              + item['description'] + ', $' + item['price'] + '</button>');
        })
      })
    });
  }

  // add food item to order
  $(document).on('click', '.food-item', function() {
    var urlParams = new URLSearchParams(window.location.search);
    var tableID = urlParams.get('tableID');
    var urlParams = new URLSearchParams(window.location.search);
    var orderID = urlParams.get('orderID');
    $.ajax({
      type: "GET",
      url: URL + "addItemToOrder?orderID=" + orderID + "&item=" + $(this).val(),
      success: function(result) {
        console.log('food item adding result:' + result);
        if(result === true) {
          window.location.replace("edit-order.html?tableID=" + tableID);
        }
      },
      error: function(e) {
        console.log("ERROR: " + e);
      }
    });
  });

  // cancel add food item and return to employee home page
  $('#buttonCancelAddItem').click(function () {
    var urlParams = new URLSearchParams(window.location.search);
    var tableID = urlParams.get('tableID');
    window.location.replace("edit-order.html?tableID=" + tableID);
  });

  // submit order
  $('#buttonSubmitOrder').click(function () {
    var orderID = $('#orderID').val();
    $.ajax({
      type: "GET",
      url: URL + "submitOrder?orderID=" + orderID,
      success: function(result) {
        console.log('submit order result:' + result);
        if(result === true) {
          window.location.replace("employee-home.html");
        }
      },
      error: function(e) {
        console.log("ERROR: " + e);
      }
    });
  });

  // when select-table-order.html is loaded, fetch the tables and display status and buttons
  if($('body').attr('id') == 'bodySelectTableOrder') {
    console.log('Fetching tables...');
    $.getJSON(URL+"floorStatus", function(result) {
      $.each(result, function(i, field) {
        if(field.status == "Occupied") {
          $('#bodySelectTableOrder').append("<button class=\"buttonTableOrder\" id=\"buttonTableOrder"+(i+1)+"\" value="+field.status+">Table "
              +(i+1)+"</button>\n");
          $('#buttonTableOrder' + (i+1)).css('background-color', '#00a61f');
        }
      });
    });
  }

  // user selects which table to edit order of
  $(document).on('click', '.buttonTableOrder', function() {
    if($(this).val() == "Occupied") {
      var editTableID = $(this).attr('id').substring(16); // "buttonTableOrder" is 11 char long
      window.location.replace("edit-order.html?tableID=" + editTableID);
    }
  });

});
