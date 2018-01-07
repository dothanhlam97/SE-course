$(document).ready(function() {
    
    $('#btn-post-project').on("click", function(event) {
        event.preventDefault();
        $.ajax({
            url: '/post-one-project',
            type: 'post',
            data: { 
                'name': $('#name-of-project').val(),
                'about': $('#about-project').val(),
                'requirement': $('#require-project').val()
            },
            success: function() { 
                // bootbox.alert("Post project success.");
                window.location = 'localhost:8080';
            },
            error: function() {

            }
        })
    });
});