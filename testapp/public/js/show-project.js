$(document).ready(function () { 

    $('.btn-join-job').on("click", function() { 
        var id_project = this.id.split('-')[1];
        $.ajax({
            url: '/join-a-job',
            type: 'post',
            data: {
                "id_project": id_project
            },
            success: function(){ 
                location.reload();
            },
            error: function() { 
                console.log('bb');
            }
        });
    });
    $('.btn-cancel-job').on("click", function() { 
        var id_project = this.id.split('-')[1];
        $.ajax({
            url: '/cancel-a-job',
            type: 'post',
            data: {
                "id_project": id_project
            },
            success: function(){ 
                location.reload();
            },
            error: function() { 
                console.log('bb');
            }
        });
    });
    $('.btn-offer').on("click", function() { 
        var id_project = this.id.split('-')[1];
        $.ajax({
            url: '/offer-candidate',
            type: 'post',
            data: {
                "id-join-project": id_project,
            },
            success: function(){ 
                location.reload();
            },
            error: function() { 
                console.log('bb');
            }
        });
    });
    $('.btn-reject').on("click", function() { 
        var id_project = this.id.split('-')[1];
        $.ajax({
            url: '/reject-candidate',
            type: 'post',
            data: {
                "id-join-project": id_project,
            },
            success: function(){ 
                location.reload();
            },
            error: function() { 
                console.log('bb');
            }
        });
    });
});