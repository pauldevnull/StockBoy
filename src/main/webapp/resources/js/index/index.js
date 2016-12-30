$(document).ready(function() {
    var table = $('#stocks').DataTable({
        "paging":   true,
        "ordering": true,
        "info":     true
    });

    $('input.toggle-vis').on('click', function (e) {
        var column = table.column( $(this).attr('data-column') );
        column.visible(!column.visible());
    });
});