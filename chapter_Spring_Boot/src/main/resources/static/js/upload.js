$(document).ready(function(){

    $("#submitButton").click(function(event) {
        event.preventDefault();
        ajaxSubmitForm();
    });
});


function ajaxSubmitForm() {

    var form = $("#restUploadForm")[0];

    var data = new FormData(form);

    $("#submitButton").prop("disabled", true);

    $.ajax({
        type : "POST",
        enctype : 'multipart/form-data',
        url : "restUpload",
        data : data,
        // prevent jQuery from automatically transforming the data into a query string
        processData : false,
        contentType : false,
        cache : false,
        timeout : 1000000,
        success : function(data, textStatus, jqXHR) {
            $("#result").html(data);
            $("#result").addClass('alert alert-info');
            $("#submitButton").prop("disabled", false);
            $('#restUploadForm')[0].reset();
        },
       error: function(jqXHR, textStatus, errorThrown) {
            $("#result").html(jqXHR.responseText);
            $("#result").addClass('alert alert-info');
            $("#submitButton").prop("disabled", false);
        }
    });
}