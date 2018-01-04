$(document).ready(function() {
    $("#looking_to_hire").prop("checked", false);
    $("#looking_to_work").prop("checked", false);
    $('#btnSignup').on("click", function (event) {
        event.preventDefault();
        var flag = false;
        $('#signup-email').css('border-bottom-color', "#327ad5");
        $('#signup-pass').css('border-bottom-color', "#327ad5");
        $('#alert-hire-work').css('display', 'none');
        $('#alert-email').css('display', 'none');
        if (!$('#signup-email').val()) {
            $('#signup-email').css('border-bottom-color', 'red');
            flag = true;
        }
        if (!$('#signup-pass').val()) {
            $('#signup-pass').css('border-bottom-color', 'red');
            flag = true;
        }
        if (!$('#looking_for_hire').prop("checked") && !$('#looking_for_work').prop("checked")) {
            $('#alert-hire-work').css("display", "block");
            flag = true;
        }
        if (flag) {
            return;
        }
        $.ajax({
            type: "POST",
            url: "/post-account",
            data: {
                "email": $('#signup-email').val(),
                "passd": $('#signup-pass').val(),
                "isHire": $('#looking_for_hire').prop("checked") === true ? "true" : "false",
                "isWork": $('#looking_for_work').prop("checked") === true ? "true" : "false"
            },
            dataType: "json",
            success: function () {
                window.location.href = 'http://localhost:8080/login';
            },
            error: function () {
                $('#alert-email').css("display", "block");
            }
        });
    });
    $('#btnLogin').on("click", function (event) {
        event.preventDefault();
        $('#alert-email').css('display', 'none');
        $('#alert-pass').css('display', 'none');
        $.ajax({
            type: "GET",
            url: "/login-account",
            data: {
                "email": $('#login-email').val(),
                "passd": $('#login-pass').val(),
            },
            dataType: "json",
            success: function () {
                window.location.href = 'http://localhost:8080';
            },
            error: function (err) {
                if (err.responseText === 'FAIL_EMAIL') {
                    $('#alert-email').css('display', 'block');
                }
                if (err.responseText === 'FAIL_PASS') {
                    $('#alert-pass').css('display', 'block');
                }
            }
        });
    });
});