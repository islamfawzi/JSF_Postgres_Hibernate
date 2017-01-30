
// select all
$(document).on("click", "#checkall", function () {
    var checkall = $(this).is(":checked");
    $(".check").prop("checked", checkall);

});

$('.datepicker').datepicker({
    format: 'dd/mm/yyyy'
});
$('.datepicker').datepicker('setDate', $(this).val());





