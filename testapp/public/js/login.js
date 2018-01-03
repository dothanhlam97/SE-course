$(document).ready(function() {
    $('#btnSignup').on("click", function(event) {
        event.preventDefault();
        $.ajax({
            type: "POST",
            url: "/post-account",
            data: {
                "email": $('#signup-email').val(),
                "passd": $('#signup-pass').val()
            },
            dataType: "json",
            success: function () {
                window.location.href = 'http://localhost:8080/login';
            },
            error: function () {
            }
        });
    });
});