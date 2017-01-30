$("#payroll-items-modal-btn").click(function (e) {
    $("#payrollItemsModal").modal();
    e.preventDefault();
});

$('#payrollModalSave').click(function () {
    
    var checked_length = $("#employees_checkboxes input:checkbox:checked").length;
    /*if(checked_length > 0){
        $("#payroll-items-res").empty();
        return false;
    }*/
    var html = "";

    $("#payrollItems-checkboxes input:checkbox:checked").each(function (index) {

        var val = $(this).val();
        var id = $(this).attr("id");
        var label = $(".modal label[for="+ id +"]").text();
        
        html += "<div class='form-group row' id='item-"+val+"-div'>";
        html += "<input type='hidden' name='item_ids' value='"+val+"' />";
        
        html += "<div class='col-md-3'>" +
                    "<label for='payroll-"+val+"'>"+label+"</label>" +
                "</div>";

        html += "<div class='col-md-3'>" +
                    "<input type='text' name='amount-"+val+"' class='form-control' placeholder='Amount' />" +
                "</div>" +
                "<div class='col-md-3'>" +
                    "<input type='checkbox' value='1'  name='status-"+val+"' /> active" +
                "</div>" +
                 "<div class='col-md-3'>" +
                     "<span style='color:red;font-size:22px;cursor:pointer;' class='glyphicon glyphicon-remove-circle remove-item' item='"+val+"' aria-hidden='true'></span>" +
                "</div>";            
        
        html += "</div>";
        

    });
    
    $("#payroll").append(html);
    $("#payrollItemsModal").modal('hide');   // close modal

});