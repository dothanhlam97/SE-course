$(document).ready(function() { 
    $('#btn-log-out').on("click", function() { 
        console.log("log out");
        $.ajax({
            url: "/log-out",
            type: "post", 
            data: {},
            success: function() { 
            },
            error: function() { 

            }
        })
    });
})