$(document).ready(function () {

    $("#datasBtn").on('click', function () {
        $("#account-details").show();
        $("#books").hide(); 
        $(".add-book-btn").hide();       
    });

    $("#booksBtn").on('click', function () {
        $("#account-details").hide();
        $("#books").show();
        $(".add-book-btn").show();
    });

    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active');
    });
});