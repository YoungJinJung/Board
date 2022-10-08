let index = {
    init: function() {
        $("#btn-save").on("click",() => {
            this.save();
        });
        $("#btn-update").on("click",() => {
            this.update();
        });
        $("#btn-reply-save").on("click", () => {
               this.saveReply();
        });
    },

    save: function() {
        let data = {
            title: $("#title").val(),
            category: $("#category").val(),
            content: $("#content").val(),
        }

        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
        }).done(function(resp) {
            alert("Success Save Post");
            location.href = "/";
        }).fail(function(error) {
            alert("Failed Save Post");
        })
    },

    update: function() {
        let id = $("#id").val();
        let data = {
            title: $("#title").val(),
            category: $("#category").val(),
            content: $("#content").val(),
        }

        $.ajax({
            type: "PUT",
            url: "/api/board/" + id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
        }).done(function(resp) {
            alert("Success Update Post");
            location.href = "/";
        }).fail(function(error) {
            alert("Failed Update Post");
        })
    },

    deleteById : function (boardId) {
        $.ajax({
               type: "DELETE",
               url: `/api/board/${boardId}`,
               contentType: "application/json; charset=utf-8",
           }).done(function(resp) {
               alert("Success Delete Post");
               location.href = "/";
           }).fail(function(error) {
               alert("Failed Delete Post");
           })
    },

    saveReply: function () {
        let data = {
            userId: $("#userId").val(),
            boardId: $("#boardId").val(),
            content: $("#reply-content").val()
        }
        $.ajax({
            type: "POST",
            url: `/api/board/${data.boardId}/reply`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
        }).done(function (resp) {
            alert("Success Save Reply");
            location.href = `/board/${data.boardId}`;
        }).fail(function (error) {
            alert(JSON.stringify(error))
        });
    },

    deleteReply: function (boardId, replyId) {
        $.ajax({
            type: "DELETE",
            url: `/api/board/reply/${replyId}`,
            contentType: "application/json; charset=utf-8",
        }).done(function (resp) {
            alert("Success Remove Reply");
            location.href = `/board/${boardId}`;
        }).fail(function (error) {
            alert(JSON.stringify(error))
        });
    }
}

index.init();