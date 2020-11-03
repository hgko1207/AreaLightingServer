const ApartmentManager = function() {
	const DataTable = {
		ele: "#cityTable",
		table: null,
		option: {
			columns: [
				{ data: "name" },
			]
		},
		init: function() {
			this.table = Datatables.select(this.ele, this.option);
			this.search();
			return this.table;
		},
		search: function() {
			var param = new Object();
			Datatables.rowsAdd(this.table, contextPath + "/city/search", param);
		}
	}
	
	return {
		init: function() {
			DataTable.init();
		},
		table: function() {
			return DataTable.table;
		}
	}
}();

$(document).ready(function() {
	ApartmentManager.init();
	
	const cityTable = ApartmentManager.table();
	
	cityTable.on('select', function (e, dt, type, indexes) {
    	e.preventDefault();
        var rowData = cityTable.rows(indexes).data().toArray()[0];
        $('#apartment_card').removeClass('d-none');
        $('#danji_name').text(rowData.name + "의 단지 목록");
        
        $('#danji_regist').empty();
        $("#apartment_body").empty();
        
        $.ajax({
			type: "GET",
	       	url: contextPath + "/city/get",
	       	data: {"id": rowData.id},
	       	success: function(response) {
	       		const registButton = `<a href="${contextPath}/apartment/regist?id=${rowData.id}" 
	    			class="btn bg-blue-400"><i class="icon-pencil5 mr-2"></i>단지 등록</a>`;
	            $('#danji_regist').append(registButton);
	            
	            $.each(response.apartments, function(index, data) {
	            	let div = `<div class="card-group-control card-group-control-left">
		    			<div class="card">
		    				<div class="card-header header-elements-inline">
		    					<h6 class="card-title">
		    						<a data-toggle="collapse" class="text-default font-weight-bold" href="#collapsible-control-group1">${data.name}</a>
		    					</h6>
		    					<div class="header-elements">
		    						<a th:href="@{/apartment/regist}" class="btn bg-primary btn-sm">추가</a>
		    					</div>
		    				</div>
		    				
		    				<div id="collapsible-control-group1" class="collapse show">
		    					<div class="card-body">`;
	            	
	            	$.each(data.floors, function(index, floor) {
	            		console.log(floor);
	            		div += `<div>${floor.name} / 채널 번호 : ${floor.channel}</div>`;
	            	});
	            	
	            	div += `</div></div></div></div>`;
	            	
		    		$("#apartment_body").append(div);
	            });
	       	}
        });
    }).on('deselect', function (e, dt, type, indexes) {
    	$('#apartment_card').addClass('d-none');
    });
});
