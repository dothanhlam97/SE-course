$(document).ready(function() {
    $('#btnSignup').on("click", function(event) {
        event.preventDefault();
        $.ajax({
            type: "POST",
            url: "/post-account",
            data: {},
            dataType: "json",
            success: function () {
                console.log('gioi qua');
            },
            error: function () {
                console.log('dfcdf');
            }
        });
    });
});