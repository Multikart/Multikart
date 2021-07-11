(function($) {
    "use strict";
    function getData(response) {
    var abc = JSON.parse(response);
    var id;
        $("#batchDelete").jsGrid({
            width: "100%",
            autoload: true,
            confirmDeleting: false,
            paging: true,
            sorting: true,
            pageSize: 5,
            controller: {
                loadData: function () {
                    return abc;
                }
            },
            fields: [
            	{ name:"id",css:"hide"},
                { name: "name", title:"Name", type: "text", width: 100 },
                { name: "description", title:"Description", type: "text", width: 100 },
                { name: "validflag", title:"Validflag", type: "text", width: 100 },
                {
                	title: "Edit",
                    itemTemplate: function () {
                        return $("<button>").attr("type", "button").text("Edit").addClass("btn btn-warning btn-sm  mb-0 b-r-4")
                            .click(function () {
                                editItem(this);
                            });
                 	}
                },
                {
                    title: "Delete",
                    itemTemplate: function () {
                        return $("<button>").attr("type", "button").text("Delete").addClass("btn btn-primary btn-delete btn-sm  mb-0 b-r-4")
                            .click(function () {
                                deleteItem(this);
                            });
                    }
                }
            ]
        });
    }
    
    function editItem(item) {
        var id = item.parentElement.parentElement.children[0].innerHTML;
        window.location.href = "/admin/role/edit-role?id=" + id;
    }
    function deleteItem(item) {
        var id = item.parentElement.parentElement.children[0].innerHTML;
        $.ajax({
            url: "/admin/role/delete-role?id=" + id,
            type: "GET",
            success: function (response) {
                if (response.status !== 200) {
                    swal("Successful!", "You insert role successfully!", "success");
                    changeValidflag(item,2);
                } else {
                    swal("Failed!", "Please check!", "error");
                }
            },
            error: function (response) {
                swal("Failed!", "Call api fail!", "error");
            }
        });
    }
    function changeValidflag(item,value) {
        item.parentElement.parentElement.children[3].innerHTML = value;
    }
    var selectedItems = [];
    var selectItem = function(item) {
        selectedItems.push(item);
    };
    var unselectItem = function(item) {
        selectedItems = $.grep(selectedItems, function(i) {
            return i !== item;
        });
    };
    var deleteSelectedItems = function() {
        if(!selectedItems.length || !confirm("Are you sure?"))
            return;
        deleteClientsFromDb(selectedItems);
        var $grid = $("#batchDelete");
        $grid.jsGrid("option", "pActionIndex", 1);
        $grid.jsGrid("loadData");
        selectedItems = [];
    };
    var deleteClientsFromDb = function(deletingClients) {
        db.clients = $.map(db.clients, function(client) {
            return ($.inArray(client, deletingClients) > -1) ? null : client;
        });
    };
    $.ajax({
        url: window.location.href + "/page",
        type: "GET",
        success: function (response) {
            getData(response);
        },
        error: function () {

        }
    });
})(jQuery);
