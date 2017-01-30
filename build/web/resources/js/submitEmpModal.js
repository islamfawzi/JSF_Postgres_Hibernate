
var module = "";

$("#emp-modal-btn").click(function () {
    
    $("#empModal").modal();
    
    module = $(this).attr("module");
    
    return false;
});


$('#modalSave').click(function () {

    var checked_length = $("#employees_checkboxes input:checkbox:checked").length;
    var html = "";
    var emp_ids = [];
    

    $("#employees_checkboxes input:checkbox:checked").each(function (index) {

        var val = $(this).val();
        var id = $(this).attr("id");
        var label = $(".modal label[for=" + id + "]").text();
        
        emp_ids.push(val);

        if (index % 2 == 0) {
            html += "<div class='row'>";
        }

        html += "<div class='col-md-6 checkbox-div'>";
        html += "<input type='checkbox' ng-model='emp_ids[" + index + "]' name='emp_ids' value='" + val + "' checked>";
        html += " <label for='emp_'" + id + ">";
        html += label + "</label> ";
        html += "</div>";

        if (index % 2 != 0) {
            html += "</div>";
        }

    });

    $("#emps_res").html(html);
    $("#empModal").modal('hide');

    console.log(module);
    if (module == "payroll-entry"){
        getPayrollItems_Emps(emp_ids);
    }


});


var getPayrollItems_Emps = function (emp_ids) {
    console.log(emp_ids);
    $.ajax({
        method: "POST",
        url: "payrolls",
        data: {submit: 'employee-payroll-items', emp_ids: emp_ids},
        async: false
    }).done(function (data) {

        $("#payroll").html(data);

    }); /* ajax End */

}; /* getPayrollItems End */