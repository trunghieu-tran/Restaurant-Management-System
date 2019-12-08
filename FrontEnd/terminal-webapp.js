$(document).ready(function() {
  console.log("JS loaded");

  $('button').hover(function() {
    $(this).fadeTo(0, 0.75);
  }, function() {
    $(this).fadeTo(0, 1);
  });

  
});
