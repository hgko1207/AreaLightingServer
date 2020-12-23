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
			param.city = $("select[name=city]").val();
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
			
			if (!$('input[name=password]').val()) {
	    		swalInit.fire({title: "비밀번호를 입력하세요.", type: "warning"});
	    		return;
	    	}
	    	
	    	if (!$('select[name=apartmentId]').val()) {
	    		swalInit.fire({title: "단지를 선택하세요.", type: "warning"});
	    		return;
	    	}
	    	
	    	const password = $('input[name=password]').val();
	    	const passwordCheck = $('input[name=passwordCheck]').val();
	    	if (password != passwordCheck) {
	    		swalInit.fire({title: "비밀번호가 일치하지 않습니다.", type: "error"});
	    		return;
	    	}
			
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
	    		success: function(user) {
	    			$('#updateForm input[name="id"]').val(user.id);
	    			$('#updateForm input[name="userId"]').val(user.userId);
	    			$('#updateForm select[name="cityId"]').val(user.city.id);
	    			
	    			$.ajax({
	    				url: contextPath + "/user/apartment/list",
	    				type: "GET",
	    				data: {"cityId": user.city.id},
	    				success: function(response) {
	    					if (response.length > 0) {
	    						$.each(response, function (i, item) {
	    							$('#apartmentSelect').append($('<option>', {
	    							    value: item.id,
	    							    text: item.name
	    							}));
	    						});
	    						
	    						$('#updateForm select[name="apartmentId"]').val(user.apartment.id);
	    					}
	    					
	    					$('#updateModal').modal();
	    		       	}
	    			}); 
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
	
	$('#citySelect').change(() => {
		$("#apartmentSelect").empty();
		
		$.ajax({
			url: contextPath + "/user/apartment/list",
			type: "GET",
			data: {"cityId": $('#citySelect').val()},
			success: function(response) {
				if (response.length > 0) {
					$.each(response, function (i, item) {
						$('#apartmentSelect').append($('<option>', {
						    value: item.id,
						    text: item.name
						}));
					});
				}
	       	}
		}); 
	});
});
