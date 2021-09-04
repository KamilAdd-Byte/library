//Set up the dialog box
$("#clickMe").dialog({
    autoOpen  : false,
    modal     : true,
    title     : "Title",
    buttons   : {
        'Yes' : function() {
            var textValue = $('#myTextBox').val();
            alert('Yes clicked');
            //Do whatever you want to do when Yes clicked
        },
        'No' : function() {
            alert('No clicked');
            //Do whatever you want to do when No clicked
            $(this).dialog('close');
        }
    }
});

//Open the dialog box when the button is clicked.
$('#clickMe').click(function() {
    $("#myDialog").dialog("open");
});
