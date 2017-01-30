$("#payroll-items-modal-btn").click(function (e) {
    $("#payrollItemsModal").modal();
    e.preventDefault();
});

$('#payrollModalSave').click(function () {
    
    var checked_length = $("#employees_checkboxes input:checkbox:checked").length;
    if(checked_length > 0){
        $("#payroll-items-res").empty();
        return false;
    }
    var html = "";

    $("#payrollItems-checkboxes input:checkbox:checked").each(function (index) {

        var val = $(this).val();
        var id = $(this).attr("id");
        var label = $(".modal label[for="+ id +"]").text();
        
        if (index % 2 == 0) {
            html += "<div class='row'>";
        }

        html += "<div class='col-md-6 checkbox-div'>";
        html += "<input id='"+id+"' type='checkbox' name='item_ids' value='" + val + "' checked>";
        html += " <label for='"+id+"'>";
        html += label +"</label> ";
        html += "</div>";

        if(index % 2 != 0){
           html += "</div>";    
        }

    });
    
    $("#payroll-items-res").html(html);
    $("#payrollItemsModal").modal('hide');   // close modal

});