const UserManager = function() {
	const name = "유저";
	
	const DataTable = {
		ele: "#userTable",
		table: null,
		option: {
			columns: [
				{
					width: "10%",
					render: function(data, type, row, meta) {
						return meta.row + 1
					}
				},
				{ data: "userId" },
				{
					render: function(data, type, row, meta) {
						return row.city.name;
					}
				},
				{
					render: function(data, type, row, meta) {
						return row.apartment.name;
					}
				},
				{
					width: "10%",
					render: function(data, type, row, meta) {
						return '<button type="button" class="btn btn-outline bg-primary text-primary-600 btn-sm"'
						+ 'onClick="UserManager.modal(' + row.id + ')"><i class="icon-pencil7"></i></button>'
						+ '<button type="button" class="btn btn-outline bg-danger text-danger-600 btn-sm" '
						+ 'onClick="UserManager._delete(' + row.id + ')"><i class="icon-trash"></i></button>';
					}
				}
			]
		},
		init: function() {
			this.table = Datatables.order(this.ele, this.option, 1);
			this.search();
		},
		search: function() {
			var param = new Object();
			param.name = $("input[name=name]").val();
			Datatables.rowsAdd(this.table, contextPath + "/user/search", param);
		}
	}
	
	const Control = function() {
		$("#searchBtn").click(function() {
			DataTable.search();
		});

		$('#updateForm').submit(function(e) {
			e.preventDefault();
			var form = $(this);
			var url = form.attr('action');
			
		 	updateModalCommon(url, form.serializeObject(), name, DataTable, "updateModal");
		}); 
	}
	
	return {
		init: function() {
			DataTable.init();
			Control();
		},
		_delete: function(id) {
			deleteCommon(contextPath + "/user/delete", id, name, DataTable);
		},
		modal: function(id) {
			$.ajax({
	    		url: contextPath + "/user/get",
	    		type: "GET",
	    		data: {"id": id},
	    		success: function(response) {
	    			$('#updateForm input[name="id"]').val(response.id);
	    			$('#updateForm input[name="name"]').val(response.name);
	    			$('#updateModal').modal();
	           	}
	    	}); 
		},
		success: function() {
			DataTable.search();
		}
	}
}();

$(document).ready(function() {
	UserManager.init();
});
