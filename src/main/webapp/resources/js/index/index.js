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

    $(".collapsable").click(function() {
        $(this).toggleClass("glyphicon-expand").toggleClass("glyphicon-collapse-down");
        $("#filters").toggle("slow", function() {});
    });
});

/*
 <span class="tag label label-info">
 <span>Example Tag</span>
 <a><i class="remove glyphicon glyphicon-remove-sign glyphicon-white"></i></a>
 </span>
 */