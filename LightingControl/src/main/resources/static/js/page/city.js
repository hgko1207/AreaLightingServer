const CityManager = function() {
	const name = "도시";
	
	const DataTable = {
		ele: "#cityTable",
		table: null,
		option: {
			columns: [
				{
					width: "10%",
					render: function(data, type, row, meta) {
						return meta.row + 1
					}
				},
				{ data: "name" },
				{
					width: "10%",
					render: function(data, type, row, meta) {
						return '<button type="button" class="btn btn-outline bg-primary text-primary-600 btn-sm"'
						+ 'onClick="CityManager.modal(' + row.id + ')"><i class="icon-pencil7"></i></button>'
						+ '<button type="button" class="btn btn-outline bg-danger text-danger-600 btn-sm" '
						+ 'onClick="CityManager._delete(' + row.id + ')"><i class="icon-trash"></i></button>';
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
			Datatables.rowsAdd(this.table, contextPath + "/city/search", param);
		}
	}
	
	const Control = function() {
		$("#searchBtn").click(function() {
			DataTable.search();
		});

		$("#registForm").submit(function(e) {
			e.preventDefault();
			var form = $(this);
			var url = form.attr('action');
			
		    registCommon(url, form.serializeObject(), name, CityManager);
		});

		$('#updateForm').submit(function(e) {
			e.preventDefault();
			var form = $(this);
			var url = form.attr('action');
			
		 	updateModalCommon(url, form.serializeObject(), name, DataTable, "updateCityModal");
		}); 
	}
	
	return {
		init: function() {
			DataTable.init();
			Control();
		},
		_delete: function(id) {
			deleteCommon(contextPath + "/city/delete", id, name, DataTable);
		},
		modal: function(id) {
			$.ajax({
	    		url: contextPath + "/city/get",
	    		type: "GET",
	    		data: {"id": id},
	    		success: function(response) {
	    			$('#updateForm input[name="id"]').val(response.id);
	    			$('#updateForm input[name="name"]').val(response.name);
	    			$("#updateCityModal").modal();
	           	}
	    	}); 
		},
		success: function() {
			$('#registForm input[name="name"]').val("");
			DataTable.search();
		}
	}
}();

$(document).ready(function() {
	CityManager.init();
});
